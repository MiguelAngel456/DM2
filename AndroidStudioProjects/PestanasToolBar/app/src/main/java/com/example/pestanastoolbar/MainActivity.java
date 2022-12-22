package com.example.pestanastoolbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void ejemplo1 (View v){
        Intent intent = new Intent(this, PestanasEj1.class);
        startActivity(intent);
    }
    public void ejemplo2 (View v){
        Intent intent = new Intent(this, PestanasEj2.class);
        startActivity(intent);
    }
    public void ejemplo3 (View v){
        Intent intent = new Intent(this, PestanasEj3.class);
        startActivity(intent);
    }
    public void toolBar (View v){
        Intent intent = new Intent(this,ToolBarActivity.class);
        startActivity(intent);
    }
}