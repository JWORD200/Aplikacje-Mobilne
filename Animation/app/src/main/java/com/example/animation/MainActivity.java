package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private BubbleAnimation bubbleAnimation;
    private boolean isAnimationRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.frameLayout);
        bubbleAnimation = new BubbleAnimation(this);
        frameLayout.addView(bubbleAnimation);

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAnimationRunning) {
                    bubbleAnimation.stopAnimation();
                    isAnimationRunning = false;
                } else {
                    bubbleAnimation.startAnimation();
                    isAnimationRunning = true;
                }
            }
        });
    }
}

class BubbleAnimation extends View {
    private List<Bubble> bubbles = new ArrayList<>();
    private Paint paint = new Paint();
    private ValueAnimator animator;
    private long animatorPausedTime = 0;

    public BubbleAnimation(Context context) {
        super(context);
        paint.setColor(Color.CYAN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Bubble bubble : bubbles) {
            canvas.drawCircle(bubble.x, bubble.y, bubble.radius, paint);
        }
    }

    public void startAnimation() {
        if (animator == null) {
            animator = ValueAnimator.ofInt(0, getHeight());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    createBubble();
                    updateBubbles();
                    invalidate();
                }
            });
            animator.setDuration(3000);
            animator.setRepeatMode(ValueAnimator.RESTART);
            animator.setRepeatCount(ValueAnimator.INFINITE);
        } else {
            long currentTime = System.currentTimeMillis();
            animator.setCurrentPlayTime(currentTime - animatorPausedTime);
        }
        animator.start();
    }

    public void stopAnimation() {
        if (animator != null) {
            animator.cancel();
            animatorPausedTime = System.currentTimeMillis();
        }
    }

    private void createBubble() {
        Random random = new Random();
        if (random.nextInt(5) == 0) {
            int x = random.nextInt(getWidth());
            int radius = random.nextInt(50) + 20;
            bubbles.add(new Bubble(x, getHeight(), radius));
        }
    }

    private void updateBubbles() {
        for (int i = 0; i < bubbles.size(); i++) {
            Bubble bubble = bubbles.get(i);
            bubble.y -= 10;
            if (bubble.y + bubble.radius < 0) {
                bubbles.remove(i);
                i--;
            }
        }
    }

    private class Bubble {
        int x;
        int y;
        int radius;

        public Bubble(int x, int y, int radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }
    }
}