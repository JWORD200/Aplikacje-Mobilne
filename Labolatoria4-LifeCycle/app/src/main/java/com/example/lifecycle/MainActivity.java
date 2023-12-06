package com.example.lifecycle;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("EXIT button clicked!");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        showToast("Starting app (onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showToast("Resuming (onResume)");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        showToast("onSaveInstanceState");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showToast("Pausing (onPause)");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showToast("Stopping (onStop)");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showToast("Destroying (onDestroy)");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showToast("Restarting (onRestart)");
    }

    @Override
    public void onBackPressed() {
        showToast("BACK button pressed!");
        super.onBackPressed();
    }

    @Override
    protected void onUserLeaveHint() {
        showToast("HOME button pressed!");
        super.onUserLeaveHint();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_CALL) {
            showToast("CALL button pressed!");
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_ENDCALL) {
            showToast("HANG-UP button pressed!");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onReceiveSMS(String message) {
        showToast("SMS received: " + message);
    }

    public void onIncomingCall(String phoneNumber) {
        showToast("Incoming call from: " + phoneNumber);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
