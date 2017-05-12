package com.example.liaohaicongsx.progressdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by liaohaicongsx on 2017/05/11.
 */
public class CircleProgressView extends View {

    private Paint mPaint;
    private RectF mRectf;
    private Timer mTimer;

    private int startAngel = -90;
    private int count = 0;

    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(android.R.color.holo_red_light));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        mPaint.setStrokeWidth(12);
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                startAngel = startAngel  + 45;
                postInvalidate();  //调用该方法之后会重新调用onDraw的方法进行重绘
                if(count >= 100){
                    this.cancel();   //关闭该定时任务
                }
                count ++;
            }
        }, 200, 200);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mRectf = new RectF(100, 100, 200, 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(mRectf, startAngel, 225, false, mPaint);
    }
}
