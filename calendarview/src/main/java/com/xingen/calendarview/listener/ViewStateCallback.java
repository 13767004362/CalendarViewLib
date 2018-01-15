package com.xingen.calendarview.listener;

import android.content.Context;

import com.xingen.calendarview.bean.ViewStyleBean;

/**
 * Created by ${xinGen} on 2018/1/15.
 */

public interface ViewStateCallback {

    int getViewWidth();

    Context getViewContext();

    ViewStyleBean getViewStyle();
}
