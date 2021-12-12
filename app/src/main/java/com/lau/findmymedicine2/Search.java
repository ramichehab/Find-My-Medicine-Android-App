package com.lau.findmymedicine2;

import static android.content.ContentValues.TAG;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.StringValue;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Search extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Medicine");
    ArrayList<String> medName= new ArrayList<String>();
    ArrayList<String> medUse= new ArrayList<String>();
    ArrayList<String> medWarning= new ArrayList<String>();

    ArrayAdapter arrayAdapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        DownloadTask task = new DownloadTask();


        try {
                String apiURL = "https://find-my-medicine-61d7c-default-rtdb.firebaseio.com/Medicine/1.json";
                task.execute(apiURL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        listView = (ListView) findViewById(R.id.ls2);

            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, medName);


            listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent =new Intent(Search.this,MedResult.class);
                intent.putExtra("use",medUse.get(i));
                intent.putExtra("warning",medWarning.get(i));
                startActivity(intent);
            }
        });

    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

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

                JSONObject jsonObject=new JSONObject(result);
                String Name=jsonObject.getString("Name");
                String Use=jsonObject.getString("Use");
                String Warning=jsonObject.getString("Warning");

                medName.add(Name);
                medUse.add(Use);
                medWarning.add(Warning);


                for(int i=2;i<=20;i++){
                    url=new URL("https://find-my-medicine-61d7c-default-rtdb.firebaseio.com/Medicine/"+i+".json");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    in = urlConnection.getInputStream();
                    reader = new InputStreamReader(in);
                    data=reader.read();

                    String articleContent="";

                    while (data!=-1){
                        char current = (char) data;
                        articleContent += current;
                        data = reader.read();
                    }
                    JSONObject jsonObject1=new JSONObject(articleContent);
                    String Name1=jsonObject1.getString("Name");
                    String Use1=jsonObject1.getString("Use");
                    String Warning1=jsonObject1.getString("Warning");

                    medName.add(Name1);
                    medUse.add(Use1);
                    medWarning.add(Warning1);

//                    Log.i("test1",Name1+" "+Use1+" "+Warning1+" ");
//                    Log.i("test1",String.valueOf(medUse.size()));

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








