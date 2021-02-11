package com.infotech.infotech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    RecyclerView opporView, courseView;
    ImageView detailImage;
    TextView detailDesc;

    String name, description;
    int image;


    final String[] s1 = {"Open Classroom", "Udemy", "Skilleos", "Other", "Other", "Other", "Other", "Other", "Other"};

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


        detailImage = findViewById(R.id.details_img);
        detailDesc = findViewById(R.id.details_desc);

        getDate();
        setData();

    }

    private void getDate(){
        if (getIntent().hasExtra("name") && getIntent().hasExtra("description") && getIntent().hasExtra("image")){
            name = getIntent().getStringExtra("name");
            description = getIntent().getStringExtra("description");
            image = getIntent().getIntExtra("image", 1);

        }else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        detailDesc.setText(description);
        detailImage.setImageResource(image);
        setTitle(name);
    }

}