package com.lau.findmymedicine2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DisplayMed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_med);



        TextView quantityView=findViewById(R.id.txt_quan);
        TextView contactView=findViewById(R.id.txt_cont);





        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String contact = extras.getString("contact");
            String quantity=extras.getString("quantity");

            quantityView.setText(quantity);
            contactView.setText(contact);


        }
    }
}