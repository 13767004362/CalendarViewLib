package com.xingen.calendarview.view;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.xingen.calendarview.bean.DayBean;
import com.xingen.calendarview.bean.MonthBean;
import com.xingen.calendarview.bean.ViewStyleBean;
import com.xingen.calendarview.listener.ItemClickListener;
import com.xingen.calendarview.listener.ResultCallBack;
import com.xingen.calendarview.listener.ViewStateCallback;
import com.xingen.calendarview.task.DrawTask;
import com.xingen.calendarview.thread.TaskThread;
import com.xingen.calendarview.touch.ViewClickHandler;
import com.xingen.calendarview.touch.ViewTouchHandler;
import com.xingen.calendarview.utils.common.DateUtils;

/**
 * Created by ${xinGen} on 2018/1/15.
 * blog:http://blog.csdn.net/hexingen
 */

public class DateTableSurfaceView extends SurfaceView implements SurfaceHolder.Callback, ResultCallBack, ViewStateCallback, ViewClickHandler.ClickCallback {
    private final String TAG = DateTableSurfaceView.class.getSimpleName();
    /**
     * 用于管理Surface对象
     */
    private SurfaceHolder surfaceHolder;
    /**
     * 工作线程的Handler
     */
    private volatile Handler workThreadHandler;

    private Looper looper;
    private ViewTouchHandler touchHanlder;
    private ItemClickListener itemClickListener;
    private ViewStyleBean viewStyleBean;
    /**
     * 当前选中的日期
     */
    private String currentSelectDate;
    /**
     * 月份的实体
     */
    private MonthBean monthBean;

    private String incomingDate;
    public DateTableSurfaceView(Context context) {
        super(context);
        init();
    }

    public DateTableSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.surfaceHolder = getHolder();
        // 添加监听
        this.surfaceHolder.addCallback(this);
        this.setFocusableInTouchMode(true);
        this.viewStyleBean = new ViewStyleBean.Builder().builder();
        this.touchHanlder = new ViewClickHandler.Builder().setClickCallback(this).builder();
        //设置一个默认值，用于选中当天，显示一个圆形的背景。
        this.currentSelectDate = DateUtils.getTodayDate();
    }

    private boolean isExistTask;

    private void createWorkThread() {
        HandlerThread handlerThread = new HandlerThread(TAG + "WorkThread");
        handlerThread.start();
        this.looper = handlerThread.getLooper();
        this.workThreadHandler = new Handler(this.looper);
        if (isExistTask) {
            isExistTask = false;
            this.setMonth(this.incomingDate);
        }
    }

    /**
     * 当SurfaceView被显示时会调用的方法:创建线程
     *
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        createWorkThread();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    /**
     * 当SurfaceView被隐藏会销毁时调用的方法：停止线程
     *
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        this.looper.quit();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (monthBean != null) {
            setMeasuredDimension(monthBean.getViewBean().getWidth(), monthBean.getViewBean().getHeight());
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.touchHanlder.handleEvent(event);
        return true;
    }
    @Override
    public void deliverResult(MonthBean monthBean) {
        this.monthBean = monthBean;
    }
    /**
     * 传入参数格式：年 - 月。
     * <p>
     * 例如：2017-09，不可传入2017-9。
     *
     * @param date
     */
    public void setMonth(String date) {
        if (workThreadHandler == null) {
            isExistTask = true;
            this.incomingDate = date;
            return;
        }
        if (monthBean != null && monthBean.getCurrentMonth().equals(date)) {
            return;
        }
        executeTask(date);
    }

    private void executeTask(String date) {
        DrawTask.Builder builder = new DrawTask.Builder();
        builder.setIncomingDate(date)
                .setCallBack(this)
                .setViewStateCallback(this)
                .setCurrentSelectDate(this.currentSelectDate)
                .setSurfaceHolder(this.surfaceHolder);
        workThreadHandler.post(new TaskThread(builder.builder()));
    }

    @Override
    public int getViewWidth() {
        return getWidth();
    }
    @Override
    public Context getViewContext() {
        return this.getContext();
    }
    @Override
    public ViewStyleBean getViewStyle() {
        return this.viewStyleBean;
    }
    @Override
    public void click(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (monthBean == null) return;
        for (DayBean day : monthBean.getDayList()) {
            if (day.getRectF() != null && day.getRectF().contains(x, y)) {
                String date = getClickDate(day);
                if (currentSelectDate.equals(date)) {
                    return;
                }
                currentSelectDate = date;
                if (itemClickListener != null) {
                    itemClickListener.clickDate(date);
                }
                executeTask(monthBean.getCurrentMonth());
                break;
            }
        }
    }

    private String getClickDate(DayBean day) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(monthBean.getCurrentMonth());
        stringBuilder.append("-");
        if (day.getSolar().length() == 1) {
            stringBuilder.append("0");
        }
        stringBuilder.append(day.getSolar());
        return stringBuilder.toString();
    }

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
