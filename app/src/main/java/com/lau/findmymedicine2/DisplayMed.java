package com.lau.findmymedicine2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DisplayMed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_med);


        TextView Name=findViewById(R.id.txt_name);
        TextView quantityView=findViewById(R.id.txt_quan);
        TextView contactView=findViewById(R.id.txt_cont);





        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name");
            String contact = extras.getString("contact");
            String quantity=extras.getString("quantity");

            Name.setText(name);
            quantityView.setText(quantity);
            contactView.setText(contact);


        }
    }
}