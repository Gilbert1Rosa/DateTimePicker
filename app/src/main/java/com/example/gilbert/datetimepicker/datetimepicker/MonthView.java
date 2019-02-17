package com.example.gilbert.datetimepicker.datetimepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.gilbert.datetimepicker.helper.MonthViewDrawHelper;
import com.example.gilbert.datetimepicker.util.Constants;
import com.example.gilbert.datetimepicker.util.MetricsUtil;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

class MonthView extends View {

    private LocalDate selectedDate;
    private Month month;
    private DayOfWeek startDayOfWeek;
    private int measuredWidth;
    private int measuredHeight;

    private MonthViewDrawHelper helper;
    private OnDaySelectedListener onDaySelectedListener;

    interface OnDaySelectedListener {
        void onDaySelected(LocalDate date);
    }

    public MonthView(Context context) {
        super(context);
        initialize();
    }

    public MonthView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public void initialize() {

    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    public DayOfWeek getStartDayOfWeek() {
        return startDayOfWeek;
    }

    public void setStartDayOfWeek(DayOfWeek startDayOfWeek) {
        this.startDayOfWeek = startDayOfWeek;
    }

    public void setOnDaySelectedListener(OnDaySelectedListener onDaySelectedListener) {
        this.onDaySelectedListener = onDaySelectedListener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        helper.drawWeekDayLabels(canvas);
        helper.drawWeekDayNumbers(canvas);
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld) {
        super.onSizeChanged(xNew, yNew, xOld, yOld);
        measuredWidth = xNew;
        measuredHeight = yNew;
        helper = new MonthViewDrawHelper(selectedDate, month, startDayOfWeek, measuredWidth, measuredHeight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean handled = false;
        float eventX = motionEvent.getX();
        float eventY = motionEvent.getY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                handled = onTap(eventX, eventY);
                break;
        }
        return handled;
    }

    private boolean onTap(float x, float y) {
        boolean handled = false;
        int eventBorder = MetricsUtil.calculatePosition(helper.getVerticalStep(), 1, 0);
        int position = MetricsUtil.getPositionByCoordinates(x, y, helper.getHorizontalStep(), helper.getVerticalStep());
        eventBorder -= 50;
        if (y >= eventBorder) {
            selectDay(getDateByPosition(x, y));
            invalidate();
            handled = true;
        }
        return handled;
    }

    private void selectDay(LocalDate dayDate) {
        if (onDaySelectedListener != null) {
            onDaySelectedListener.onDaySelected(dayDate);
        }
    }

    private LocalDate getDateByPosition(float x, float y) {
        return LocalDate.of(selectedDate.getYear(), month, helper.getDayNumberByPosition(x, y));
    }
}
