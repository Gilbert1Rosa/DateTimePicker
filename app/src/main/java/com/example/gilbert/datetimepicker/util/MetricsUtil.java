package com.example.gilbert.datetimepicker.util;

public class MetricsUtil {
    public static int calculatePosition(int step, int count, int orientation) {
        int padding = (orientation == 0) ? Constants.MONTH_VIEW_HORIZONTAL_PADDING : Constants.MONTH_VIEW_VERTICAL_PADDING; // 0 = horizontal, otherwise vertical
        int margin = (orientation == 0) ? Constants.MONTH_VIEW_HORIZONTAL_MARGIN : Constants.MONTH_VIEW_VERTICAL_MARGIN;
        return (padding + margin) + step * count;
    }

    public static int getPositionByCoordinates(float x, float y, int xStep, int yStep) {
        double vertical = y;
        double horizontal = x;
        double cellNumber;
        vertical -= Constants.MONTH_VIEW_VERTICAL_PADDING + Constants.MONTH_VIEW_VERTICAL_MARGIN;
        horizontal -= Constants.MONTH_VIEW_HORIZONTAL_PADDING + Constants.MONTH_VIEW_HORIZONTAL_MARGIN;
        horizontal /=  xStep;
        vertical /= yStep;
        cellNumber = Math.round(horizontal) + Math.round(vertical) * 7;
        return (int)cellNumber;
    }
}
