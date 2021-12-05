package com.lau.findmymedicine2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MedResult extends AppCompatActivity {
    TextView txt=(TextView) findViewById(R.id.txt_1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_result);

        Intent intent=getIntent();
        txt.setText(intent.getStringExtra("content"));


    }
}