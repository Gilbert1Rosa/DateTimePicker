package com.example.gilbert.datetimepicker.datetimepicker;


import com.example.gilbert.datetimepicker.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.Nullable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class DatePicker extends DialogFragment {

    private ViewPager monthViewPager;

    public DatePicker() {

    }

    public static DatePicker create() {
        DatePicker datePicker = new DatePicker();
        return datePicker;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.date_picker_dialog, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        monthViewPager = view.findViewById(R.id.monthPages);
        monthViewPager.setAdapter(new MonthViewPagerAdapter(getChildFragmentManager(), getContext()));
    }

    class MonthViewPagerAdapter extends FragmentPagerAdapter {
        Context context;

        public MonthViewPagerAdapter(FragmentManager fragmentManager, Context context) {
            super(fragmentManager);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            MonthFragment fragment = new MonthFragment();
            int monthNumber = (position + 1);
            fragment.setSelectedMonth(Month.of(monthNumber));
            fragment.setSelectedDate(LocalDate.now());
            return fragment;
        }

        @Override
        public int getCount() {
            return 12;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            int monthNumber = (position + 1);
            return Month.of(monthNumber).toString();
        }
    }
}
