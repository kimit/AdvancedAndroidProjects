/**
 * Author:  Kimitoshi Senno
 * Date: 	Sep 30, 2014
 * For:	    CS311D Homework 3
 * File:	fragApp/app/src/main/java/com/senno/toshi/fragapp/ShowItemActivity.java
 * Purpose: Activity for Portrait and Landscape switching
 */

package com.senno.toshi.fragapp;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class ShowItemActivity extends Activity
{
    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        if(isLandScape())
        {
            finish();
            return;
        }
        setContentView(R.layout.frag2);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            String selectedItem = extras.getString("item");

            TextView tv = (TextView) findViewById(R.id.selected_option);
            tv.setText(selectedItem + "\n\n To continue, please go back");
        }
    }

    public  boolean isLandScape()
    {
        Resources r = getResources();
        DisplayMetrics d = r.getDisplayMetrics();
        return (d.widthPixels > d.heightPixels);
    }
}
