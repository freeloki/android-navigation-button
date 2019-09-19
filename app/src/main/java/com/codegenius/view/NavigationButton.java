package com.codegenius.view;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class NavigationButton extends View implements View.OnTouchListener {
    private static final String TAG = NavigationButton.class.getSimpleName();

    class Defaults {
        public static final int INNER_CIRCLE_COLOR = Color.DKGRAY;
        public static final int OUTER_CIRCLE_COLOR = Color.GRAY;
        public static final int INNER_CIRCLE_RADIUS = 75;
        public static final int OUTER_CIRCLE_RADIUS = 200;
        public static final int TRANSPARENCY = 100;
        public static final int DOT_RADIUS = 10;
        public static final int DOT_COLOR = Color.RED;
        public static final int LINE_COLOR = Color.BLACK;
        public static final int LINE_STROKE = 10;
        public static final boolean CLICK_ANIM = true;
    }

    class Region {
        public static final int UP = 100;
        public static final int DOWN = 101;
        public static final int RIGHT = 102;
        public static final int LEFT = 103;
        public static final int STOP = 104;
    }

    private int innerCircleRadius;
    private int outerCircleRadius;
    private int innerCircleTransparency;
    private int outerCircleTransparency;
    private int innerDotRadius;
    private int outerDotsRadius;
    private int innerDotColor;
    private int outerDotsColor;
    private int dotTransparency;
    private int innerCircleColor;
    private int outerCircleColor;
    private int lineColor;
    private int lineTransparency;
    private int lineStroke;
    private boolean clickAnimation;
    private Rect top, bottom, right, left, center;
    private Canvas mCanvas;
    private Paint linePaint, innerCirclePaint, outerCirclePaint;
    private NavigationButtonListener mNavigationButtonListener;

    public NavigationButton(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);


        TypedArray mAttributeArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.NavigationButton, 0, 0);
        try {

            innerCircleColor = mAttributeArray.getColor(R.styleable.NavigationButton_innerCircleColor, Defaults.INNER_CIRCLE_COLOR);
            outerCircleColor = mAttributeArray.getColor(R.styleable.NavigationButton_outerCircleColor, Defaults.OUTER_CIRCLE_COLOR);
            innerCircleRadius = mAttributeArray.getInteger(R.styleable.NavigationButton_innerCircleRadius, Defaults.INNER_CIRCLE_RADIUS);
            outerCircleRadius = mAttributeArray.getInteger(R.styleable.NavigationButton_outerCircleRadius, Defaults.OUTER_CIRCLE_RADIUS);
            innerCircleTransparency = mAttributeArray.getInteger(R.styleable.NavigationButton_innerCircleTransparency, Defaults.TRANSPARENCY);
            outerCircleTransparency = mAttributeArray.getInteger(R.styleable.NavigationButton_outerCircleTransparency, Defaults.TRANSPARENCY);
            innerDotRadius = mAttributeArray.getInteger(R.styleable.NavigationButton_innerDotRadius, Defaults.DOT_RADIUS);
            outerDotsRadius = mAttributeArray.getInteger(R.styleable.NavigationButton_outerDotsRadius, Defaults.DOT_RADIUS);
            innerDotColor = mAttributeArray.getInteger(R.styleable.NavigationButton_innerDotColor, Defaults.DOT_COLOR);
            dotTransparency = mAttributeArray.getInteger(R.styleable.NavigationButton_dotTransparency, Defaults.TRANSPARENCY);
            outerDotsColor = mAttributeArray.getInteger(R.styleable.NavigationButton_outerDotsColor, Defaults.DOT_COLOR);
            lineColor = mAttributeArray.getInteger(R.styleable.NavigationButton_lineColor, Defaults.LINE_COLOR);
            lineStroke = mAttributeArray.getInteger(R.styleable.NavigationButton_lineStroke, Defaults.LINE_STROKE);
            lineTransparency = mAttributeArray.getInteger(R.styleable.NavigationButton_lineTransparency, Defaults.TRANSPARENCY);
            clickAnimation = mAttributeArray.getBoolean(R.styleable.NavigationButton_clickAnimation, Defaults.CLICK_ANIM);


        } finally {
            mAttributeArray.recycle();
        }

        setLongClickable(true);
        setOnTouchListener(this);


    }

    public int getInnerCircleRadius() {
        return innerCircleRadius;
    }

    public void setInnerCircleRadius(int innerCircleRadius) {
        this.innerCircleRadius = innerCircleRadius;
    }

    public int getOuterCircleRadius() {
        return outerCircleRadius;
    }

    public void setOuterCircleRadius(int outerCircleRadius) {
        this.outerCircleRadius = outerCircleRadius;
    }

    public int getInnerCircleTransparency() {
        return innerCircleTransparency;
    }

    public void setInnerCircleTransparency(int innerCircleTransparency) {
        this.innerCircleTransparency = innerCircleTransparency;
    }

    public int getOuterCircleTransparency() {
        return outerCircleTransparency;
    }

    public void setOuterCircleTransparency(int outerCircleTransparency) {
        this.outerCircleTransparency = outerCircleTransparency;
    }

    public int getInnerDotRadius() {
        return innerDotRadius;
    }

    public void setInnerDotRadius(int innerDotRadius) {
        this.innerDotRadius = innerDotRadius;
    }

    public int getOuterDotsRadius() {
        return outerDotsRadius;
    }

    public void setOuterDotsRadius(int outerDotsRadius) {
        this.outerDotsRadius = outerDotsRadius;
    }

    public int getInnerDotColor() {
        return innerDotColor;
    }

    public void setInnerDotColor(int innerDotColor) {
        this.innerDotColor = innerDotColor;
    }

    public int getOuterDotsColor() {
        return outerDotsColor;
    }

    public void setOuterDotsColor(int outerDotsColor) {
        this.outerDotsColor = outerDotsColor;
    }

    public int getDotTransparency() {
        return dotTransparency;
    }

    public void setDotTransparency(int dotTransparency) {
        this.dotTransparency = dotTransparency;
    }

    public int getInnerCircleColor() {
        return innerCircleColor;
    }

    public void setInnerCircleColor(int innerCircleColor) {
        this.innerCircleColor = innerCircleColor;
    }

    public int getOuterCircleColor() {
        return outerCircleColor;
    }

    public void setOuterCircleColor(int outerCircleColor) {
        this.outerCircleColor = outerCircleColor;
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public int getLineTransparency() {
        return lineTransparency;
    }

    public void setLineTransparency(int lineTransparency) {
        this.lineTransparency = lineTransparency;
    }

    public int getLineStroke() {
        return lineStroke;
    }

    public void setLineStroke(int lineStroke) {
        this.lineStroke = lineStroke;
    }

    public Paint getLinePaint() {
        return linePaint;
    }

    public void setLinePaint(Paint linePaint) {
        this.linePaint = linePaint;
    }

    public Paint getInnerCirclePaint() {
        return innerCirclePaint;
    }

    public void setInnerCirclePaint(Paint innerCirclePaint) {
        this.innerCirclePaint = innerCirclePaint;
    }

    public Paint getOuterCirclePaint() {
        return outerCirclePaint;
    }

    public void setOuterCirclePaint(Paint outerCirclePaint) {
        this.outerCirclePaint = outerCirclePaint;
    }

    public NavigationButtonListener getNavigationButtonListener() {
        return mNavigationButtonListener;
    }

    public void setNavigationButtonListener(NavigationButtonListener mNavigationButtonListener) {
        this.mNavigationButtonListener = mNavigationButtonListener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        Paint mOuterCirclePaint = new Paint();
        mOuterCirclePaint.setColor(outerCircleColor);
        mOuterCirclePaint.setAlpha(outerCircleTransparency);
        //Log.e("Test", "onDraw:\nWidth: " + getMeasuredWidth() + "\nHeight: " + this.getMeasuredHeight());

        int cx = this.getMeasuredWidth() / 2;
        int cy = this.getMeasuredHeight() / 2;

        //Log.e("Test", "\nCX:" + cx + "\nCY:" + cy);

        canvas.drawCircle(cx, cy, outerCircleRadius, mOuterCirclePaint);

        Paint innerCirclePaint = new Paint();
        innerCirclePaint.setColor(innerCircleColor);

        //Path mPath = new Path();

        innerCirclePaint.setAlpha(innerCircleTransparency);
        canvas.drawCircle(cx, cy, innerCircleRadius, innerCirclePaint);


        int left_x = cx - outerCircleRadius;
        int left_y = cy;
        int right_x = cx + outerCircleRadius;
        int right_y = cy;

        int top_x = cx;
        int top_y = cy - outerCircleRadius;

        int bottom_x = cx;
        int bottom_y = cy + outerCircleRadius;
        canvas.rotate(45, cx, cy);
        Paint linePaint = new Paint();

        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(lineStroke);
        linePaint.setAlpha(lineTransparency);
        canvas.drawLine(cx - innerCircleRadius, cy, left_x, left_y, linePaint);
        canvas.drawLine(cx + innerCircleRadius, cy, right_x, right_y, linePaint);
        canvas.drawLine(cx, cy - innerCircleRadius, top_x, top_y, linePaint);
        canvas.drawLine(cx, cy + innerCircleRadius, bottom_x, bottom_y, linePaint);

        int offset = (outerCircleRadius - innerCircleRadius) / 2 + innerCircleRadius;
        canvas.rotate(315, cx, cy);
        int top_region_cx = cx;
        int top_region_cy = cy - offset;
        int bottom_region_cx = cx;
        int bottom_region_cy = cy + offset;
        int left_region_cx = cx - offset;
        int left_region_cy = cy;
        int right_region_cx = cx + offset;
        int right_region_cy = cy;
        //int center_region_cx = cx;
        //int up_region_cy = cy + offset;
        canvas.drawCircle(left_region_cx, left_region_cy, outerDotsRadius, linePaint);
        canvas.drawCircle(bottom_region_cx, bottom_region_cy, outerDotsRadius, linePaint);
        canvas.drawCircle(top_region_cx, top_region_cy, outerDotsRadius, linePaint);
        canvas.drawCircle(right_region_cx, right_region_cy, outerDotsRadius, linePaint);

        Paint innerDotPaint = new Paint();
        innerDotPaint.setAlpha(dotTransparency);
        innerDotPaint.setColor(innerDotColor);
        //linePaint.setAlpha(90);
        canvas.drawCircle(cx, cy, innerDotRadius, innerDotPaint);

        // Rect r = new Rect()

        int region_big_offset = 2 * ((outerCircleRadius - innerCircleRadius) / 2);
        int region_small_offset = region_big_offset / 2;
        double param_scale = innerCircleRadius * 0.75;
        int paramcikint = (int) param_scale;
        top = new Rect(top_region_cx - region_big_offset, top_region_cy - region_small_offset, top_region_cx + region_big_offset, top_region_cy + region_small_offset);
        bottom = new Rect(bottom_region_cx - region_big_offset, bottom_region_cy - region_small_offset, bottom_region_cx + region_big_offset, bottom_region_cy + region_small_offset);
        left = new Rect(left_region_cx - region_small_offset, left_region_cy - region_big_offset, left_region_cx + region_small_offset, left_region_cy + region_big_offset);
        right = new Rect(right_region_cx - region_small_offset, right_region_cy - region_big_offset, right_region_cx + region_small_offset, right_region_cy + region_big_offset);
        center = new Rect(cx - paramcikint, cy - paramcikint, cx + paramcikint, cy + paramcikint);

        this.mCanvas = canvas;


    }

    public int checkRegion(int x, int y) {

        Log.e("Test", "CHECKING... " + "\n" + x + "\n" + y);

        if (top.contains(x, y)) {
            Log.e("Test", "checkRegion: TOP:");
            return Region.UP;
        } else if (bottom.contains(x, y)) {
            Log.e("Test", "checkRegion: BOTTOM:");
            return Region.DOWN;
        } else if (left.contains(x, y)) {
            Log.e("Test", "checkRegion: LEFT:");
            return Region.LEFT;
        } else if (right.contains(x, y)) {
            Log.e("Test", "checkRegion: RIGHT:");
            return Region.RIGHT;
        } else if (center.contains(x, y)) {
            Log.e("Test", "checkRegion: CENTER:");
            return Region.STOP;
        }
        return -1;

    }

    public interface NavigationButtonListener {
        void onUpPressed();

        void onUpReleased();

        void onDownPressed();

        void onDownReleased();

        void onLeftPressed();

        void onLeftReleased();

        void onRightPressed();

        void onRightReleased();

        void onStopPressed();

        void onStopReleased();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {


        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.e("TEST", "onTouch DOWN: " + event.getAction() + "\n" + mNavigationButtonListener);
            if (mNavigationButtonListener == null) {
                Log.e(TAG, "onTouch: LISTENER NULL");
            }
            switch (checkRegion((int) event.getX(), (int) event.getY())) {

                case Region.UP:
                    if (mNavigationButtonListener != null) {
                        Log.e(TAG, "onTouch:???");
                        mNavigationButtonListener.onUpPressed();

                    }
                    break;
                case Region.DOWN:
                    if (mNavigationButtonListener != null) {
                        mNavigationButtonListener.onDownPressed();
                    }
                    break;
                case Region.LEFT:
                    if (mNavigationButtonListener != null) {
                        mNavigationButtonListener.onLeftPressed();
                    }
                    break;
                case Region.RIGHT:
                    if (mNavigationButtonListener != null) {
                        mNavigationButtonListener.onRightPressed();
                    }
                    break;
                case Region.STOP:
                    if (mNavigationButtonListener != null) {
                        mNavigationButtonListener.onStopPressed();
                    }
                    break;

                default:
                    break;
            }

        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.e("TEST", "onTouch UP: " + event.getAction());
            switch (checkRegion((int) event.getX(), (int) event.getY())) {

                case Region.UP:
                    if (mNavigationButtonListener != null) {
                        mNavigationButtonListener.onUpReleased();
                    }
                    break;
                case Region.DOWN:
                    if (mNavigationButtonListener != null) {
                        mNavigationButtonListener.onDownReleased();
                    }
                    break;
                case Region.LEFT:
                    if (mNavigationButtonListener != null) {
                        mNavigationButtonListener.onLeftReleased();
                    }
                    break;
                case Region.RIGHT:
                    if (mNavigationButtonListener != null) {
                        mNavigationButtonListener.onRightReleased();
                    }
                    break;
                case Region.STOP:
                    if (mNavigationButtonListener != null) {
                        mNavigationButtonListener.onStopReleased();
                    }
                    break;
                default:
                    break;
            }

        }

        return true;
    }
}
