package com.nageoffer.shorlink.project.toolkit;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

import static com.nageoffer.shorlink.project.common.constant.ShortLinkConstant.DEFAULT_CACHE_VALID_TIME;

/**
 * <p>
 * 功能描述: 短链接工具类
 * </p>
 *
 * @author Hanxuewei
 * @since 2025/10/11
 */
public class LinkUtil {
    public static long getLinkCacheValidDate(Date validDate){
//        return Optional.ofNullable(validDate)
//                .map(each -> DateUtil.between(new Date(), each, DateUnit.MS))
//                .orElse(DEFAULT_CACHE_VALID_TIME);

        if(validDate == null) {
            return DEFAULT_CACHE_VALID_TIME;
        } else {
            Date now = new Date();
            return DateUtil.between(now, validDate, DateUnit.MS);
        }


    }
}
 