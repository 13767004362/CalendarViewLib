package com.xingen.calendarview.task;

import android.content.Context;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.xingen.calendarview.bean.ViewStyleBean;
import com.xingen.calendarview.listener.ResultCallBack;
import com.xingen.calendarview.listener.ViewStateCallback;
import com.xingen.calendarview.view.DateTableSurfaceView;

/**
 * Created by ${xinGen} on 2018/1/15.
 */

public class DrawTask {
    /**
     * 画笔
     */
    private Paint paint;
    /**
     * 管理surface
     */
    private SurfaceHolder surfaceHolder;
    /**
     * 当前选中的日期
     */
    private String currentSelectDate;
    /**
     * 传入时间
     */
    private String incomingDate;
    /**
     * 结果的回调接口
     */
    private ResultCallBack callBack;
    private ViewStateCallback viewStateCallback;

    private DrawTask(){
        this.paint=new Paint();
        this.paint.setAntiAlias(true);
    }
    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
    public ResultCallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(ResultCallBack callBack) {
        this.callBack = callBack;
    }
    public SurfaceHolder getSurfaceHolder() {
        return surfaceHolder;
    }

    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    public String getCurrentSelectDate() {
        return currentSelectDate;
    }

    public void setCurrentSelectDate(String currentSelectDate) {
        this.currentSelectDate = currentSelectDate;
    }

    public ViewStateCallback getViewStateCallback() {
        return viewStateCallback;
    }

    public void setViewStateCallback(ViewStateCallback viewStateCallback) {
        this.viewStateCallback = viewStateCallback;
    }

    public String getIncomingDate() {
        return incomingDate;
    }

    public void setIncomingDate(String incomingDate) {
        this.incomingDate = incomingDate;
    }

    public static class Builder {
        private DrawTask drawTask;
        public Builder(){
            this.drawTask=new DrawTask();
        }
        public Builder setPaint(Paint paint) {
            this.drawTask.paint = paint;
            return this;
        }
        public Builder setSurfaceHolder(SurfaceHolder surfaceHolder) {
            this.drawTask.surfaceHolder = surfaceHolder;
            return this;
        }
        public Builder setCallBack(ResultCallBack callBack) {
            this.drawTask.callBack = callBack;
            return this;
        }
        public Builder setCurrentSelectDate(String currentSelectDate) {
            this.drawTask.currentSelectDate = currentSelectDate;
            return this;
        }
        public Builder setViewStateCallback(ViewStateCallback viewStateCallback) {
            this.drawTask.viewStateCallback = viewStateCallback;
            return this;
        }
        public Builder setIncomingDate(String incomingDate) {
            this.drawTask.incomingDate = incomingDate;
            return this;
        }
        public  DrawTask builder(){
            return  drawTask;
        }
    }
    public  void  releaseResource(){
        this.paint=null;
        this.callBack=null;
        this.surfaceHolder=null;
        this.viewStateCallback=null;
        this.incomingDate=null;
    }
}
