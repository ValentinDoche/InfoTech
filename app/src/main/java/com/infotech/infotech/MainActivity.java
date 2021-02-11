package com.infotech.infotech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

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
        jobsNumber = new ArrayList<>();
        for (String ignored : name) {
            jobsNumber.add("Loading...");
        }
        initAdapter();
        for (int i = 0; i < name.size(); i++) {
            getJobsCount(this, i, jobsNumber);
        }
        databaseAccess.close();

    }

    String url = "https://jobs.github.com/positions.json?description=";

    public void getJobsCount(Context ct, int position, List<String> jobsNumber) {

        RequestQueue requestQueue = Volley.newRequestQueue(ct);
        String url_search = url + name.get(position);

        @SuppressLint("DefaultLocale") JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url_search, null, response -> {
                    if (response.length() == 50){
                        jobsNumber.set(position, String.format("Offres : +%d",response.length()));
                    }else {
                        jobsNumber.set(position, String.format("Offres : %d",response.length()));
                    }
                    updateAdapter();
                }, error -> getJobsCount(ct, position, jobsNumber));

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