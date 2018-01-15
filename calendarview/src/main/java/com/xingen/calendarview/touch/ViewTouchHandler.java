package com.xingen.calendarview.touch;

import android.view.MotionEvent;

/**
 * Created by ${xinGen} on 2018/1/2.
 * blog:http://blog.csdn.net/hexingen
 * View的touch事件处理类
 */

public interface ViewTouchHandler {
    /**
     * 处理触摸事件
     * @param event
     */
    void handleEvent(MotionEvent event);
}
