package com.example.holisdefresis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HoliwisActivity extends AppCompatActivity {

    private TextView txtHoli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiwis);

        txtHoli = findViewById(R.id.txtSaludo);

        Bundle b = this.getIntent().getExtras();
        txtHoli.setText("hola " + b.getString("NOMBRE"));

    }
}