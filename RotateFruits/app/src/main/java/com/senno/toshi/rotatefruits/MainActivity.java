/**
 * Author: Kimitoshi Senno
 * Date: 	Sep 15, 2014
 * For:	CS311D Homework 3
 * File:	AndAdv/app/src/main/java/com/senno/toshi/RotateFruits/MainActivity.java
 * Purpose:Main Activity for the application
 */
package com.senno.toshi.rotatefruits;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends Activity implements View.OnClickListener
{

    private Button buttonApple;
    private Button buttonGrape;
    private Button buttonKiwi;
    private Button buttonOrange;
    private Button buttonBanana;
    private TextView myTV;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonApple = (Button) findViewById(R.id.buttonApple);
        buttonGrape = (Button) findViewById(R.id.buttonGrape);
        buttonKiwi = (Button) findViewById(R.id.buttonKiwi);
        buttonOrange = (Button) findViewById(R.id.buttonOrange);
        buttonBanana = (Button) findViewById(R.id.buttonBanana);

        myTV = (TextView) findViewById(R.id.myTextView);

        buttonApple.setOnClickListener(this);
        buttonGrape.setOnClickListener(this);
        buttonKiwi.setOnClickListener(this);
        buttonOrange.setOnClickListener(this);
        buttonBanana.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        String tvText = null;
        int tvColor = 0;

        switch (v.getId())
        {
            case R.id.buttonApple:
                tvText = "Apples come from Washington";
                tvColor = Color.RED;
                break;
            case R.id.buttonGrape:
                tvText = "Grapes come from California";
                tvColor = Color.BLUE;
                break;
            case R.id.buttonKiwi:
                tvText = "Kiwis come from New Zealand";
                tvColor = Color.GREEN;
                break;
            case R.id.buttonOrange:
                tvText = "Oranges come from Florida";
                tvColor = Color.MAGENTA;
                break;
            case R.id.buttonBanana:
                tvText = "Bananas come from Hawaii";
                tvColor = Color.YELLOW;
                break;
            default:
                tvText = "Nothing comes from nothing";
                tvColor = Color.BLACK;
                break;
        }

        myTV.setTextColor(tvColor);
        myTV.setText(tvText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
