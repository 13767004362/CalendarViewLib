package com.xingen.calendarview.thread;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.SurfaceHolder;

import com.xingen.calendarview.bean.DayBean;
import com.xingen.calendarview.bean.MonthBean;
import com.xingen.calendarview.bean.ViewBean;
import com.xingen.calendarview.bean.ViewStyleBean;
import com.xingen.calendarview.constants.DataConfig;
import com.xingen.calendarview.task.DrawTask;
import com.xingen.calendarview.utils.size.DisplayUtils;

/**
 * Created by ${xinGen} on 2018/1/15.
 */
public class TaskThread implements Runnable {
   private DrawTask drawTask;
   private Context context;
    public TaskThread(DrawTask drawTask) {
        this.drawTask=drawTask;
        this.context=this.drawTask.getViewStateCallback().getViewContext();
    }
    @Override
    public void run() {
        Canvas canvas = null;
        SurfaceHolder surfaceHolder=drawTask.getSurfaceHolder();
        try {
            //多线程操作，需要同步,避免两个不同的线程同时操作同一个Canvas对象.
            synchronized (surfaceHolder) {
                //获取画布的对象
                canvas = surfaceHolder.lockCanvas(null);
                MonthBean monthBean=calculate();
                Paint paint=drawTask.getPaint();
                onDraw(canvas,paint,monthBean);
                this.drawTask.getCallBack().deliverResult(monthBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            canvas=null;
        } finally {
            if (canvas!=null){
                //解锁画布，提交画好的图像
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
            drawTask.releaseResource();
            drawTask=null;
        }
    }
    /**
     * 计算数据
     * @return
     */
    private MonthBean calculate(){
        ViewStyleBean viewStyleBean=this.drawTask.getViewStateCallback().getViewStyle();
        MonthBean month = new MonthBean.Builder()
                .setMonthText( viewStyleBean,this.drawTask.getIncomingDate())
                .setCalculateViewData(context, viewStyleBean)
                .builder();
        return month;
    }
    private void onDraw(Canvas canvas, Paint paint,MonthBean monthBean) {
        //因SurfaceView会保存上一次的图像，所以需要清屏操作。
        canvas.drawColor(Color.WHITE);
        if (monthBean == null) return;
        drawWeekText(canvas,paint,monthBean);
        drawDateText(canvas,paint,monthBean);
    }

    /**
     * 绘制周末的文字
     *
     * @param canvas
     */
    private void drawWeekText(Canvas canvas,Paint paint,MonthBean monthBean) {
        ViewStyleBean viewStyleBean=this.drawTask.getViewStateCallback().getViewStyle();
        int width=this.drawTask.getViewStateCallback().getViewWidth();
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#cccccc"));
        paint.setStyle(Paint.Style.FILL);
        float y1 = DisplayUtils.dip2px(context, 0.5f);
        canvas.drawLine(0, y1, width, y1, paint);
        //画周日提示字
        paint.reset();
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        paint.setTextSize(DisplayUtils.sp2px(context, viewStyleBean.getTextSize_week()));
        paint.setColor(viewStyleBean.getWeekTextColor());
        int move_left = (int) (width * viewStyleBean.getPaddingProportion());
        ViewBean viewBean = monthBean.getViewBean();
        for (int i = 1; i <= DataConfig.weekTexts.length; ++i) {
            String week = DataConfig.weekTexts[i - 1];
            Rect rect1 = new Rect();
            paint.getTextBounds(week, 0, week.length(), rect1);

            float x = move_left + (i - 1) * viewBean.getColumnSize() + (viewBean.getColumnSize() - rect1.width()) / 2;
            float y = viewBean.getWeekHeight() - (viewBean.getWeekHeight() - rect1.height()) / 2;
            canvas.drawText(week, x, y, paint);
        }
    }

    /**
     * 绘制日期背景
     *
     * @param canvas
     */
    private void drawDateBG(RectF rectF, Canvas canvas,Paint paint) {
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#1cbf61"));
        canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.height() / 2, paint);
    }

    /**
     * 判断是否选中日期
     *
     * @param day
     * @return
     */
    protected boolean isSelect(String day) {
        boolean select = false;
        String[] s1 = drawTask.getCurrentSelectDate().split("-");
        String[] s2 = drawTask.getIncomingDate().split("-");
        if (s1[0].equals(s2[0])) {
            if (Integer.valueOf(s1[1]) == Integer.valueOf(s2[1])) {
                if (Integer.valueOf(day) == Integer.valueOf(s1[2])) {
                    select = true;
                }
            }
        }
        // Log.i(TAG, "判断是否是选中日期    " + selectDate + " 传入的年月 " + incomingDate + " 天 " + day + select);
        return select;
    }

    /**
     * 绘制日期 : 阳历、农历、24节气、阳历假日、农历假日
     *
     * @param canvas
     */
    private void drawDateText(Canvas canvas,Paint paint,MonthBean monthBean) {
        ViewStyleBean viewStyleBean=this.drawTask.getViewStateCallback().getViewStyle();
        //绘制日期
        for (int i = 1; i <= monthBean.getCurrentMaxDay(); ++i) {
            DayBean day = monthBean.getDayList().get(i - 1);
            //计算每个号所占的区域面积
            RectF rectF = day.getRectF();
            //选中的日期背景绘制
            boolean isSelect = isSelect(day.getSolar());
            if (isSelect) {
                drawDateBG(rectF, canvas,paint);
            }
            //绘制阳历
            paint.reset();
            paint.setAntiAlias(true);
            paint.setTextSize(DisplayUtils.sp2px(context, viewStyleBean.getTextSize_solar()));
            paint.setColor(isSelect ? Color.WHITE : day.getSolarTextColor());
            paint.setFakeBoldText(true);
            Rect solarBounds = new Rect();
            paint.getTextBounds(day.getSolar(), 0, day.getSolar().length(), solarBounds);
            float left = rectF.centerX() - (float) solarBounds.width() / 2;
            float bottom = rectF.centerY() - 5;
            canvas.drawText(day.getSolar(), left, bottom, paint);
            //绘制阴历
            paint.reset();
            paint.setColor(isSelect ? Color.WHITE : day.getLunarTextColor());
            paint.setAntiAlias(true);
            paint.setTextSize(DisplayUtils.sp2px(context, viewStyleBean.getTextSize_lunar()));
            Rect lunarBounds = new Rect();
            paint.getTextBounds(day.getLunar(), 0, day.getLunar().length(), lunarBounds);
            float y1 = rectF.centerY() + lunarBounds.height() + 5;
            float x1 = rectF.centerX() - (float) lunarBounds.width() / 2;
            canvas.drawText(day.getLunar(), x1, y1, paint);
        }
    }
}
