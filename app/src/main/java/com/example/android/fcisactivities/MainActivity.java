
package com.example.android.fcisactivities;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.fcisactivities.Adapters.MainAdapter;
import com.example.android.fcisactivities.Model.FcisActivity;
import com.example.android.fcisactivities.Network.ApiBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<FcisActivity> activities;
    private RecyclerView mainList;
    private MainAdapter mainAdapter;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeQuery();
        MainAdapter mainAdapter=new MainAdapter(MainActivity.this,activities);
        mainList=(RecyclerView)findViewById(R.id.main_list);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        mainList.setLayoutManager(layoutManager);
        mainList.setAdapter(mainAdapter);
    }
    private void makeQuery(){

        URL url=ApiBuilder.buildUrl();
        new getActivityTask().execute(url);
    }


    private class getActivityTask extends AsyncTask<URL,Void,ArrayList<FcisActivity>>
    {


        @Override
        protected ArrayList<FcisActivity> doInBackground(URL... urls) {
            URL url=urls[0];

            try {
                String jsonRespnse= ApiBuilder.getResponseFromHttpUrl(url);
                activities=ApiBuilder.extractFromJson(jsonRespnse);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return activities;
        }

        @Override
        protected void onPostExecute(ArrayList<FcisActivity> fcisActivities) {
            if(activities==null)
            {
                return;
            }

            super.onPostExecute(fcisActivities);
            MainAdapter mainAdapter=new MainAdapter(MainActivity.this,activities);
            mainList.setAdapter(mainAdapter);
        }
    }
}
