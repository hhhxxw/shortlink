package com.nageoffer.shorlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nageoffer.shorlink.project.dao.entity.ShortLinkDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 功能描述: 短链接持久层
 * </p>
 *
 * @author Hanxuewei
 * @since 2025/9/19
 */
public interface ShortLinkMapper extends BaseMapper<ShortLinkDO> {

    /**
     * 增加短链接点击次数
     * @param gid 分组标识（用于分片路由）
     * @param fullShortUrl 完整短链接
     */
    @Update("UPDATE t_link SET click_num = click_num + 1 " +
            "WHERE gid = #{gid} AND full_short_url = #{fullShortUrl} AND del_flag = 0")
    void incrementClickNum(@Param("gid") String gid, @Param("fullShortUrl") String fullShortUrl);
}
