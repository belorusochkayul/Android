package com.example.firstandroid;

import android.graphics.Path;
import android.graphics.RectF;

class PathUtils {


    public static Path getSolidArcPath(Path solidArcPath,
                                       RectF outerCircleBounds, RectF innerCircleBounds,
                                       float startAngle, float sweepAngle) {
        solidArcPath.reset();

               float startX = MathUtils.getPointX(
                innerCircleBounds.centerX(),
                innerCircleBounds.width() / 2f,
                startAngle);
        float startY = MathUtils.getPointY(
                innerCircleBounds.centerY(),
                innerCircleBounds.height() / 2f,
                startAngle);
        solidArcPath.moveTo(startX, startY);

        solidArcPath.addArc(innerCircleBounds, startAngle, sweepAngle);

                solidArcPath.lineTo(
                MathUtils.getPointX(
                        outerCircleBounds.centerX(),
                        outerCircleBounds.width() / 2f,
                        startAngle + sweepAngle),
                MathUtils.getPointY(
                        outerCircleBounds.centerY(),
                        outerCircleBounds.height() / 2f,
                        startAngle + sweepAngle));

               solidArcPath.addArc(outerCircleBounds, startAngle + sweepAngle, -sweepAngle);

               solidArcPath.lineTo(startX, startY);

        return solidArcPath;
    }
}