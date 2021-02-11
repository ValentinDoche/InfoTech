package com.infotech.infotech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
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
    int image;

    List<String> opporName, opporType, opporDesc, opporLocation, opporURL;


    final String[] coursesTitle = {"Open Classroom", "Udemy", "Skilleos", "oclock", "sololearn", "Coursera", "Codecademy", "Matha", "Udacity"};
    final String[] coursesPrice = {"5 290 €", "2 288 €", "240 €", "6500 €", "Free", "Free", "216 €", "1500 €", "600 €"};
    final String[] coursesDescription = {
            "Devenez qui vous voulez être avec OpenClassrooms. Choisissez votre carrière. Suivez une formation constituée de projets professionnalisants et de séances individuelles avec un mentor dédié chaque semaine. Obtenez un diplôme reconnu par l'état. Enrichissez votre CV avec les programmes en alternance proposés par OpenClassrooms et gagnez un salaire tout en suivant votre formation.",
            "Le choix de cours le plus vaste du monde",
            "Retrouvez le goût d’apprendre, Des milliers de cours interactifs 100% en ligne 24/7 réalisés par les meilleurs Experts et sur tous vos sujets préférés !",
            "mbarquez dans nos salles de classe virtuelles pour apprendre le métier de développeur, entouré de vos camarades de promotion et de vos formateurs. Un seul objectif : faire de vous un développeur web compétent, diplômé et recruté !",
            "Apprenez à coder gratuitement! Cours interactifs, «sur le pouce» pratique, soutien par les pairs. ",
            "Développez vos compétences grâce à des cours, des certificats et des diplômes en ligne proposés par les meilleures universités et entreprises au monde.",
            "Tout d'abord, nous avons inventé le meilleur système pour apprendre à coder. Neuf ans et 50 millions d'apprenants plus tard, nous l'avons perfectionné.",
            "Forme-toi aujourd'hui aux compétences de demain.",
            "Les dernières compétences numériques à portée de main. Découvrez le moyen le plus rapide et le plus efficace d'acquérir une expertise prête à l'emploi pour les carrières de l'avenir."
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
            image = getIntent().getIntExtra("image", 1);
            jobsCount = getIntent().getStringExtra("jobsCount");

        }else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        detailDesc.setText(description);
        detailImage.setImageResource(image);
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
                            opporDesc.set(i, jObject.getString("description").replace("<p>", ""));
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