/**
 * Author:  Kimitoshi Senno
 * Date: 	Oct 26, 2014
 * For:	    CS311D Homework 4
 * File:	PrimeNumService/app/src/main/java/com/senno/toshi/primenumservice/MyBinderService.java
 * Purpose: Service class to generate prime numbers
 */

package com.senno.toshi.primenumservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
//import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MyBinderService extends Service
{
    private final IBinder mb = new MyBinder();
    private String secret;

    /**
     * Called when the service is first created.
     */
    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    /**
     * Called to notify a Service that it is no longer used and is being removed.
     */
    @Override
    public void onDestroy()
    {
        Toast.makeText(this,"Service is destroyed.", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    /**
     * Called every time a client explicitly starts the service.
     *
     * @param intent The Intent.
     * @param flags Additional data about this start request.
     * @param startId A unique integer representing this specific request
     *
     * @return The return value indicates what semantics the system should
     * use for the service's current started state.
     */
    @Override
    public int onStartCommand(Intent i, int flags, int startId)
    {
        /*
        // For future work to receive secret from client
        Bundle b = i.getExtras();
        if(b!=null)
        {
            secret = b.getString("secret");
        }
        */

        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        return (START_STICKY);
    }

    /**
     * Return the communication channel to the service.
     *
     * @param intent The Intent that was used to bind to this service.
     *
     * @return Return an IBinder through which clients can call on to the
     *         service.
     */
    @Override
    public IBinder onBind(Intent i)
    {
        return mb;
    }

    /**
     * Internal Class to extend Binder class for the outer class.
     */
    public class MyBinder extends Binder
    {
        MyBinderService getService()
        {
            return MyBinderService.this;
        }
    }

    /**
     * Return the specified numbers of prime numbers in the specified
     * range.
     *
     * @param start The integer that defines the start of the range (inclusive.)
     * @param end The integer that defines the end of the range (inclusive.)
     * @param num The integer that defines how many prime number are generated.
     *
     * @return Return an ArrayList contains the generated prime numbers.
     *         In case of an error or a 0 result, it is empty.
     */
    public List<Integer> primeNumber (int start, int end, int num)
    {
        Toast.makeText(this,"One moment, please.", Toast.LENGTH_LONG).show();

        List<Integer> lPrime = new ArrayList<Integer>();
        boolean isPrime;

        // Very basic error checking
        if(start <= 0 ||
           end <= 0 ||
           num <= 0 ||
           start > end)
        {
            return lPrime;
        }

        if (start == 1 || start == 2) {
            start = 3;
            lPrime.add(2);
        }

        if(start%2==0)
        {
            start += 1;
        }

        for (int i = start;  i<= end; i+=2)
        {
            isPrime = true;

            for (int j=2; j < i; j++)
            {
                Log.d("Step 1", "i:" + i + "j:" + j);
                if(i%j == 0)
                {
                    //Log.d("Step 2", "i:" + i + " j:" + j);
                    isPrime = false;
                    break;
                }
            }

            if (isPrime)
            {
                lPrime.add(i);
            }

            if (lPrime.size() >= num)
            {
                return lPrime;
            }
        }
        return lPrime;
    }
}
