package com.devmobil.cours_dev_mobil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textview = findViewById(R.id.todolist);
        ScrollView scrollView = findViewById(R.id.toDoBox);
        FloatingActionButton button = findViewById(R.id.floatingActionButton);

        button.setOnClickListener(v -> {
            textview.setText("Hello World");
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("cybrary", "onStart: ");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("cybrary", "onResume: ");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("cybrary", "onPause: ");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("cybrary", "onStop: ");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("cybrary", "onDestroy: ");
    }
}
