package com.xingen.calendarview.touch;

import android.view.MotionEvent;


/**
 * Created by ${xinGen} on 2018/1/2.
 * blog:http://blog.csdn.net/hexingen
 * <p>
 * 这里处理，View的点击事件
 */

public class ViewClickHandler implements ViewTouchHandler {
    /**
     * 用于记录触摸点的位置
     */
    private int down_x, down_y, move_x, move_y;
    private ClickCallback clickCallback;

    public ClickCallback getClickCallback() {
        return clickCallback;
    }
    @Override
    public void handleEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.down_x = (int) event.getRawX();
                this.down_y = (int) event.getRawY();
                this.move_x = this.down_x;
                this.move_y = this.down_y;
                break;
            case MotionEvent.ACTION_MOVE:
                this.move_x = (int) event.getRawX();
                this.move_y = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                if (down_y == move_y && move_x == down_x) {
                    if (getClickCallback()!=null){
                        getClickCallback().click(event);
                    }
                }
                break;
            default:
                break;
        }
    }
    public static class Builder {
        private ViewClickHandler viewClickHandler;
        public Builder() {
            this.viewClickHandler = new ViewClickHandler();
        }
        public Builder setClickCallback(ClickCallback clickCallback) {
            this.viewClickHandler.clickCallback = clickCallback;
            return this;
        }
        public ViewClickHandler builder() {
            return this.viewClickHandler;
        }
    }
    public  interface  ClickCallback{
        void click(MotionEvent event);
    }

}
