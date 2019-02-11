package com.example.gilbert.datetimepicker.helper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.gilbert.datetimepicker.util.Constants;
import com.example.gilbert.datetimepicker.util.DateUtil;
import com.example.gilbert.datetimepicker.util.DrawUtil;
import com.example.gilbert.datetimepicker.util.MetricsUtil;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

public class MonthViewDrawHelper {

    private LocalDate selectedDate;
    private DayOfWeek startDayOfWeek;
    private DayOfWeek[] days;
    private int measuredWidth;
    private int measuredHeight;

    private static final int HORIZONTAL_MARGIN = 0;
    private static final int VERTICAL_MARGIN = 0;
    private int horizontalStep;
    private int verticalStep;

    public MonthViewDrawHelper(LocalDate selectedDate, DayOfWeek startDayOfWeek, int measuredWidth, int measuredHeight) {
        this.selectedDate = selectedDate;
        this.startDayOfWeek = startDayOfWeek;
        this.days = getDays();
        this.measuredWidth = measuredWidth;
        this.measuredHeight = measuredHeight;
        calculateSteps();
    }

    private void calculateSteps() {
        horizontalStep = measuredWidth / 7;
        verticalStep = 150;
    }

    public void drawWeekDayLabels(Canvas canvas) {
        Paint paint = DrawUtil.getDefaultBoldTextPaint();
        int offset = HORIZONTAL_MARGIN;
        for (DayOfWeek day : getDays()) {
            int x = Constants.MONTH_VIEW_HORIZONTAL_PADDING + offset;
            int y = Constants.MONTH_VIEW_VERTICAL_PADDING;
            canvas.drawText(day.toString().substring(0, 1), x, y, paint);
            offset += horizontalStep;
        }
    }

    public void drawWeekDayNumbers(Canvas canvas) {
        Paint paint = DrawUtil.getDefaultTextPaint();
        int offset = Arrays.binarySearch(days, DateUtil.firstDayOfMonth(selectedDate));
        int daysCount = selectedDate.lengthOfMonth();
        int actualDay = 1;
        int actualWeek = 1;
        int x;
        int y;
        while(actualDay <= daysCount) {
            if (offset % 7 == 0) {
                actualWeek++;
            }
            x = MetricsUtil.calculatePosition(HORIZONTAL_MARGIN, horizontalStep, offset % 7,0);
            y = MetricsUtil.calculatePosition(VERTICAL_MARGIN, verticalStep, actualWeek, 0);
            canvas.drawText(Integer.valueOf(actualDay).toString(), x, y, paint);
            offset++;
            actualDay++;
        }
    }

    public int getDayNumberByPosition(float x, float y) {
        return 1;
    }

    private DayOfWeek[] getDays() {
        DayOfWeek[] daysArray = new DayOfWeek[7];
        int daysPassed = 0;
        while(daysPassed < 7) {
            daysArray[daysPassed] = startDayOfWeek.plus(daysPassed);
            daysPassed++;
        }
        return daysArray;
    }

}
