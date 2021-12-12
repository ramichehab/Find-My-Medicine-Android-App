package com.lau.findmymedicine2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MedResult extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_result);



        TextView useView=findViewById(R.id.textView9);
        TextView warningView=findViewById(R.id.textView10);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String use = extras.getString("use");
            String warning=extras.getString("warning");

            useView.setText(use);
            warningView.setText(warning);


        }





    }
}