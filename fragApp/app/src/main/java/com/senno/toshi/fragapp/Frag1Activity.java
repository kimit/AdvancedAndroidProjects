/**
 * Author:  Kimitoshi Senno
 * Date: 	Sep 30, 2014
 * For:	    CS311D Homework 3
 * File:	fragApp/app/src/main/java/com/senno/toshi/fragapp/Frag1Activity.java
 * Purpose: Activity for Fragment 1
 */

package com.senno.toshi.fragapp;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Frag1Activity extends Fragment implements View.OnClickListener
{
    private String selectedMaker;
    private Button btnPrice;
    private Button btnPower;

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b)
    {
        Context c = getActivity().getApplicationContext();
        View vw = li.inflate(R.layout.frag1, vg, false);

        // Set up buttons
        btnPrice = (Button) vw.findViewById(R.id.buttonPrice);
        btnPower = (Button) vw.findViewById(R.id.buttonPower);

        btnPrice.setOnClickListener(this);
        btnPower.setOnClickListener(this);

        // Disable buttons by default
        btnPrice.setEnabled(false);
        btnPower.setEnabled(false);

        // Set up listview
        final String cars[]={"Honda",
                             "Toyota",
                             "Nissan",
                             "Subaru",
                             "Mitsubishi"};
        ListView lv = (ListView) vw.findViewById(R.id.car_list);
        ArrayAdapter<String> ar =
                new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1, cars);
        lv.setAdapter(ar);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                btnPower.setEnabled(true);
                btnPrice.setEnabled(true);

                selectedMaker = ((TextView) v).getText().toString();
            }
        });
        return vw;
    }

    public void onClick(View v)
   {
        String output;

        switch (v.getId())
        {
            case R.id.buttonPrice:
                output = "its price is " + getPrice(selectedMaker);
                break;

            case R.id.buttonPower:
                output = "its power is " + getPower(selectedMaker);
                break;

            default:
                output = "other information was not found";
                break;
        }

        String finalOutput = "You have selected " + selectedMaker + " and "
                            + output;

        // Set up textview
        TextView tv = (TextView) getActivity().findViewById(R.id.selected_option);
        tv.setText(finalOutput + "\n\n To continue, please select a maker");

       // Reset buttons
        btnPrice.setEnabled(false);
        btnPower.setEnabled(false);

        // Set up intent
        Intent i = new Intent(getActivity().getApplicationContext(),ShowItemActivity.class);
        i.putExtra("item", finalOutput);

        // Start ShowItemActivity
        startActivity(i);
    }

    private String getPrice(String maker)
    {
        String result = "0";

        if (selectedMaker.equals("Honda"))
        {
            result = "10,000";
        }
        else if(selectedMaker.equals("Toyota"))
        {
            result = "$0,000";
        }
        else if(selectedMaker.equals("Nissan"))
        {
            result = "30,000";
        }
        else if(selectedMaker.equals("Subaru"))
        {
            result = "40,000";
        }
        else if(selectedMaker.equals("Mitsubishi"))
        {
            result = "50,000";
        }

        return "$" + result;
    }

    private String getPower(String maker)
    {
        String result = "0";

        if (selectedMaker.equals("Honda"))
        {
            result = "100";
        }
        else if(selectedMaker.equals("Toyota"))
        {
            result = "200";
        }
        else if(selectedMaker.equals("Nissan"))
        {
            result = "300";
        }
        else if(selectedMaker.equals("Subaru"))
        {
            result = "400";
        }
        else if(selectedMaker.equals("Mitsubishi"))
        {
            result = "500";
        }

        return result + "-hp";
    }
}
