/**
 * Author:  Kimitoshi Senno
 * Date: 	Oct 26, 2014
 * For:	    CS311D Homework 4
 * File:	PrimeNumService/app/src/main/java/com/senno/toshi/primenumservice/PrimeNumService.java
 * Purpose: Main Activity for Application
 */

package com.senno.toshi.primenumservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
//import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;

public class PrimeNumService extends Activity
{
    private Intent i;

    private Button btnClick;
    private Button btnStop;
    private EditText etStart;
    private EditText etEnd;
    private EditText etNumber;
    private EditText etCode;

    private MyBinderService mbs;
    private boolean isBound = false;

    public ServiceConnection con = new ServiceConnection()
    {
        /**
         * Called connection to the Service.
         *
         * @param name The concrete component name of the service.
         *
         * @param service The IBinder of the Service's communication channel.
         */
        @Override
        public void onServiceConnected(ComponentName cn, IBinder ib)
        {
            mbs = ((MyBinderService.MyBinder)ib).getService();
            isBound = true;
        }

        /**
         * Called when a connection to the Service has been lost.
         *
         * @param name The concrete component name of the service.
         */
        @Override
        public void onServiceDisconnected(ComponentName cn)
        {
            isBound = false;
        }
    };

    /**
     * Called when this client stops appearing visually
     *
     * @return Return nothing.
     */
    @Override
    public void onStop()
    {
        super.onStop();

        if(isBound)
        {
            unbindService(con);
            isBound = false;
        }
    }

    /**
     * Called when this client starts appearing visually
     *
     * @return Return nothing.
     */
    @Override
    protected void onStart()
    {
        super.onStart();
        bindService(i, con, Context.BIND_AUTO_CREATE);
    }

    /**
     * Called when this client is created
     *
     * @param b This Bundle contains the saved state
     */
    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.activity_prime_num_service);

        btnClick = (Button) findViewById(R.id.btn_click);
        btnStop = (Button) findViewById(R.id.btn_stop);
        etStart = (EditText) findViewById(R.id.et_start);
        etEnd = (EditText) findViewById(R.id.et_end);
        etNumber = (EditText) findViewById(R.id.et_number);
        etCode = (EditText) findViewById(R.id.et_code);

        // Create Intent to start service
        i = new Intent(getBaseContext(), MyBinderService.class);

        /*
        // For future work of passing secret in Bundle to Service
        Bundle nb = new Bundle();
        nb.putString("secret", "kill");
        i.putExtras(nb);
        */

        // Action for Click button
        btnClick.setOnClickListener(new Button.OnClickListener()
        {
            /**
             * Called when Click button has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v)
            {
                // StringBuilder for a message in Toast
                StringBuilder sb = new StringBuilder();

                String txtEtStart = etStart.getText().toString();
                String txtEtEnd = etEnd.getText().toString();
                String txtEtNum = etNumber.getText().toString();

                // Basic check if input fields are empty
                if(txtEtStart.isEmpty() ||
                   txtEtEnd.isEmpty() ||
                   txtEtNum.isEmpty())
                {
                    sb.append("Please fill all fields.");
                }
                else
                {
                    int iStart = Integer.parseInt(txtEtStart);
                    int iEnd = Integer.parseInt(txtEtEnd);
                    int iNum = Integer.parseInt(txtEtNum);

                    // If service is connected, call its method
                    // to generate prime numbers
                    if (isBound)
                    {
                        List<Integer> listPN = mbs.primeNumber(iStart, iEnd, iNum);

                        if(listPN.isEmpty())
                        {
                            sb.append("No result is found");
                        }
                        else
                        {
                            for (Integer s : listPN)
                            {
                                sb.append(s);
                                sb.append(" ");
                            }
                        }
                    }
                    else
                    {
                        sb.append("Service is not connected.");
                    }
                }

                // Display the message in Toast
                Toast.makeText(getBaseContext(),
                               "Prime Numbers: " + sb.toString(),
                               Toast.LENGTH_LONG).
                      show();
            }
        });

        // Action for Stop button
        btnStop.setOnClickListener(new Button.OnClickListener()
        {
            /**
             * Called when Stop button has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v)
            {
                String msg;
                if(! isBound)
                {
                    msg = "Service was not connected.";
                }
                else if (! etCode.getText().toString().equals("kill"))
                {
                    msg = "Secret does not match.";
                }
                else
                {
                    unbindService(con);
                    isBound = false;
                    msg = "Service is disconnected.";
                }

                Toast.makeText(getBaseContext(),
                               msg,
                               Toast.LENGTH_LONG).
                      show();
            }
        });
    }
}
