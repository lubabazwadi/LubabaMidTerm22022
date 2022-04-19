package com.example.lubaba_midterm_22022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Splash extends AppCompatActivity {
    String weatherWebserviceURL = "https://api.openweathermap.org/data/2.5/weather?q=london&appid=96bf76f06d33bd08a65dfc1b092065fb&units=metric";
    ImageView weatherBackground;
    TextView temperature, welcome, data, humidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Button mainButton3 = (Button) findViewById(R.id.activity3);
        final Button mainButton4 = (Button) findViewById(R.id.activity4);

        mainButton3.setOnClickListener(e-> startActivity(new Intent(Splash.this,MainActivity.class)));
        mainButton4.setOnClickListener(e-> startActivity(new Intent(Splash.this,MainActivity2.class)));


        temperature = (TextView) findViewById(R.id.temperature);
        welcome = (TextView) findViewById(R.id.welcome);
        data = (TextView) findViewById(R.id.data);
        humidity = (TextView) findViewById(R.id.humidity);
        weatherBackground = (ImageView) findViewById(R.id.weatherbackground);
        weather(weatherWebserviceURL);
    }

    public void weather(String url) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        data.setText(spinner.getSelectedItem().toString());
                        break;
                    case 1:
                        data.setText(spinner.getSelectedItem().toString());
                        break;
                    case 2:
                        data.setText(spinner.getSelectedItem().toString());
                        break;
                    case 3:
                        data.setText(spinner.getSelectedItem().toString());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonMain = response.getJSONObject("main");

                    temperature.setText(jsonMain.getDouble("temp") + " °C");
                    humidity.setText("Humidity: " + jsonMain.getDouble("humidity") + "%");

                    JSONArray weatherArray = response.getJSONArray("weather");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Lubaba", "Error in url");
            }
        });//1

        RequestQueue queue = Volley.newRequestQueue(this);//2
        queue.add(jsonObj);//3
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        weather("https://api.openweathermap.org/data/2.5/weather?q=London&appid=96bf76f06d33bd08a65dfc1b092065fb&units=metric");
                        break;
                    case 1:
                        weather("https://api.openweathermap.org/data/2.5/weather?q=riyadh&appid=96bf76f06d33bd08a65dfc1b092065fb&units=metric");
                        break;
                    case 2:
                        weather("https://api.openweathermap.org/data/2.5/weather?q=Madinah&appid=96bf76f06d33bd08a65dfc1b092065fb&units=metric");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}