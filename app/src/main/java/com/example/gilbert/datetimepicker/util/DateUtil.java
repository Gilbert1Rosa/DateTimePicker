package com.example.gilbert.datetimepicker.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class DateUtil {
    public static DayOfWeek firstDayOfMonth(LocalDate date) {
        int dayOfMonth = date.getDayOfMonth() - 1;
        return date.getDayOfWeek().minus(dayOfMonth);
    }

    public static int weekLineCountInMonth(int year, Month month) {
        return 0;
    }
}
