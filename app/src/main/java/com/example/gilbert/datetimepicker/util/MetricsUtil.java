package com.example.gilbert.datetimepicker.util;

public class MetricsUtil {
    public static int calculatePosition(int step, int count, int orientation) {
        int padding = (orientation == 0) ? Constants.MONTH_VIEW_HORIZONTAL_PADDING : Constants.MONTH_VIEW_VERTICAL_PADDING; // 0 = horizontal, otherwise vertical
        int margin = (orientation == 0) ? Constants.MONTH_VIEW_HORIZONTAL_MARGIN : Constants.MONTH_VIEW_VERTICAL_MARGIN;
        return (padding + margin) + step * count;
    }

    public static int getPositionByCoordinates(float x, float y, int xStep, int yStep) {
        float vertical = y;
        float horizontal = x;
        vertical -= Constants.MONTH_VIEW_VERTICAL_PADDING + Constants.MONTH_VIEW_VERTICAL_MARGIN;
        vertical /= yStep;
        horizontal -= Constants.MONTH_VIEW_HORIZONTAL_PADDING + Constants.MONTH_VIEW_HORIZONTAL_MARGIN;
        horizontal /= xStep;
        return (int)(horizontal + vertical * 7);
    }
}
