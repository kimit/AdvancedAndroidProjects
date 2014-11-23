/**
 * Author:  Kimitoshi Senno
 * Date: 	Nov 23, 2014
 * For:	    CS311D Homework 5
 * File:	CameraDemo/app/src/main/java/com/senno/toshi/camerademo/CameraDemoActivity.java
 * Purpose: Main Activity for Application
 */

package com.senno.toshi.camerademo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.File;

public class CameraDemoActivity extends Activity
{
    private final int PICTURE_ACTIVITY_CODE = 1;
    private final String FILEPARENT = "sdcard/";
    private File fname;
    private Button btn;
    private ImageView iv;
    private EditText et;

    //********************************onCreate()*******************************
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_demo);

        // Getting UIs
        btn = (Button) findViewById(R.id.btn);
        iv = (ImageView) findViewById(R.id.img);
        et = (EditText) findViewById(R.id.et);

        // Setting up event listener for the button
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                takePhoto();
            }
        });
    }

    //********************************defineFileName()*************************
    // Create file name for a photo
    private String defineFileName()
    {
      String fileName = FILEPARENT;
      String etContent = et.getText().toString();
      if(etContent.isEmpty())
      {
          etContent = "photo"; // default file name
      }
      fileName += etContent;
      fileName += ".jpg";
      return fileName;
    }

    //********************************takePhoto()******************************
    // Start Camera app
    private void takePhoto()
    {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Log.d("file name is: ", defineFileName()); // Debug
        fname = new File(defineFileName());

        Uri outputFileUri = Uri.fromFile(fname);
        i.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(i, PICTURE_ACTIVITY_CODE);
    }

    //********************************onActivityResult()***********************
    // Display a photo
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICTURE_ACTIVITY_CODE)
        {
            if (resultCode == RESULT_OK)
            {
                Uri inputFileUri = Uri.fromFile(fname);
                iv.setImageURI(inputFileUri);
            }
        }
    }
}
