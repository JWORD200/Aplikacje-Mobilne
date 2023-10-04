package com.example.intencje;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    public LocationManager locationManager;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
        Button buttonStart = this.findViewById(R.id.bStart);
        Button buttonStop = this.findViewById(R.id.bStop);
        buttonStop.setEnabled(true); //xd

        TextView textWidth = this.findViewById(R.id.tWidth);
        TextView textLength = this.findViewById(R.id.tLength);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                String width = String.valueOf(location.getLatitude());
                String length = String.valueOf(location.getLongitude());

                textWidth.setText(width);
                textLength.setText(length);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        buttonStart.setOnClickListener(v -> {
            if (!locationManager.isLocationEnabled()) {
                return;
            }

            buttonStart.setEnabled(false);
            buttonStop.setEnabled(true);

            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
        });

        buttonStop.setOnClickListener(v -> {
            if (!locationManager.isLocationEnabled()) {
                return;
            }

            buttonStop.setEnabled(false);
            buttonStart.setEnabled(true);

            locationManager.removeUpdates(listener);
        });
    }
}