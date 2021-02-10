package com.infotech.infotech;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

<<<<<<< HEAD
    private static final String FILENAME = "last-tech";

    private Button mButtonLocationSearch;
    private TextInputEditText mTextInputEditTextLocationSearch;
    private TextView mTextViewCityName;
    private ImageView mImageViewJobs;
    public static String MESSAGE_KEY = "hello";

=======
>>>>>>> f0601917e345e887c83d3bbf97f5b9fd3f217cff
    RecyclerView recyclerView;

    String s1[] = {"JAVA", "Python", "Html", "CSS", "Javascript", "Go"};
    String s2[] = {"Descirption JAVA", "Description Python", "Description HTML", "Description CSS", "Description Javascript", "Description Go"};

<<<<<<< HEAD
    @SuppressLint("WrongViewCast")
=======
>>>>>>> f0601917e345e887c83d3bbf97f5b9fd3f217cff
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.tech_recycler);

        TechsAdapter techsAdapter = new TechsAdapter(this, s1, s2);
        recyclerView.setAdapter(techsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
<<<<<<< HEAD

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.MESSAGE_KEY);

        ActionBar actionBar = getSupportActionBar();
        Log.v(Config.LOG_TAG, actionBar != null ? "actionBar not null" : "actionBar null");
        if (actionBar != null)
        {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //mImageViewJobs = findViewById(R.id.ImageViewjobs);


        getCurrentWeather("38000");

        JSONObject response = readFromCache();
        if (response != null) {
            updateUI(response);
        }

        //mButtonLocationSearch = findViewById(R.id.ButtonLocationSearch2);
        //mTextViewCityName = findViewById(R.id.TextViewCityName2);
        //mTextInputEditTextLocationSearch = findViewById(R.id.textLoup);
        //mButtonLocationSearch.setOnClickListener(new View.OnClickListener() {
        //});
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void getCurrentWeather(String search) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://jobs.github.com/positions.json?search="+search+"";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        storeInCache(response);
                        updateUI(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        queue.add(jsonObjectRequest);
    }

    private void updateUI(JSONObject response) {
        try {
            //JSONObject main = response.getJSONObject("city");
            //String name = main.getString("name");
            //mTextViewCityName.setText(name);
            //JSONArray weather = response.getJSONArray("list");
            //JSONArray secondWeather = weather.getJSONObject(1).getJSONArray("weather");
            //String tech = secondWeather.getJSONObject(0).getString("main");
            //mImageViewJobs.setImageResource(R.drawable.check);
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    private void storeInCache(JSONObject response) {
        File file = new File(getCacheDir(), FILENAME);
        String sResponse = response.toString();
        try (FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE)) {
            fos.write(sResponse.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONObject readFromCache() {
        String contents;
        JSONObject response = null;
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILENAME);
            if (fis == null) {
                return null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        } finally {
            contents = stringBuilder.toString();
        }
        try {
            response = new JSONObject(contents);
        }catch (JSONException err){
            Log.d("Error", err.toString());
        }
        return response;
=======
>>>>>>> f0601917e345e887c83d3bbf97f5b9fd3f217cff
    }
}