package com.example.firstandroid;

import android.graphics.PointF;

class MathUtils {

    public static PointF getPoint(float centerX, float centerY, float distance, float degrees) {
        return new PointF(
                getPointX(centerX, distance, degrees),
                getPointY(centerY, distance, degrees));
    }

    public static float getPointX(float centerX, float distance, float degrees) {
        return (float) (centerX + distance * Math.sin(-degrees * Math.PI / 180 + Math.PI / 2));
    }

    public static float getPointY(float centerY, float distance, float degrees) {
        return (float) (centerY + distance * Math.cos(-degrees * Math.PI / 180 + Math.PI / 2));
    }

}