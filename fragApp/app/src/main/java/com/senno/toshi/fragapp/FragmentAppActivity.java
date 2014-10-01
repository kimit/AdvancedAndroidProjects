/**
 * Author:  Kimitoshi Senno
 * Date: 	Sep 30, 2014
 * For:	    CS311D Homework 3
 * File:	fragApp/app/src/main/java/com/senno/toshi/fragapp/FragmentAppActivity.java
 * Purpose: Main Activity for Application
 */

package com.senno.toshi.fragapp;

import android.app.Activity;
import android.os.Bundle;


public class FragmentAppActivity extends Activity
{

    @Override
    protected void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.activity_fragments_app);
    }
}
