package com.lau.findmymedicine2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button btn=findViewById(R.id.btn_logout);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogOut();
            }
        });


    }

    private void LogOut() {
        Intent intent=new Intent(Dashboard.this,MainActivity.class);

        startActivity(intent);
    }


    public void openSearch(View view) {
        Intent intent=new Intent(Dashboard.this,Search.class);

        startActivity(intent);
    }

    public void openBlog(View view) {
        Intent intent=new Intent(Dashboard.this, BlogFeed.class);

        startActivity(intent);

    }

}