package com.brave.common.utils;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/13 <br/>
 * <b>description</b> ： 正则表达式 相关工具类
 */
public final class RegexUtils {
    private RegexUtils() {
        throw new RuntimeException("cannot be instantiated");
    }

    /**
     * 执行正则表达式，判断是否匹配
     *
     * @param input 待验证文本
     * @param regEx 正则表达式
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMatch(CharSequence input, @NonNull String regEx) {
        return input != null && Pattern.matches(regEx, input);
    }

    /**
     * 获取正则匹配的部分
     *
     * @param input 要匹配的字符串
     * @param regex 正则表达式
     * @return 正则匹配的部分
     */
    public static List<String> getMatches(CharSequence input, @NonNull String regex) {
        if (input == null) {
            return null;
        }
        List<String> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }

    /**
     * 获取正则匹配分组
     * <p>将字符串分割为给定的匹配项</p>
     *
     * @param input 要分组的字符串
     * @param regex 正则表达式
     * @return 正则匹配分组
     */
    public static String[] getSplits(String input, @NonNull String regex) {
        if (input == null) {
            return null;
        }
        return input.split(regex);
    }

    /**
     * 替换正则匹配的第一部分
     * <p>替换与给定的替换字符串相匹配的输入序列的第一个子序列</p>
     *
     * @param input       要替换的字符串
     * @param regex       正则表达式
     * @param replacement 代替者
     * @return 正则匹配的第一部分 被替换之后的字符串
     */
    public static String replaceFirst(String input, @NonNull String regex, @NonNull String replacement) {
        if (input == null) {
            return null;
        }
        return Pattern.compile(regex).matcher(input).replaceFirst(replacement);
    }

    public static String replaceFirst(String input, @NonNull String regex) {
        return replaceFirst(input, regex, "");
    }

    /**
     * 替换所有正则匹配的部分
     *
     * @param input       要替换的字符串
     * @param regex       正则表达式
     * @param replacement 代替者
     * @return 正则匹配的部分 被替换之后的字符串
     */
    public static String replaceAll(String input, @NonNull String regex, @NonNull String replacement) {
        if (input == null) {
            return null;
        }
        return Pattern.compile(regex).matcher(input).replaceAll(replacement);
    }

    public static String replaceAll(String input, @NonNull String regex) {
        return replaceAll(input, regex, "");
    }
}