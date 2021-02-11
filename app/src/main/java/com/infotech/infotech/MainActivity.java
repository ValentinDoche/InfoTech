package com.infotech.infotech;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TechsAdapter techsAdapter;
    RecyclerView recyclerView;
    List<String> name, type, description, img, jobsNumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.tech_recycler);


        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        name = databaseAccess.getName();
        type = databaseAccess.getType();
        description = databaseAccess.getDescription();
        img = databaseAccess.getImg();
        jobsNumber = new ArrayList<String>();
        for (String elem: name) {
            jobsNumber.add("Loading...");
        }
        initAdapter();
        for (int i = 0; i < name.size(); i++) {
            getJobsCount(this, i, jobsNumber);
        }
        databaseAccess.close();


        ActionBar actionBar = getSupportActionBar();
        Log.v(Config.LOG_TAG, actionBar != null ? "actionBar not null" : "actionBar null");
        if (actionBar != null)
        {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


    }

    String url = "https://jobs.github.com/positions.json?description=";

    public void getJobsCount(Context ct, int position, List<String> jobsNumber) {

        RequestQueue requestQueue = Volley.newRequestQueue(ct);
        String url_search = url + name.get(position);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url_search, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() == 50){
                            jobsNumber.set(position, String.format("Offres : +%d",response.length()));
                        }else {
                            jobsNumber.set(position, String.format("Offres : %d",response.length()));
                        }
                        updateAdapter();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyError ex = error;
                        // TODO: Handle error

                    }
                });

        requestQueue.add(jsonArrayRequest);
    }

    public void initAdapter(){
        techsAdapter = new TechsAdapter(this, name,type,description,img, jobsNumber);
        recyclerView.setAdapter(techsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void updateAdapter(){
        techsAdapter.notifyDataSetChanged();
    }
}