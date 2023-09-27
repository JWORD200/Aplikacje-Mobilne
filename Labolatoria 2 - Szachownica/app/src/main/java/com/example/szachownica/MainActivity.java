package com.example.szachownica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    Panel p;
    Paint background_p = new Paint(), background_p2 = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CONTEXT_MENU);
        setContentView(p = new Panel(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();
        if (id == R.id.creator) {
            Context context = getApplicationContext();
            CharSequence text = "Creator -> Jan Tomczak";
            int displayTime = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, displayTime);
            toast.setGravity(Gravity.BOTTOM, 0,0);
            toast.show();
            return true;
        }

        if (id == R.id.exit) {
            finish();
            return true;
        }

        if (id == R.id.color) {
            background_p.setColor(Color.BLACK);
            background_p2.setColor(Color.WHITE);
            p.postInvalidate();
            return true;
        }

        if (id == R.id.color2) {
            background_p.setColor(Color.RED);
            background_p2.setColor(Color.YELLOW);
            p.postInvalidate();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }


    class Panel extends View {

        public Panel (Context context){
            super(context);
            background_p.setColor(Color.WHITE);
            background_p2.setColor(Color.BLACK);
        }

        @Override
        public void onDraw(Canvas canvas){
            canvas.drawColor(Color.BLUE);
            float width = p.getWidth();
            float height = p.getHeight();

            float minSize = Math.min(width, height);

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 == 0) {
                        canvas.drawRect(i*minSize/8, j*minSize/8, (i+1)*minSize/8, (j+1)*minSize/8, background_p);
                    } else {
                        canvas.drawRect(i*minSize/8, j*minSize/8, (i+1)*minSize/8, (j+1)*minSize/8, background_p2);
                    }
                }
            }
        }
    }
}

