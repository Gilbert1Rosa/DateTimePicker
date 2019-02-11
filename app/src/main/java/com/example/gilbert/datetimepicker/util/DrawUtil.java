package com.example.gilbert.datetimepicker.util;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class DrawUtil {
    public static Paint getDefaultTextPaint() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(60);
        paint.setTextAlign(Paint.Align.CENTER);
        return paint;
    }

    public static Paint getDefaultBoldTextPaint() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(60);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        return paint;
    }

    public static Paint getDefaultLinePaint() {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        return paint;
    }
}
