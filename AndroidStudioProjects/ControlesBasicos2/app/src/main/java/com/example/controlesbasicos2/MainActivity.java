package com.example.controlesbasicos2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private CheckBox cbMarcar;
    private TextView tvMessage
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbMarcar=findViewById(R.id.Marcar);
        tvMessage=tvMessagefindViewById(R.id.tvMessaje);

        cbMarcar.setOnClickListener(){
            if(cb)
        }
    }
}