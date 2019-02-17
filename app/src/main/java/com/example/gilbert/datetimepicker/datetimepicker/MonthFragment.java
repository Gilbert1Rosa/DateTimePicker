package com.example.gilbert.datetimepicker.datetimepicker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gilbert.datetimepicker.R;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class MonthFragment extends Fragment {

    private LocalDate selectedDate;
    private Month selectedMonth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.month_fragment, container, false);
        MonthView monthView = v.findViewById(R.id.monthView);
        monthView.setSelectedDate(selectedDate);
        monthView.setMonth(selectedMonth);
        monthView.setStartDayOfWeek(DayOfWeek.SUNDAY);
        return v;
    }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    public void setSelectedMonth(Month selectedMonth) {
        this.selectedMonth = selectedMonth;
    }
}
