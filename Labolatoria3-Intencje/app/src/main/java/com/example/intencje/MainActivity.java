package com.example.intencje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bName = this.findViewById(R.id.bName);
        Button bURL = this.findViewById(R.id.bURL);
        Button bPosition = this.findViewById(R.id.bPosition);
        TextInputLayout textInput = this.findViewById(R.id.tInput);

        bName.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FirstActivity.class);
            startActivity(intent);
        });

        bURL.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(textInput.getEditText().getText().toString()));
            startActivity(intent);
        });

        bPosition.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
            startActivity(intent);
        });
    }
}