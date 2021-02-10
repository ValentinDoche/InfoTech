package com.infotech.infotech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String s1[] = {"JAVA", "Python", "Html", "CSS", "Javascript", "Go"};
    String s2[] = {"Descirption JAVA", "Description Python", "Description HTML", "Description CSS", "Description Javascript", "Description Go"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.tech_recycler);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> quotes = databaseAccess.getContent();
        databaseAccess.close();

        TechsAdapter techsAdapter = new TechsAdapter(this, s1, s2);
        recyclerView.setAdapter(techsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}