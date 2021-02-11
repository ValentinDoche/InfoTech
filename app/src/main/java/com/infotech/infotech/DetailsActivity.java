package com.infotech.infotech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {

    RecyclerView opporView, courseView;

    String s1[] = {"Open Classroom", "Udemy", "Skilleos", "Other", "Other", "Other", "Other", "Other", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        opporView = findViewById(R.id.oppor_recycler);
        courseView = findViewById(R.id.courses_recycler);

        OpportunitiesAdapter opportunitiesAdapter = new OpportunitiesAdapter(this, s1);
        opporView.setAdapter(opportunitiesAdapter);
        opporView.setLayoutManager(new LinearLayoutManager(this));

        CoursesAdapter coursesAdapter = new CoursesAdapter(this, s1);
        courseView.setAdapter(coursesAdapter);
        courseView.setLayoutManager(new LinearLayoutManager(this));


    }
}