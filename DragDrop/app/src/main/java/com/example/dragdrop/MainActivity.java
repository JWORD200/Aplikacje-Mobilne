package com.example.dragdrop;

import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View square1 = findViewById(R.id.canvas1);
        View square2 = findViewById(R.id.canvas2);
        View square3 = findViewById(R.id.canvas3);
        View square4 = findViewById(R.id.canvas4);
        View square5 = findViewById(R.id.canvas5);

        square1.setOnTouchListener(new MyTouchListener());
        square2.setOnTouchListener(new MyTouchListener());
        square3.setOnTouchListener(new MyTouchListener());
        square4.setOnTouchListener(new MyTouchListener());
        square5.setOnTouchListener(new MyTouchListener());

        RelativeLayout mainLayout = findViewById(R.id.mainLayout);
        mainLayout.setOnDragListener(new MyDragListener());
    }

    private final class MyTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDragAndDrop(null, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    private class MyDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundColor(Color.LTGRAY);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundColor(Color.WHITE);
                    break;
                case DragEvent.ACTION_DROP:
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    RelativeLayout container = (RelativeLayout) v;
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                            view.getWidth(),
                            view.getHeight());
                    layoutParams.leftMargin = (int) event.getX() - (view.getWidth() / 2);
                    layoutParams.topMargin = (int) event.getY() - (view.getHeight() / 2);
                    container.addView(view, layoutParams);
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundColor(Color.WHITE);
                default:
                    break;
            }
            return true;
        }
    }
}