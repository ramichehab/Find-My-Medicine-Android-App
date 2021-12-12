package com.lau.findmymedicine2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BlogFeed extends AppCompatActivity {
    JSONArray array;
    ArrayList<String> medName= new ArrayList<String>();
    ArrayList<String> medQ= new ArrayList<String>();
    ArrayList<String> contact= new ArrayList<String>();

    ArrayAdapter arrayAdapter;
    ListView listView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        DownloadTask2 task = new DownloadTask2();


        try {
            String apiURL = "https://find-my-medicine-61d7c-default-rtdb.firebaseio.com/Blog/.json";
            task.execute(apiURL);

//            Log.i("1",String.valueOf(array.length()));
            //intent.putExtra("warning",medWarning.get(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
        listView = (ListView) findViewById(R.id.ls3);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, medName);


        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent =new Intent(BlogFeed.this, DisplayMed.class);
                intent.putExtra("contact",contact.get(i));
                intent.putExtra("quantity",medQ.get(i));
                startActivity(intent);
            }
        });




    }

    public void createPost(View view){
        Intent intent=new Intent(BlogFeed.this,BlogPost.class);

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
                array.length();
                for (int i=0;i<=array.length(); i++) {

                    JSONObject jsonObject = array.getJSONObject(i);
                    String Name = jsonObject.getString("medicineName");
                    String Quantity = jsonObject.getString("Quantity");
                    String Contact = jsonObject.getString("ContactInfo");

                    medName.add(Name);
                    medQ.add(Quantity);
                    contact.add(Contact);




                }


                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

          arrayAdapter.notifyDataSetChanged();

        }
    }

}