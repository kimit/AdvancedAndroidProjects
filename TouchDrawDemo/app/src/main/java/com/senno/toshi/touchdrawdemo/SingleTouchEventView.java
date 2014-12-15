/**
 * Author:  Kimitoshi Senno
 * Date: 	Dec 14, 2014
 * For:	    CS311D Homework Extra Credit
 * File:	TouchDrawDemo/app/src/main/java/com/senno/toshi/touchdrawdemo/SingleTouchEventView.java
 * Purpose: Main Activity for Application
 */

package com.senno.toshi.touchdrawdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SingleTouchEventView extends View
{
    private Paint p = new Paint();
    private Path pt = new Path();

    //**********************SingleTouchEventView()*****************************
    public SingleTouchEventView(Context con, AttributeSet attrs)
    {
        super(con, attrs);
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeJoin(Paint.Join.ROUND);
        // stroke color and width is set from the activity
    }

    //********************************onDraw()*********************************
    @Override
    protected void onDraw (Canvas c)
    {
        c.drawPath(pt, p);
    }

    //********************************onTouchEvent()***************************
    @Override
    public boolean onTouchEvent(MotionEvent me)
    {
        float x = me.getX();
        float y = me.getY();

        switch (me.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                pt.moveTo(x, y);
                return true;

            case MotionEvent.ACTION_MOVE:
                pt.lineTo(x,y);
                break;

            case MotionEvent.ACTION_UP:
                break;

            default:
                return false;
        }

        invalidate();

        return (false);
    }

    //*****************************setStrokeWidthF()***************************
    public void setStrokeWidthF(float sw)
    {
        p.setStrokeWidth(sw);
    }

    //**************************setStrokeColor()*******************************
    public void setStrokeColor(int c)
    {
        p.setColor(c);
    }
}
