package com.example.gilbert.datetimepicker.helper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.LayoutInflater;

import com.example.gilbert.datetimepicker.util.Constants;
import com.example.gilbert.datetimepicker.util.DateUtil;
import com.example.gilbert.datetimepicker.util.DrawUtil;
import com.example.gilbert.datetimepicker.util.MetricsUtil;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;

public class MonthViewDrawHelper {

    private LocalDate selectedDate;
    private DayOfWeek startDayOfWeek;
    private Month month;
    private DayOfWeek[] days;
    private int measuredWidth;
    private int measuredHeight;


    private int horizontalStep;
    private int verticalStep;

    public MonthViewDrawHelper(LocalDate selectedDate,
                               Month month,
                               DayOfWeek startDayOfWeek,
                               int measuredWidth,
                               int measuredHeight) {
        this.selectedDate = selectedDate;
        this.startDayOfWeek = startDayOfWeek;
        this.month = month;
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
        int offset = Constants.MONTH_VIEW_HORIZONTAL_MARGIN;
        for (DayOfWeek day : getDays()) {
            int x = Constants.MONTH_VIEW_HORIZONTAL_PADDING + offset;
            int y = Constants.MONTH_VIEW_VERTICAL_PADDING;
            canvas.drawText(day.toString().substring(0, 1), x, y, paint);
            offset += horizontalStep;
        }
    }

    public void drawWeekDayNumbers(Canvas canvas) {
        Paint blackPaint = DrawUtil.getDefaultTextPaint();
        Paint whitePaint = DrawUtil.getDefaultBoldTextPaint();
        LocalDate date = LocalDate.of(selectedDate.getYear(), month, 1);
        int offset = getFirstDayPosition();
        int daysCount = date.lengthOfMonth();
        int actualDay = 1;
        int actualWeek = 1;
        int xCoordinate;
        int yCoordinate;
        whitePaint.setColor(Color.WHITE);
        while(actualDay <= daysCount) {
            if (offset % 7 == 0) {
                actualWeek++;
            }
            xCoordinate = MetricsUtil.calculatePosition(horizontalStep, offset % 7,0);
            yCoordinate = MetricsUtil.calculatePosition(verticalStep, actualWeek, 0);
            if (actualDay == selectedDate.getDayOfMonth()) {
                canvas.drawCircle(xCoordinate + 10, yCoordinate - 50, 75, blackPaint);
                canvas.drawText(Integer.valueOf(actualDay).toString(), xCoordinate, yCoordinate, whitePaint);
            } else {
                canvas.drawText(Integer.valueOf(actualDay).toString(), xCoordinate, yCoordinate, blackPaint);
            }
            offset++;
            actualDay++;
        }
    }

    public int getDayNumberByPosition(float xCoordinate, float yCoordinate) {
        int dayNumber = MetricsUtil.getPositionByCoordinates(xCoordinate, yCoordinate, horizontalStep, verticalStep);
        LocalDate date = LocalDate.of(selectedDate.getYear(), month, 1);
        dayNumber -= 7 + getFirstDayPosition() - 1;
        if (dayNumber > date.lengthOfMonth()) {
            dayNumber = -1;
        }
        return dayNumber;
    }

    public int getVerticalStep() {
        return verticalStep;
    }

    public int getHorizontalStep() {
        return horizontalStep;
    }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
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

    private int getFirstDayPosition() {
        LocalDate date = LocalDate.of(selectedDate.getYear(), month, 1);
        return DateUtil.getDayPosition(days, DateUtil.firstDayOfMonth(date));
    }
}
