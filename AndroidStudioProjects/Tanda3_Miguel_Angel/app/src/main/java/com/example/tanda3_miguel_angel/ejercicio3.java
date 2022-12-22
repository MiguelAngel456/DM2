package com.example.tanda3_miguel_angel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ejercicio3 extends AppCompatActivity {
    private EditText txtNombre,txtApellido;
    private CheckBox chViajar,chMusica,chDeporte,chLectura;
    private RadioGroup rgSexo;
    private RadioButton rbHombre,rbMujer;
    private Button btnEnviar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio3);

        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        chViajar = findViewById(R.id.chViajar);
        chMusica = findViewById(R.id.chMusica);
        chDeporte = findViewById(R.id.chDeporte);
        chLectura = findViewById(R.id.chLectura);
        rgSexo = findViewById(R.id.rgSexo);
        rbHombre=findViewById(R.id.rbHombre);
        rbMujer=findViewById(R.id.rbMujer);
        btnEnviar = findViewById(R.id.btnEnviar);

        rbHombre.setChecked(true);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
        CheckBox[] arrCheckBox={chViajar,chMusica,chDeporte,chLectura};
            @Override
            public void onClick(View view) {
                String sexo = "";
                ArrayList<String> arrAficiones=new ArrayList<String>();
                if(rbHombre.isChecked()){
                    sexo=rbHombre.getText().toString();
                }
                if(rbMujer.isChecked()){
                    sexo=rbMujer.getText().toString();
                }

                Intent intent = new Intent(ejercicio3.this, ejercicio3_1.class);
                intent.putExtra("nom",txtNombre.getText().toString()+" "+txtApellido.getText().toString() );
                intent.putExtra("sex",sexo);
                for (int i = 0; i <arrCheckBox.length; i++) {
                    if(arrCheckBox[i].isChecked()){
                        intent.putExtra("afic"+i,arrCheckBox[i].getText().toString());
                    }
                }
                startActivityForResult(intent, 1235);
            }
        });
    }
    protected void onActivityResult(int requestCode,int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1235 && resultCode == RESULT_OK) {

        }
    }

}
