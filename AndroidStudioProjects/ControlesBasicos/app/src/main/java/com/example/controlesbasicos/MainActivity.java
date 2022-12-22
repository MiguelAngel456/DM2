package com.example.controlesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView lblMensaje;
    private ToggleButton btnToggleButton;
    private Switch btnSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblMensaje = findViewById(R.id.lblMensaje);
        btnToggleButton = findViewById(R.id.TogglebotonON);
        btnSwitch = findViewById(R.id.swtich1);
        btnSwitch.setOnClickListener(this);
        btnToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnToggleButton.isChecked()){
                    lblMensaje.setText("Boton toggle Activado");
                }else{
                    lblMensaje.setText("boton Toggle desactivado");
                }
            }
        });
    }
    public void botomSimplePulsado(View v){
        lblMensaje.setText("Boton simple pulsado");
    }

    @Override
    public void onClick(View view) {
        if(btnSwitch.isChecked()){
            lblMensaje.setText("Boton Switch Activado");
        }else{
            lblMensaje.setText("boton Switch desactivado");
        }
    }
}