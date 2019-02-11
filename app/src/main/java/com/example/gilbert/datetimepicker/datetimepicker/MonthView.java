package com.example.gilbert.datetimepicker.datetimepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.gilbert.datetimepicker.helper.MonthViewDrawHelper;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class MonthView extends View {

    private LocalDate selectedDate = LocalDate.now();
    private DayOfWeek startDayOfWeek = DayOfWeek.SUNDAY;
    private int measuredWidth;
    private int measuredHeight;

    private MonthViewDrawHelper helper;
    private OnDaySelectedListener onDaySelectedListener;

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
        helper = new MonthViewDrawHelper(selectedDate, startDayOfWeek, measuredWidth, measuredHeight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean handled = false;
        float eventX = motionEvent.getX();
        float eventY = motionEvent.getY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                selectDay(getDateByPosition(eventX, eventY));
                invalidate();
                handled = true;
                break;
        }
        return handled;
    }

    public void setOnDaySelectedListener(OnDaySelectedListener onDaySelectedListener) {
        this.onDaySelectedListener = onDaySelectedListener;
    }

    public interface OnDaySelectedListener {
        void onDaySelected(LocalDate date);
    }

    private void selectDay(LocalDate dayDate) {
        if (onDaySelectedListener != null) {
            onDaySelectedListener.onDaySelected(dayDate);
        }
    }

    private LocalDate getDateByPosition(float x, float y) {
        return LocalDate.of(selectedDate.getYear(), selectedDate.getMonth(), helper.getDayNumberByPosition(x, y));
    }
}
