package com.xingen.calendarview.constants;

import android.graphics.Color;

/**
 * Created by ${xinGen} on 2018/1/2.

 * 默认的数据Config
 */


public final class DataConfig {
    /**
     * 线段颜色
     */
    public static final int LINE_COLOR = Color.parseColor("#e9e5cb");

    /**
     * 周末文字颜色
     */
    public static final int WEEK_TEXT_COLOR = Color.parseColor("#999999");
    /**
     * 阳历字体颜色
     */
    public static final int SOLAR_TEXT_COLOR = Color.parseColor("#333333");
    /**
     * 三种农历字体颜色
     */
    public static final int[] LUNAR_TEXT_COLOR_ARRAY = {
            Color.parseColor("#999999"),
            Color.parseColor("#ff4a4a"),
            Color.parseColor("#ff4a4a")};
    /**
     * 阳历和农历字体大小
     */
    public static final float SOLAR_TEXT_SIZE = 20;
    public static final float LUNAR_TEXT_SIZE = 11;
    /**
     * 周末文字的大小
     */
    public static final float WEEK_TEXT_SIZE = 15.7f;
    /**
     * padding 的一边的比率
     */
    public static float PADDING_PROPORTION = 0.06f;
    public static float PADDING_TOP_WEEK = 17.3f;
    /**
     * 周末文字到日期的偏移量
     */
    public static float PADDING_TOP_VIEW = 8;
    /**
     * 底部的偏移量
     */
    public static float PADDING_BOTTOM_VIEW = 5.3f;
    /**
     * 周日到周六的字符串
     */
    public static   final String[] weekTexts = {"日", "一", "二", "三", "四", "五", "六"};

    public static final String[] specialLunar = {"春节", "元宵", "端午", "七夕", "中秋", "重阳", "腊八", "除夕"};

    public static final String[] specialSolar = {"元旦", "情人", "妇女", "植树", "愚人", "劳动", "青年", "儿童", "建党", "建军", "教师", "国庆", "光棍", "艾滋病", "圣诞"};
}
