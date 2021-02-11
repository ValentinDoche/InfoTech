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


    final String[] Title = {"Open Classroom", "Udemy", "Skilleos", "oclock", "sololearn", "Coursera", "Codecademy", "Matha", "Udacity"};
    final String[] Price = {"5 290 €", "2 288 €", "240 €", "6500 €", "Free", "Free", "216 €", "1500 €", "600 €"};
    final String[] Description = {
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
    final int[] images = {R.drawable.openclassrooms,R.drawable.udemy,R.drawable.skilleos,R.drawable.oclock,R.drawable.sololearn,R.drawable.coursera,R.drawable.codecademy,R.drawable.matha,R.drawable.udacity};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        opporView = findViewById(R.id.oppor_recycler);
        courseView = findViewById(R.id.courses_recycler);

        OpportunitiesAdapter opportunitiesAdapter = new OpportunitiesAdapter(this, Title);
        opporView.setAdapter(opportunitiesAdapter);
        opporView.setLayoutManager(new LinearLayoutManager(this));

        CoursesAdapter coursesAdapter = new CoursesAdapter(this, Title);
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