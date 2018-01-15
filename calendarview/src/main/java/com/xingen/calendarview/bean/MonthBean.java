package com.xingen.calendarview.bean;

import android.content.Context;

import com.xingen.calendarview.utils.month.MonthUtils;
import com.xingen.calendarview.utils.size.ViewUtils;

import java.util.List;

/**
 * Created by ${xinGen} on 2018/1/2.
 * blog:http://blog.csdn.net/hexingen
 */

public class MonthBean {
    /**
     * 当前年，月
     */
    private String currentMonth;
    /**
     * 一个月的最大天数
     */
    private int currentMaxDay;
    /**
     * 第一天属于周几
     */
    private int firstDayWeek;
    /**
     * 一个月的天数
     */
    private List<DayBean> dayList;
    /**
     * 控件的绘制参数
     */
    private ViewBean viewBean;

    public ViewBean getViewBean() {
        return viewBean;
    }

    public void setViewBean(ViewBean viewBean) {
        this.viewBean = viewBean;
    }

    public String getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(String currentMonth) {
        this.currentMonth = currentMonth;
    }

    public int getCurrentMaxDay() {
        return currentMaxDay;
    }

    public void setCurrentMaxDay(int currentMaxDay) {
        this.currentMaxDay = currentMaxDay;
    }

    public int getFirstDayWeek() {
        return firstDayWeek;
    }

    public void setFirstDayWeek(int firstDayWeek) {
        this.firstDayWeek = firstDayWeek;
    }

    public List<DayBean> getDayList() {
        return dayList;
    }

    public void setDayList(List<DayBean> dayList) {
        this.dayList = dayList;
    }

    public static class Builder {
        private MonthBean monthBean;

        public Builder() {
            this.monthBean = new MonthBean();
        }

        /**
         * 设置一个月份
         * @param viewStyleBean
         * @param monthText
         * @return
         */
        public Builder setMonthText(ViewStyleBean viewStyleBean,String monthText){
            this.monthBean.setCurrentMonth(monthText);
            MonthUtils.calculationMonth(monthText,this.monthBean);
            MonthUtils.calculationDay(viewStyleBean,this.monthBean);
            return this;
        }

        /**
         * 动态计算view的一些参数
         * @param context
         * @param viewStyleBean
         * @return
         */
        public Builder setCalculateViewData(Context context,ViewStyleBean viewStyleBean){
            ViewBean viewBean=ViewUtils.calculateViewData(context,this.monthBean,viewStyleBean);
            this.monthBean.setViewBean(viewBean);
            MonthUtils.calculationDayRect(this.monthBean,viewBean.getWeekHeight(),(int) (viewBean.getWidth()*viewStyleBean.getPaddingProportion()), viewBean.getRowSize(), viewBean.getColumnSize());
            return this;
        }
        public MonthBean builder() {
            return monthBean;
        }
    }

}
