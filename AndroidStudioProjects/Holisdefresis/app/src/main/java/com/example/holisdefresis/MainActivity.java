package com.example.holisdefresis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText txtHoliwi;
    private Button btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHoliwi = findViewById(R.id.txtNombre);
        btnAceptar = findViewById(R.id.btnAceptar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, HoliwisActivity.class);

                //Creamos un Bundle donde guardaremos la variable txtHoliwi
                Bundle b = new Bundle();
                b.putString("NOMBRE", txtHoliwi.getText().toString());
                intent.putExtras(b);

                startActivity(intent);
            }
        });
    }
}