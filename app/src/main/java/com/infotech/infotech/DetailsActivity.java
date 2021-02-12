package com.infotech.infotech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    OpportunitiesAdapter opportunitiesAdapter;
    CoursesAdapter coursesAdapter;

    RecyclerView opporView, courseView;
    ImageView detailImage;
    TextView detailDesc;

    String name, description, jobsCount;
    Bitmap image;

    List<String> opporName, opporType, opporDesc, opporLocation, opporURL;


    final String[] coursesTitle = {"Open Classroom", "Udemy", "Skilleos", "oclock", "sololearn", "Coursera", "Codecademy", "Matha", "Udacity"};
    final String[] coursesPrice = {"5 290 €", "2 288 €", "240 €", "6500 €", "Free", "Free", "216 €", "1500 €", "600 €"};
    final String[] coursesDescription = {
            "Become who you want to be with OpenClassrooms. Choose your career. Take a training course consisting of professionalizing projects and one-on-one sessions with a dedicated mentor each week. Earn a state-recognized diploma. Enhance your resume with OpenClassrooms' co-op programs and earn a salary while you train.",
            "The widest choice of courses in the world",
            "Find the desire to learn, thousands of 100% interactive courses online 24/7 made by the best Experts and on all your favorite topics!",
            "Embark on our virtual classrooms to learn the developer's job, surrounded by your classmates and trainers. One goal: make you a competent, qualified and recruited web developer!",
            "Learn how to code for free! Interactive, hands-on, \"on the go\" courses, peer support. ",
            "Develop your skills with online courses, certificates and diplomas from the world's top universities and companies.",
            "First of all, we invented the best system to learn how to code. Nine years and 50 million learners later, we perfected it.",
            "Train today for the skills of tomorrow.",
            "The latest digital skills at your fingertips. Discover the fastest and most effective way to acquire ready-to-use expertise for the careers of the future."
    };
    final int[] coursesImages = {R.drawable.openclassrooms,R.drawable.udemy,R.drawable.skilleos,R.drawable.oclock,R.drawable.sololearn,R.drawable.coursera,R.drawable.codecademy,R.drawable.matha,R.drawable.udacity};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detailImage = findViewById(R.id.details_img);
        detailDesc = findViewById(R.id.details_desc);

        getDate();
        setData();

        opporName = new ArrayList<>();
        opporType = new ArrayList<>();
        opporDesc = new ArrayList<>();
        opporLocation = new ArrayList<>();
        opporURL = new ArrayList<>();

        for (int i = 0; i < Integer.parseInt(jobsCount); i++) {
            opporName.add("Loading...");
            opporType.add("Loading...");
            opporDesc.add("Loading...");
            opporLocation.add("Loading...");
            opporURL.add("Loading...");
        }

        initAdapter();

        getOpportunities();

    }

    private void getDate(){
        if (getIntent().hasExtra("name") &&
                getIntent().hasExtra("description") &&
                getIntent().hasExtra("image") &&
                getIntent().hasExtra("jobsCount")){

            name = getIntent().getStringExtra("name");
            description = getIntent().getStringExtra("description");
            image = (Bitmap) getIntent().getParcelableExtra("image");
            jobsCount = getIntent().getStringExtra("jobsCount");

        }else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        detailDesc.setText(description);
        detailImage.setImageBitmap(image);
        setTitle(name);
    }

    public void initAdapter(){
        opporView = findViewById(R.id.oppor_recycler);
        courseView = findViewById(R.id.courses_recycler);

        opportunitiesAdapter = new OpportunitiesAdapter(this, opporName, opporType, opporDesc, opporLocation, opporURL);
        opporView.setAdapter(opportunitiesAdapter);
        opporView.setLayoutManager(new LinearLayoutManager(this));

        coursesAdapter = new CoursesAdapter(this, coursesTitle, coursesPrice, coursesDescription, coursesImages);
        courseView.setAdapter(coursesAdapter);
        courseView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void updateAdapter(){
        opportunitiesAdapter.notifyDataSetChanged();
        coursesAdapter.notifyDataSetChanged();
    }

    final String url = "https://jobs.github.com/positions.json?description=";

    public void getOpportunities() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url_search = url + name;

        @SuppressLint("DefaultLocale") JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url_search, null, response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jObject = response.getJSONObject(i);
                            opporName.set(i, jObject.getString("title"));
                            opporDesc.set(i, jObject.getString("description")
                                    .replace("<p>", "")
                            .replace("<strong>", "")
                            .replace("</strong>", "")
                            .replace("</p>", ""));
                            opporType.set(i, jObject.getString("type"));
                            opporLocation.set(i, jObject.getString("location"));
                            opporURL.set(i, jObject.getString("url"));
                            updateAdapter();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, error -> getOpportunities());

        requestQueue.add(jsonArrayRequest);
    }
}