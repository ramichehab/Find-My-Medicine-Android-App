package com.lau.findmymedicine2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class BlogPost extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Blog");

    JSONArray array;
    int x;
    String y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_post);
        TextView medNameN=findViewById(R.id.txt_medName);
        TextView medQuantityN=findViewById(R.id.txt_quantity);
        TextView medContactN=findViewById(R.id.txt_contanct);
        Button post=findViewById(R.id.btn_create);

        DownloadTask2 task = new DownloadTask2();


        try {
            String apiURL = "https://find-my-medicine-61d7c-default-rtdb.firebaseio.com/Blog/.json";
            task.execute(apiURL);

//            Log.i("1",String.valueOf(array.length()));
            //intent.putExtra("warning",medWarning.get(i));
        } catch (Exception e) {
            e.printStackTrace();
        }




        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Blog b1=new Blog(medNameN.getText().toString(),medQuantityN.getText().toString(),medContactN.getText().toString());

                myRef.child(y).setValue(b1);
                Home();







            }
        });







    }
    public void Home(){
        Intent intent = new Intent(this,BlogFeed.class);
        startActivity(intent);
    }


    public class DownloadTask2 extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection;
            try {

                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

                array=new JSONArray(result);
                x=array.length();
                y=String.valueOf(x);





                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

//            arrayAdapter.notifyDataSetChanged();

        }
    }
}








//                myRef.setValue(medNameN.getText().toString());
//                Log.i("ashta",medNameN.getText().toString());
//                medQuantityN.getText().toString();
//                medContactN.getText().toString();