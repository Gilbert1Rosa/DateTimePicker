package com.example.gilbert.datetimepicker.util;

public class MetricsUtil {
    public static int calculatePosition(int margin, int step, int count, int orientation) {
        int padding = (orientation == 0) ? Constants.MONTH_VIEW_HORIZONTAL_PADDING : Constants.MONTH_VIEW_VERTICAL_PADDING; // 0 = horizontal, otherwise vertical
        return (padding + margin) + step * count;
    }
}
