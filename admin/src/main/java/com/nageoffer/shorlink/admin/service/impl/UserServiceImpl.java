package com.nageoffer.shorlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nageoffer.shorlink.admin.common.convention.exception.ClientException;
import com.nageoffer.shorlink.admin.common.enums.UserErrorCodeEnum;
import com.nageoffer.shorlink.admin.dao.entity.UserDO;
import com.nageoffer.shorlink.admin.dao.mapper.UserMapper;
import com.nageoffer.shorlink.admin.dto.req.UserRegisterReqDTO;
import com.nageoffer.shorlink.admin.dto.resp.UserRespDTO;
import com.nageoffer.shorlink.admin.service.UserService;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.nageoffer.shorlink.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;
import static com.nageoffer.shorlink.admin.common.enums.UserErrorCodeEnum.USER_NAME_EXIST;
import static com.nageoffer.shorlink.admin.common.enums.UserErrorCodeEnum.USER_SAVE_ERROR;

/**
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-08-25 14:07
 */
// 标记为Spring的一个Bean
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    private final RBloomFilter userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    public UserServiceImpl(RBloomFilter userRegisterCachePenetrationBloomFilter, RedissonClient redissonClient) {
        this.userRegisterCachePenetrationBloomFilter = userRegisterCachePenetrationBloomFilter;
        this.redissonClient = redissonClient;
    }

    /**
     *
     * @param username 用户名
     * @return
     */
    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        // 添加空值检查
        if(userDO == null){
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }
    @Override
    public boolean hasUsername(String username) {
        return userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterReqDTO requestParam) {
        if(hasUsername(requestParam.getUsername())){
            throw new ClientException(USER_NAME_EXIST);
        }
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestParam.getUsername());
        try {
            if(lock.tryLock()){
                int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
                if(inserted < 1){
                    throw new ClientException(USER_SAVE_ERROR);
                }

                // 2. 注册成功后，将新用户名添加到布隆过滤器中
                userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
                return;
            }
            throw new ClientException(USER_NAME_EXIST);
        }finally {
            lock.unlock();
        }



    }
}
