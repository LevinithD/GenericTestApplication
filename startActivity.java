package com.example.generictestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

     TextView txt_pathShow;
     Button btn_filePicker;
     Intent myFileIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        txt_pathShow = (TextView)findViewById(R.id.txt_path);
        btn_filePicker = (Button) findViewById(R.id.btn_filePicker);

        btn_filePicker.setOnClickListener(new View.OnClickListener()
        {
            @Override
                    public void onClick(View view)
                    {
                       myFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                       myFileIntent.setType("*/*");
                       startActivityForResult(myFileIntent,10);

                    }
        });
    }

     @Override
     protected void onActivityResult( int requestCode, int resultCode, @Nullable Intent data) 
     {
         super.onActivityResult(requestCode, resultCode, data);
            
         switch (requestCode)
            { 
                case 10:
                    if(resultCode==RESULT_OK)
                    {
                        String path = data.getData().getPath();
                        txt_pathShow.setText(path);

                    }
                break;
            }

    }
