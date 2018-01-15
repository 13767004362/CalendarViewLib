package com.xingen.calendarview.utils.size;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.xingen.calendarview.bean.MonthBean;
import com.xingen.calendarview.bean.ViewBean;
import com.xingen.calendarview.bean.ViewStyleBean;
import com.xingen.calendarview.constants.DataConfig;

import java.io.File;

/**
 * Created by ${xinGen} on 2018/1/9.
 */

public class ViewUtils {
    /**
     * 计算View的宽和高值
     *
     * @param context
     * @param defaultSize
     * @param measureSpec
     * @return
     */
    public static int measureSize(Context context, int defaultSize, int measureSpec) {
        //设置高度一个默认值
        int result = DisplayUtils.dip2px(context, defaultSize);
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);
        if (specMode == View.MeasureSpec.EXACTLY) {
            //已经控件的大小
            result = specSize;
        } else if (specMode == View.MeasureSpec.AT_MOST) {
            //遵循 AT_MOST,result不能大于specSize
            result = Math.min(result, specSize);
        } else { //设置高度一个默认值
            result = DisplayUtils.dip2px(context, defaultSize);
        }
        return result;
    }

    /**
     * 获取手机的宽度
     *
     * @param context
     * @return
     */
    public static int getPhoneWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 动态计算view的数据
     *
     * @param context
     * @param monthBean
     * @param viewStyleBean
     * @return
     */
    public static ViewBean calculateViewData(Context context, MonthBean monthBean, ViewStyleBean viewStyleBean) {
        ViewBean viewBean = new ViewBean();
        viewBean.setWidth(getPhoneWidth(context));
        viewBean.setPaddingTop(DisplayUtils.dip2px(context, viewStyleBean.getPadding_top_week()));
        Paint paint = new Paint();
        paint.setFakeBoldText(true);
        paint.setTextSize(DisplayUtils.sp2px(context, viewStyleBean.getTextSize_week()));
        Rect rect = new Rect();
        paint.getTextBounds(DataConfig.weekTexts[0], 0, DataConfig.weekTexts[0].length(), rect);
        viewBean.setWeekHeight(viewBean.getPaddingTop() + rect.height() + DisplayUtils.dip2px(context, viewStyleBean.getPadding_top_view()));
        viewBean.setColumnQuantity(7);
        //计算5行还是6行
        int size = (viewBean.getColumnQuantity()- (monthBean.getFirstDayWeek() - 1)) + 4 * viewBean.getColumnQuantity();
        viewBean.setRowQuantity(monthBean.getCurrentMaxDay() <= size ? 5 : 6);
        int height = (int) (viewBean.getWeekHeight() + viewBean.getWidth() * (1 - viewStyleBean.getPaddingProportion() * 2) / 7 * viewBean.getRowQuantity() + DisplayUtils.dip2px(context, viewStyleBean.getPadding_bottom_view()));
        viewBean.setHeight(height);
        //计算一列的高度，一行的宽度
        float rowSize = (float) (viewBean.getHeight() -viewBean.getWeekHeight() - DisplayUtils.dip2px(context, viewStyleBean.getPadding_bottom_view())) /viewBean.getRowQuantity();
        float columnSize = (viewBean.getWidth() * (1 - viewStyleBean.getPaddingProportion() * 2)) / viewBean.getColumnQuantity();
        viewBean.setColumnSize(columnSize);
        viewBean.setRowSize(rowSize);
        return viewBean;
    }


}


