package com.lau.findmymedicine2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


    }



    public void openSearch(View view) {
        Intent intent=new Intent(Dashboard.this,Search.class);

        startActivity(intent);
    }

    public void openBlog(View view) {
        Intent intent=new Intent(Dashboard.this,Blog.class);

        startActivity(intent);

    }

}