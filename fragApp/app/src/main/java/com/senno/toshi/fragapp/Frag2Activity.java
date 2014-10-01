/**
 * Author:  Kimitoshi Senno
 * Date: 	Sep 30, 2014
 * For:	    CS311D Homework 3
 * File:	fragApp/app/src/main/java/com/senno/toshi/fragapp/Frag2Activity.java
 * Purpose: Activity for Fragment 2
 */

package com.senno.toshi.fragapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Frag2Activity extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b)
    {
        return (li.inflate(R.layout.frag2, vg, false));
    }
}
