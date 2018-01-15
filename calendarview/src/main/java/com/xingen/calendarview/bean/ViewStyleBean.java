package com.xingen.calendarview.bean;

import com.xingen.calendarview.constants.DataConfig;

/**
 * Created by ${xinGen} on 2018/1/2.
 * <p>
 * blog:http://blog.csdn.net/hexingen
 * <p>
 * 日历控件的配置类
 */

public class ViewStyleBean {
    /**
     *  线段颜色
     */
    private int lineColor;
    /**
     * 周末文字颜色
     */
    private int weekTextColor;
    /**
     * 阳历字体颜色
     */
    private int solarTextColor;
    /**
     * 三种农历字体颜色
     */
    private int[] lunarTextArray;
    /**
     * 阳历字体大小
     */
    private float textSize_solar;
    private float textSize_lunar;
    /**
     * 周末文字的大小
     */
    private float textSize_week;
    /**
     * padding 的一边的比率
     */
    private float paddingProportion;
    private float padding_top_week;
    /**
     * 周末文字到日期的偏移量
     */
    private float padding_top_view;
    /**
     * 底部的偏移量
     */
    private float padding_bottom_view;
    private ViewStyleBean() {
        this.lineColor = DataConfig.LINE_COLOR;
        this.weekTextColor = DataConfig.WEEK_TEXT_COLOR;
        this.solarTextColor = DataConfig.SOLAR_TEXT_COLOR;
        this.lunarTextArray = DataConfig.LUNAR_TEXT_COLOR_ARRAY;
        this.textSize_solar = DataConfig.SOLAR_TEXT_SIZE;
        this.textSize_lunar = DataConfig.LUNAR_TEXT_SIZE;
        this.textSize_week = DataConfig.WEEK_TEXT_SIZE;
        this.paddingProportion = DataConfig.PADDING_PROPORTION;
        this.padding_top_week = DataConfig.PADDING_TOP_WEEK;
        this.padding_top_view = DataConfig.PADDING_TOP_VIEW;
        this.padding_bottom_view = DataConfig.PADDING_BOTTOM_VIEW;
    }

    public int getLineColor() {
        return lineColor;
    }

    public int getWeekTextColor() {
        return weekTextColor;
    }

    public int getSolarTextColor() {
        return solarTextColor;
    }

    public int[] getLunarTextArray() {
        return lunarTextArray;
    }

    public float getTextSize_solar() {
        return textSize_solar;
    }

    public float getTextSize_lunar() {
        return textSize_lunar;
    }

    public float getTextSize_week() {
        return textSize_week;
    }

    public float getPaddingProportion() {
        return paddingProportion;
    }

    public float getPadding_top_week() {
        return padding_top_week;
    }

    public float getPadding_top_view() {
        return padding_top_view;
    }

    public float getPadding_bottom_view() {
        return padding_bottom_view;
    }

    /**
     * 构建不同属性的对象
     */
    public static class Builder {
        private ViewStyleBean viewStyleBean;

        public Builder() {
            this.viewStyleBean = new ViewStyleBean();
        }

        public Builder setLineColor(int lineColor) {
            this.viewStyleBean.lineColor = lineColor;
            return this;
        }

        public Builder setWeekTextColor(int weekTextColor) {
            this.viewStyleBean.weekTextColor = weekTextColor;
            return this;
        }

        public Builder setSolarTextColor(int solarTextColor) {
            this.viewStyleBean.solarTextColor = solarTextColor;
            return this;
        }

        public Builder setLunarTextArray(int[] lunarTextArray) {
            this.viewStyleBean.lunarTextArray = lunarTextArray;
            return this;
        }

        public Builder setTextSize_solar(float textSize_solar) {
            this.viewStyleBean.textSize_solar = textSize_solar;
            return this;
        }

        public Builder setTextSize_lunar(float textSize_lunar) {
            this.viewStyleBean.textSize_lunar = textSize_lunar;
            return this;
        }

        public Builder setTextSize_week(float textSize_week) {
            this.viewStyleBean.textSize_week = textSize_week;
            return this;
        }

        public Builder setPaddingProportion(float paddingProportion) {
            this.viewStyleBean.paddingProportion = paddingProportion;
            return this;
        }

        public Builder setPadding_top_week(float padding_top_week) {
            this.viewStyleBean.padding_top_week = padding_top_week;
            return this;
        }

        public Builder setPadding_top_view(float padding_top_view) {
            this.viewStyleBean.padding_top_view = padding_top_view;
            return this;
        }

        public Builder setPadding_bottom_view(float padding_bottom_view) {
            this.viewStyleBean.padding_bottom_view = padding_bottom_view;
            return this;
        }

        public ViewStyleBean builder() {
            return this.viewStyleBean;
        }
    }
}
