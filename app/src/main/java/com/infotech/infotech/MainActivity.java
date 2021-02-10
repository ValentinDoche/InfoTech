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
        List<String> name = databaseAccess.getName();
        List<String> type = databaseAccess.getType();
        List<String> description = databaseAccess.getDescription();
        List<String> img = databaseAccess.getImg();
        databaseAccess.close();

        TechsAdapter techsAdapter = new TechsAdapter(this, name,type,description,img);
        recyclerView.setAdapter(techsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}