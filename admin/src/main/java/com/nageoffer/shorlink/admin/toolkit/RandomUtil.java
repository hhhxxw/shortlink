package com.nageoffer.shorlink.admin.toolkit;

import java.util.Random;

/**
 * 分组ID随机生成器
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-09-09 09:36
 */
public final class RandomUtil {
    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int DEFAULT_LENGTH = 6;
    private static final Random RANDOM = new Random();

    /**
     * 生成包含数字和英文字母的6位随机字符串
     * @return 6位随机字符串
     */
    public static String generateRandom() {
        return generateRandom(DEFAULT_LENGTH);
    }

    /**
     * 生成指定长度的包含数字和英文字母的随机字符串
     * @param length 随机字符串长度
     * @return 指定长度的随机字符串
     */
    public static String generateRandom(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
