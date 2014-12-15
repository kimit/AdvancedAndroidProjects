/**
 * Author:  Kimitoshi Senno
 * Date: 	Dec 14, 2014
 * For:	    CS311D Homework Extra Credit
 * File:	TouchDrawDemo/app/src/main/java/com/senno/toshi/touchdrawdemo/TouchDrawDemoActivity.java
 * Purpose: Main Activity for Application
 */

package com.senno.toshi.touchdrawdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;


public class TouchDrawDemoActivity extends Activity {
    private Button colorBtn;
    private Button clearBtn;
    private int strokeColor = Color.BLACK;
    private SingleTouchEventView myTouchView;
    private EditText strokeET;
    private String strokeStr = "6";
    private float strokeW;
    private final String MSG = "Stroke Width is set to the default, 6.";

    //********************************onCreate()*******************************
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_draw_demo);
        //setContentView(new SingleTouchEventView(this,null));

        clearBtn = (Button) findViewById(R.id.clearBtn);
        colorBtn = (Button) findViewById(R.id.colorBtn);
        strokeET = (EditText) findViewById(R.id.strokeET);
        myTouchView = (SingleTouchEventView) findViewById(R.id.myCanvas);

        strokeET.setText(strokeStr);
        setStrokeWidth(strokeStr);
        setColor();

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_touch_draw_demo);
                onCreate(savedInstanceState);
            }
        });

        strokeET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
            }
            @Override
            public void afterTextChanged(Editable s) {
                strokeStr = strokeET.getText().toString();
                setStrokeWidth(strokeStr);
            }
        });

        colorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getBaseContext(), colorBtn);
                getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        switch (item.getItemId())
                        {
                            case R.id.black:
                                strokeColor = Color.BLACK;
                                break;
                            case R.id.red:
                                strokeColor = Color.RED;
                                break;
                            case R.id.blue:
                                strokeColor = Color.BLUE;
                                break;
                            default:
                                strokeColor = Color.YELLOW;
                        }
                        setColor();
                        return true;
                    }

                });
                popup.show();
            }
        });
    }

    //******************************setStrokeWidth()***************************
    private void setStrokeWidth(String strokeString)
    {
        if(strokeET.getText().toString().isEmpty()) {
            strokeW = 6f;
            Toast.makeText(getBaseContext(), "Set to default value 6", Toast.LENGTH_SHORT).show();
        }
        else
        {
            strokeW = Float.parseFloat(strokeString);
        }

        myTouchView.setStrokeWidthF(strokeW);
    }

    //******************************setColor()*********************************
    private void setColor()
    {
        myTouchView.setStrokeColor(strokeColor);
    }
}