package com.xingen.calendarview.bean;

import android.graphics.RectF;

/**
 * Created by ${xinGen} on 2018/1/2.
 * blog:http://blog.csdn.net/hexingen
 */

public class DayBean {

    /**
     * 屏幕中的区域
     */

    private RectF rectF;
    /**
     * 阳历
     */

    private String solar;
    /**
     * 农历
     */
    private String lunar;
    private int solarTextColor;

    public RectF getRectF() {
        return rectF;
    }

    public void setRectF(RectF rectF) {
        this.rectF = rectF;
    }

    public String getSolar() {
        return solar;
    }

    public void setSolar(String solar) {
        this.solar = solar;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    public int getSolarTextColor() {
        return solarTextColor;
    }

    public void setSolarTextColor(int solarTextColor) {
        this.solarTextColor = solarTextColor;
    }

    public int getLunarTextColor() {
        return lunarTextColor;
    }

    public void setLunarTextColor(int lunarTextColor) {
        this.lunarTextColor = lunarTextColor;
    }

    private int lunarTextColor;
}
