package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    String filename = "text.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.b);
        final TextView textView = findViewById(R.id.text);

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line=br.readLine()) !=null){
                stringBuilder.append(line);
            }
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());

            String uczen = jsonObject.getJSONObject("Grupa2").getString("8");
            System.out.println("Moje imię i nazwisko: " + uczen);

            button.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View view) {
                    textView.setText("Moje imię i nazwisko: " + uczen);
                }
            });
        } catch (Exception e){
            throw new RuntimeException();
        }

    }
}