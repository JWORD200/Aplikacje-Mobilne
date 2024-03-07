package com.example.odliczaniebezkitu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView odliczanie;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        odliczanie = findViewById(R.id.odliczanie);

        Calendar nowyRok = Calendar.getInstance();
        nowyRok.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR) + 1);
        nowyRok.set(Calendar.MONTH, Calendar.JANUARY);
        nowyRok.set(Calendar.DAY_OF_MONTH, 1);
        nowyRok.set(Calendar.HOUR_OF_DAY, 0);
        nowyRok.set(Calendar.MINUTE, 0);
        nowyRok.set(Calendar.SECOND, 0);

        long roznicaCzasu = nowyRok.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();

        new CountDownTimer(roznicaCzasu, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long sekund = millisUntilFinished / 1000;
                long minut = sekund / 60;
                long godzin = minut / 60;
                long dni = godzin / 24;
                long miesiecy = dni / 30;
                long lat = miesiecy / 12;

                String zawarotscOdliczana = String.format("%02d:%02d:%02d:%02d:%02d:%02d",lat, miesiecy % 12, dni % 30, godzin % 24, minut % 60, sekund % 60);

                odliczanie.setText(zawarotscOdliczana);
            }

            @Override
            public void onFinish() {
                odliczanie.setText("Szczęśliwego Nowego Roku !!!");
            }
        }.start();
    }
}