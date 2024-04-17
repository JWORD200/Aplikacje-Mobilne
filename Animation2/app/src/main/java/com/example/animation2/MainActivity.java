package com.example.animation2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int CIRCLE_RADIUS = 50;
    private static final int DELAY = 10;

    private FrameLayout frameLayout;
    private CircleView circleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.frameLayout);

        circleView = new CircleView(this);
        frameLayout.addView(circleView);

        moveCircleRandomly();
    }

    private void moveCircleRandomly() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                circleView.moveRandomly();
                handler.postDelayed(this, DELAY);
            }
        }, DELAY);
    }

    public class CircleView extends View {

        private Paint paint;
        private int x, y;
        private int xVelocity, yVelocity;
        private int screenWidth, screenHeight;

        public CircleView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLUE);

            screenWidth = getResources().getDisplayMetrics().widthPixels;
            screenHeight = getResources().getDisplayMetrics().heightPixels;

            x = new Random().nextInt(screenWidth - CIRCLE_RADIUS * 2) + CIRCLE_RADIUS;
            y = new Random().nextInt(screenHeight - CIRCLE_RADIUS * 2) + CIRCLE_RADIUS;
            xVelocity = 9;
            yVelocity = 9;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawCircle(x, y, CIRCLE_RADIUS, paint);
        }

        public void moveRandomly() {
            x += xVelocity;
            y += yVelocity;

            if (x <= CIRCLE_RADIUS || x >= screenWidth - CIRCLE_RADIUS) {
                xVelocity = -xVelocity;
                x = Math.max(CIRCLE_RADIUS, Math.min(x, screenWidth - CIRCLE_RADIUS));
            }
            if (y <= CIRCLE_RADIUS || y >= screenHeight - CIRCLE_RADIUS) {
                yVelocity = -yVelocity;
                y = Math.max(CIRCLE_RADIUS, Math.min(y, screenHeight - CIRCLE_RADIUS));
            }

            invalidate();
        }
    }
}