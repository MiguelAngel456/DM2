package com.example.tanda3_miguel_angel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ejercicio3_1 extends AppCompatActivity {
    private TextView lblNombre,lblSexo,lblAficiones;
    private Button btnVolver;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio3_1);

        lblNombre=findViewById(R.id.lblNombre);
        lblSexo=findViewById(R.id.lblSexo);
        lblAficiones=findViewById(R.id.lblAficiones);
        btnVolver=findViewById(R.id.btnVolver);

        Bundle extras = getIntent().getExtras();
        String nom=extras.getString("nom");
        String sexo=extras.getString("sex");


        lblNombre.setText(nom);
        lblSexo.setText(sexo);
        String aficiones="";
        for (int i = 0; i <=4; i++) {
            if(extras.getString("afic"+i)!=null){
                aficiones+=extras.getString("afic"+i)+"\n";
            }

        }
        lblAficiones.setText(aficiones);


        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(ejercicio3_1.this, ejercicio3.class);
                    setResult(RESULT_OK, intent);
                    finish();
            }
        });
    }
}
