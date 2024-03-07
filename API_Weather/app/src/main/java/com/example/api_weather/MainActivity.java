package com.example.api_weather;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String apiUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.name);
        EditText editTextMultiline = findViewById(R.id.editTextTextMultiLine);
        Button searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + editText.getText().toString() + "&appid=212e07e8e8917e031b8d61a163eb2771&lang=pl&unit=metric";
                try {
                    URL url = new URL(apiUrl);
                    InputStream input = url.openStream();
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("GET");
                    conn.connect();

                    int responseCode = conn.getResponseCode();

                    if (responseCode != 200){
                        throw new RuntimeException("HttpResponseCode: " + responseCode);
                    } else {
                        InputStreamReader isr = new InputStreamReader(input);
                        BufferedReader reader = new BufferedReader(isr);
                        StringBuilder json = new StringBuilder();

                        int x;
                        while ((x = reader.read()) != -1) {
                            json.append((char) x);
                        }

                        editTextMultiline.setText(json);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                editText.setText("");
            }
        });
    }
}