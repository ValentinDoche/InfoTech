package com.infotech.infotech;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.List;

public class JobsAPI {

    String url = "https://jobs.github.com/positions.json?description=";

    public void getJobsNumber(Context ct, String search, List<String> jobsNumber) {

        RequestQueue requestQueue = Volley.newRequestQueue(ct);
        String url_search = url + search;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        jobsNumber.add(String.format("%d",response.length()));
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

}