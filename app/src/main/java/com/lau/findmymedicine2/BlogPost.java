package com.lau.findmymedicine2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BlogPost extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Blog");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_post);
        TextView medNameN=findViewById(R.id.txt_medName);
        TextView medQuantityN=findViewById(R.id.txt_quantity);
        TextView medContactN=findViewById(R.id.txt_contanct);
        Button post=findViewById(R.id.btn_create);
//        myRef.setValue("Hello, World!");
//        myRef.setValue(medNameN.getText().toString());

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.setValue(medNameN.getText().toString());
                Log.i("ashta",medNameN.getText().toString());
                medQuantityN.getText().toString();
                medContactN.getText().toString();

            }
        });



    }
}