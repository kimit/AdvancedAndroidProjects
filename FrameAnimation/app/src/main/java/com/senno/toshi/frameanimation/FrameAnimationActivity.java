/**
 * Author:  Kimitoshi Senno
 * Date: 	Dec 6, 2014
 * For:	    CS311D Homework 6
 * File:	FrameAnimation/app/src/main/java/com/senno/toshi/frameanimation/FrameAnimationActivity.java
 * Purpose: Main Activity for Application
 */

package com.senno.toshi.frameanimation;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.ToggleButton;


public class FrameAnimationActivity extends Activity {

    private ImageView iv;
    private AnimationDrawable ad;
    private ToggleButton tglBtn;
    private SeekBar speedBar;
    private int animSpeed;
    private int frameNumber;
    private int[] drawableArray;

    //********************************onCreate()*******************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);
        iv = (ImageView) findViewById(R.id.imgview);
        tglBtn = (ToggleButton) findViewById(R.id.toggleBtn);
        speedBar = (SeekBar) findViewById(R.id.speedSld);
        animSpeed = speedBar.getProgress();
        drawableArray = new int[]{R.drawable.c01,
                                  R.drawable.c02,
                R.drawable.c03,
                R.drawable.c04,
                R.drawable.c05,
                R.drawable.c06,
                R.drawable.c07,
                R.drawable.c08,
                R.drawable.c09,
                R.drawable.c10
        };

        setFrameNum();

        createAnimation(frameNumber, animSpeed);

        tglBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!
                        tglBtn.isChecked())
                {
                    animSpeed = speedBar.getProgress();
                    Log.d(String.valueOf(animSpeed), "aminSpeed" );

                    setFrameNum();
                    createAnimation(frameNumber, animSpeed);
                    ad.start();
                }
                else
                {
                    ad.stop();
                }
            }
        });
    }

    //****************************createAnimation()****************************
    private void createAnimation(int frames, int speed)
    {
        ad = new AnimationDrawable();

        for(int i = 0; i < frames; i++)
        {
          ad.addFrame(getResources().getDrawable(drawableArray[i]), (100-speed)*2 );
        }

        ad.setOneShot(false);

        iv.setBackground(ad);
    }

    //*****************************isLandscape()*******************************
    private boolean isLandscape()
    {
        Resources r = getResources();
        DisplayMetrics d = r.getDisplayMetrics();
        return (d.widthPixels > d.heightPixels);
    }

    //*****************************setFrameNum()*******************************
    private void setFrameNum()
    {
        frameNumber = 6;
        if (isLandscape())
        {
            frameNumber = 10;
        }
    }
}
