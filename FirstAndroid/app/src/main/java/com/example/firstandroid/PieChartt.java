package com.example.firstandroid;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.ColorInt;

import java.util.ArrayList;
import java.util.List;

public class PieChartt extends View {
    protected static final int START_ANGLE_OFFSET = 270;
    private static final int DEFAULT_TEXT_SIZE_SP = 12;
    private static final int DEFAULT_TEXT_COLOR = Color.parseColor("#ffffff");
    private static final int DEFAULT_ICON_SIZE_DP = 48;
    private static final int DEFAULT_DECOR_RING_COLOR = Color.parseColor("#33ffffff");
    private static final float DEFAULT_DECOR_RING_WEIGHT = 0.2f;
    private static final float DEFAULT_INNER_HOLE_WEIGHT = 0.28f;

    private float textSize;
    private float iconSize;
    private float decorRingWeight = DEFAULT_DECOR_RING_WEIGHT;
    private float innerHoleWeight = DEFAULT_INNER_HOLE_WEIGHT;
    @ColorInt
    private int decorRingColor = DEFAULT_DECOR_RING_COLOR;
    @ColorInt
    private int textColor = DEFAULT_TEXT_COLOR;

    private List<Slice> slices;
    private float chartRadius;
    private RectF contentBounds;
    private RectF innerHoleBounds;

    private Paint slicePaint;
    private Path slicePath;
    private Paint ringPaint;
    private Paint textPaint;

    public PieChartt(Context context) {
        super(context);
        initDefaultAttrs();
        init();
    }

    public PieChartt(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        init();
    }

    public PieChartt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawSlices(canvas);
        drawPercentageValues(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        chartRadius = Math.min(
                w - getPaddingLeft() - getPaddingRight(),
                h - getPaddingTop() - getPaddingBottom()) / 2f;
    }

    private void initDefaultAttrs() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        textSize = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE_SP, dm);
        iconSize = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, DEFAULT_ICON_SIZE_DP, dm);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray array = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PieChart,
                0, 0);
        try {
            DisplayMetrics dm = getResources().getDisplayMetrics();
            textSize = array.getDimensionPixelSize(
                    R.styleable.PieChart_textSize,
                    (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE_SP, dm));
            textColor = array.getColor(R.styleable.PieChart_textColor, DEFAULT_TEXT_COLOR);
            iconSize = array.getDimensionPixelSize(
                    R.styleable.PieChart_iconSize,
                    (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, DEFAULT_ICON_SIZE_DP, dm));
            innerHoleWeight = array.getFloat(
                    R.styleable.PieChart_innerHoleWeight, DEFAULT_INNER_HOLE_WEIGHT);
        } finally {
            array.recycle();
        }
    }

    private void init() {
        slices = new ArrayList<>();
        contentBounds = new RectF();
        innerHoleBounds = new RectF();

        slicePaint = new Paint();
        slicePaint.setAntiAlias(true);
        slicePaint.setStyle(Paint.Style.FILL);

        ringPaint = new Paint();
        ringPaint.setAntiAlias(true);
        ringPaint.setStyle(Paint.Style.FILL);
        ringPaint.setColor(decorRingColor);
        ringPaint.setAlpha(Color.alpha(decorRingColor));

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setAlpha(Color.alpha(textColor));
        textPaint.setTextSize(textSize);
        slicePath = new Path();
    }

    private void drawSlices(Canvas canvas) {
        float sliceStartAngle = START_ANGLE_OFFSET;
        getCenteredSquareBounds(chartRadius * 2, contentBounds);
        getCenteredSquareBounds((chartRadius * innerHoleWeight) * 2, innerHoleBounds);

        for (Slice slice : slices) {
            slicePath = PathUtils.getSolidArcPath(
                    slicePath, contentBounds, innerHoleBounds,
                    sliceStartAngle, getSliceAngle(slice));
            slicePaint.setColor(slice.getColor());
            canvas.drawPath(slicePath, slicePaint);

            sliceStartAngle += getSliceAngle(slice);
        }
    }

    private void drawPercentageValues(Canvas canvas) {
        float sliceStartAngle = START_ANGLE_OFFSET;
        float iconDistance = chartRadius * innerHoleWeight
                + chartRadius * (1 - decorRingWeight - innerHoleWeight) / 2f;
        float sliceHalfAngle, iconCenterX, iconCenterY;

        for (Slice slice : slices) {
            sliceHalfAngle = sliceStartAngle + getSliceAngle(slice) / 2f;
            iconCenterX = MathUtils.getPointX(
                    contentBounds.centerX(), iconDistance, sliceHalfAngle);
            iconCenterY = MathUtils.getPointY(
                    contentBounds.centerY(), iconDistance, sliceHalfAngle);
            String text = getSlicePercentagesString(slice);
            Rect bounds = new Rect();
            textPaint.getTextBounds(text, 0, text.length(), bounds);

            canvas.drawText(
                    text,
                    iconCenterX - bounds.width() / 2f,
                    iconCenterY + bounds.height() / 2f,
                    textPaint);

            sliceStartAngle += getSliceAngle(slice);
        }
    }

    protected RectF getCenteredSquareBounds(float squareSize, RectF bounds) {
        bounds.left = getPaddingLeft()
                + (getWidth() - getPaddingLeft() - getPaddingRight() - squareSize) / 2;
        bounds.top = getPaddingTop()
                + (getHeight() - getPaddingTop() - getPaddingBottom() - squareSize) / 2;
        bounds.right = getWidth() - getPaddingRight()
                - (getWidth() - getPaddingLeft() - getPaddingRight() - squareSize) / 2;
        bounds.bottom = getHeight() - getPaddingBottom()
                - (getHeight() - getPaddingTop() - getPaddingBottom() - squareSize) / 2;
        return bounds;
    }

    public void addSlice(Slice slice) {
        slices.add(slice);
        invalidate();
    }

    public void setSlices(List<Slice> slice) {
        slices.clear();
        slices.addAll(slice);
        invalidate();
    }

    public String getSlicePercentagesString(Slice slice) {
        return Math.round(slice.getValue() / getTotal() * 100) + "%";
    }

    private float getSliceAngle(Slice slice) {
        return slice.getValue() / getTotal() * 360;
    }

    public float getTotal() {
        float total = 0;
        for (Slice slice : slices) {
            total += slice.getValue();
        }
        return total;
    }

    public static class Slice {

        @ColorInt
        private int color;
        private float value;

        public Slice(@ColorInt int color, float value) {
            this.color = color;
            this.value = value;
        }

        @ColorInt
        public int getColor() {
            return color;
        }

        public float getValue() {
            return value;
        }
    }
}