package com.example.firstandroid;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PieChart extends View implements ValueAnimator.AnimatorUpdateListener {
    Paint painter;
    private List<PieSeries> series;
    private float size, sum, fraction, padding;
    RectF bound, lowerBound;
    // ValueAnimator animator;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float startAngle = 0f;
        float sweepAngle = 0f;
        for (PieSeries s : series) {
            painter.setColor(s.color);
            startAngle += sweepAngle;
            sweepAngle = s.value / sum * 360f;
            canvas.drawArc(bound, startAngle, sweepAngle, true, painter);
            //canvas.drawText(PieSeries.value + "%", dx, dy, painter);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        size = Math.min(w, h) - padding;
        bound = new RectF(padding, padding, size, size);
        invalidate();
    }

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

       init (attrs);
        padding = 20f;
        painter = new Paint(Paint.ANTI_ALIAS_FLAG);
        painter.setStyle(Paint.Style.FILL);

        Random rand = new Random();

        series = new ArrayList<>();
        series.add(new PieSeries(Color.argb(255, rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)), 100f));
        series.add(new PieSeries(Color.argb(255, rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)), 200f));
        series.add(new PieSeries(Color.argb(255, rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)), 150f));
        series.add(new PieSeries(Color.argb(255, rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)), 70f));

        for (PieSeries s : series) {
            sum += s.value;
        }
//        animator = new ValueAnimator();
//        animator.setDuration(3000);
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//        animator.addUpdateListener(this);
//        animator.setFloatValues(0f, 1f);
//        animator.start();
    }

    private void init(@Nullable AttributeSet set) {
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        fraction = animation.getAnimatedFraction();
        invalidate();
    }
}
