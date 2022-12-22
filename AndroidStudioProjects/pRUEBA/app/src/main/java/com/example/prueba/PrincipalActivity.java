package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }
    public void actividad1 (View v){
        Intent intent = new Intent(PrincipalActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void actividad2 (View v){
        Intent intent = new Intent(PrincipalActivity.this, MainActivity2.class);
        startActivity(intent);
    }
    public void actividad3 (View v){
        Intent intent = new Intent(PrincipalActivity.this, actividad3.class);
        startActivity(intent);
    }
    public void actividad4 (View v){
        Intent intent = new Intent(PrincipalActivity.this, Actividad_4.class);
        startActivity(intent);
    }
    public void actividad5 (View v){
        Intent intent = new Intent(PrincipalActivity.this, actividad5.class);
        startActivity(intent);
    }
}