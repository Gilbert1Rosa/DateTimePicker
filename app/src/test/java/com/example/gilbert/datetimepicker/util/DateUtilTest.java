package com.example.gilbert.datetimepicker.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class DateUtilTest {
    @Test
    public void testFirstDayOfMonth() {
        List<LocalDate> dates = new ArrayList<>();
        dates.add(LocalDate.of(2019, Month.JANUARY, 1));
        dates.add(LocalDate.of(2019, Month.JANUARY, 19));
        dates.add(LocalDate.of(2019, Month.JANUARY, 30));
        for (LocalDate date : dates) {
            DayOfWeek dayOfWeek = DateUtil.firstDayOfMonth(date);
            assertEquals(DayOfWeek.TUESDAY, dayOfWeek);
        }
    }
}
