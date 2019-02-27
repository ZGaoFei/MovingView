package com.example.zhaogaofei.moving.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.zhaogaofei.moving.Utils;

public class FloatBallView extends View implements View.OnTouchListener {
    public static final int CIRCLE_RADIUS = 100;

    public int mWidth = CIRCLE_RADIUS;
    public int mHeight = CIRCLE_RADIUS;

    private Paint mCirclePaint;
    private Paint mRectPaint;

    private float startX;
    private float startY;
    private float tempX;
    private float tempY;

    private int mScreenWidth;

    private WindowManager windowManager;

    private WindowManager.LayoutParams floatBallParams;

    public FloatBallView(Context context) {
        super(context);
        init(context);
    }

    public FloatBallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FloatBallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        int[] screenWidthAndHeight = Utils.getScreenWidthAndHeight(context);
        mScreenWidth = screenWidthAndHeight[0];

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

        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        floatBallParams = new WindowManager.LayoutParams();
        floatBallParams.width = mWidth;
        floatBallParams.height = mHeight - Utils.getStatusBarHeight(context);
        floatBallParams.gravity = Gravity.TOP | Gravity.LEFT;
        floatBallParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        floatBallParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        floatBallParams.format = PixelFormat.RGBA_8888;

        windowManager.addView(this, floatBallParams);
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

        canvas.drawCircle(CIRCLE_RADIUS, CIRCLE_RADIUS, CIRCLE_RADIUS, mCirclePaint);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getRawX();
                startY = event.getRawY();

                tempX = event.getRawX();
                tempY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getRawX() - startX;
                float y = event.getRawY() - startY;
                //计算偏移量，刷新视图
                floatBallParams.x += x;
                floatBallParams.y += y;
                windowManager.updateViewLayout(this, floatBallParams);
                startX = event.getRawX();
                startY = event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                //判断松手时View的横坐标是靠近屏幕哪一侧，将View移动到依靠屏幕
                float endX = event.getRawX();
                float endY = event.getRawY();
                if (endX < mScreenWidth / 2) {
                    endX = 0;
                } else {
                    endX = mScreenWidth - mWidth;
                }
                floatBallParams.x = (int) endX;
                windowManager.updateViewLayout(this, floatBallParams);
                //如果初始落点与松手落点的坐标差值超过6个像素，则拦截该点击事件
                //否则继续传递，将事件交给OnClickListener函数处理
                if (Math.abs(endX - tempX) > 6 && Math.abs(endY - tempY) > 6) {
                    return true;
                }
                break;
        }
        return false;
    }
}
