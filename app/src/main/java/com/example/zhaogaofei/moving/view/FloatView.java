package com.example.zhaogaofei.moving.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.zhaogaofei.moving.Utils;

/**
 * 需求：
 * 布局为圆形
 * 可跟着手的滑动移动
 * <p>
 * 进一步需求：
 * 根据手的滑动在屏幕上移动
 * 根据手的滑动在屏幕上移动，自动停靠在屏幕的边缘
 */
public class FloatView extends View implements View.OnTouchListener {
    private static final int CIRCLE_RADIUS = 100;

    private Paint mCirclePaint;
    private Paint mRectPaint;

    private float mX = 100;
    private float mY = 100;

    private int mStatusBarHeight;
    private int mScreenWidth;

    public FloatView(Context context) {
        super(context);
        init();
    }

    public FloatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FloatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.RED);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setStrokeWidth(50);
        mCirclePaint.setAntiAlias(true);

        mRectPaint = new Paint();
        mRectPaint.setColor(Color.BLUE);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setAntiAlias(true);

        setOnTouchListener(this);

        mStatusBarHeight = Utils.getStatusBarHeight(getContext());
        int[] screenWidthAndHeight = Utils.getScreenWidthAndHeight(getContext());
        mScreenWidth = screenWidthAndHeight[0];
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(CIRCLE_RADIUS * 2, CIRCLE_RADIUS * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        Log.e("zgf", "view width is: " + width + " view height is: " + height);
        canvas.drawRect(0, 0, width, height, mRectPaint);

        canvas.drawCircle(mX, mY, CIRCLE_RADIUS, mCirclePaint);
    }

    public void updateUi(float xPosition, float yPosition) {
        mX = xPosition;
        mY = yPosition - mStatusBarHeight;
        requestLayout();
        invalidate();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                updateUi(event.getRawX(), event.getRawY());
                break;
            case MotionEvent.ACTION_UP:
                float endX = event.getRawX();
                float endY = event.getRawY();
                if (endX < mScreenWidth / 2) {
                    endX = CIRCLE_RADIUS;
                } else {
                    endX = mScreenWidth - CIRCLE_RADIUS;
                }
                updateUi(endX, endY);
                break;
        }
        return true;
    }

}
