package com.xingen.calendarview.utils.month;

import android.graphics.RectF;

import com.xingen.calendarview.bean.DayBean;
import com.xingen.calendarview.bean.MonthBean;
import com.xingen.calendarview.bean.ViewStyleBean;
import com.xingen.calendarview.constants.DataConfig;
import com.xingen.calendarview.utils.calendar.CalendarUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by ${xinGen} on 2018/1/2.
 * blog:http://blog.csdn.net/hexingen
 */

public class MonthUtils {
    public static MonthBean calculationMonth(String monthText, MonthBean month) {
        /**
         * 获取当前的年，月，号，当前月最大号数
         * <p>
         * Calendar中的月份是：0-11
         */
        Calendar calendar;
        String[] s = monthText.split("-");
        int currentYear = Integer.valueOf(s[0]);
        int currentMonth = Integer.valueOf(s[1]) - 1;
        calendar = Calendar.getInstance(Locale.getDefault());
        //设置年
        calendar.set(Calendar.YEAR, currentYear);
        calendar.set(Calendar.MONTH, currentMonth);
        //获取当月最大天数
        month.setCurrentMaxDay(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        //设置为第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //结果是以星期天作为第一天
        month.setFirstDayWeek(calendar.get(Calendar.DAY_OF_WEEK));
        return month;
    }

    /**
     * 计算一月的天数
     *
     * @param month
     */
    public static void calculationDay(ViewStyleBean viewStyleBean,MonthBean month) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        String[] s = month.getCurrentMonth().split("-");
        int currentYear = Integer.valueOf(s[0]);
        int currentMonth = Integer.valueOf(s[1]) - 1;
        //设置年
        calendar.set(Calendar.YEAR, currentYear);
        calendar.set(Calendar.MONTH, currentMonth);
        List<DayBean> dayList = new ArrayList<>();
        for (int i = 1; i <= month.getCurrentMaxDay(); ++i) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
             DayBean day = new DayBean();
            day.setSolar(String.valueOf(i));
            day.setSolarTextColor(viewStyleBean.getSolarTextColor());
            day.setLunar(new CalendarUtils(calendar).toString());
            day.setLunarTextColor(calculationDayColor(viewStyleBean,day.getLunar()));
            dayList.add(day);
        }
        month.setDayList(dayList);
    }
    /**
     * 计算特殊节日的文字颜色
     *
     * @param day
     * @return
     */
   private static int calculationDayColor(ViewStyleBean viewStyleBean,String day) {
        int color = viewStyleBean.getLunarTextArray()[0];
        for (int i = 0; i < CalendarUtils.SolarTermsUtil.principleTermNames.length; ++i) {
            if (day.equals(CalendarUtils.SolarTermsUtil.principleTermNames[i])) {
                return viewStyleBean.getLunarTextArray()[2];
            }
        }
        for (int i = 0; i < CalendarUtils.SolarTermsUtil.sectionalTermNames.length; ++i) {
            if (day.equals(CalendarUtils.SolarTermsUtil.sectionalTermNames[i])) {
                return viewStyleBean.getLunarTextArray()[2];
            }
        }
        for (int i = 0; i < DataConfig.specialSolar.length; ++i) {
            if (day.equals(DataConfig.specialSolar[i])) {
                return viewStyleBean.getLunarTextArray()[1];
            }
        }
        for (int i = 0; i < DataConfig.specialLunar.length; ++i) {
            if (day.equals(DataConfig.specialLunar[i])) {
                return viewStyleBean.getLunarTextArray()[1];
            }
        }
        return color;
    }

    /**
     * 计算号所占的区域面积
     * @param month
     * @param weekHeight
     * @param marginLeft
     * @param rowSize
     * @param columnSize
     */
    public  static void calculationDayRect(MonthBean month, int weekHeight,float marginLeft,float rowSize, float columnSize) {
        for (int i = 1; i <= month.getCurrentMaxDay(); ++i) {
            DayBean day = month.getDayList().get(i - 1);
            int actualRow;
            int positionColumn;
            if ((month.getFirstDayWeek()- 1 + i) % 7 == 0) {
                actualRow = (month.getFirstDayWeek() - 1 + i) / 7 - 1;
                positionColumn = 7;
            } else {
                actualRow = (month.getFirstDayWeek() - 1 + i) / 7;
                positionColumn = (month.getFirstDayWeek() - 1 + i) - actualRow * 7;
            }
            //计算每个号所占的区域面积
            RectF rectF = new RectF();
            rectF.top = weekHeight + actualRow * rowSize;
            rectF.left = marginLeft+(positionColumn - 1) * columnSize;
            rectF.bottom = rectF.top + rowSize;
            rectF.right = rectF.left + columnSize;
            day.setRectF( rectF);
        }
    }

}
