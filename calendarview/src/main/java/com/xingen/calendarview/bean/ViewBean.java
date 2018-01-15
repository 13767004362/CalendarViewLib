package com.xingen.calendarview.bean;

/**
 * Created by ${xinGen} on 2018/1/9.
 *
 * 绘制View的参数
 */

public class ViewBean {
    private int width;
    private int height;
    private int paddingTop;
    private int weekHeight;
    private float rowSize;
    private float columnSize;
    /**
     * 多少行
     */
    private int rowQuantity;
    /**
     * 多少列
     */
    private int columnQuantity;

    public int getRowQuantity() {
        return rowQuantity;
    }

    public void setRowQuantity(int rowQuantity) {
        this.rowQuantity = rowQuantity;
    }

    public int getColumnQuantity() {
        return columnQuantity;
    }

    public void setColumnQuantity(int columnQuantity) {
        this.columnQuantity = columnQuantity;
    }


    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }

    public int getWeekHeight() {
        return weekHeight;
    }

    public void setWeekHeight(int weekHeight) {
        this.weekHeight = weekHeight;
    }

    public float getRowSize() {
        return rowSize;
    }

    public void setRowSize(float rowSize) {
        this.rowSize = rowSize;
    }

    public float getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(float columnSize) {
        this.columnSize = columnSize;
    }
}
