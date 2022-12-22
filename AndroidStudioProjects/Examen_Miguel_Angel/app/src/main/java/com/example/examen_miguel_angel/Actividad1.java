package com.example.examen_miguel_angel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Actividad1 extends AppCompatActivity {

    private Button btnAceptar, btnCancelar;
    private EditText txtFecha,txtNombre,txtApellidos;
    private Spinner spinOrigen,spinDestino;
    private RadioGroup rgHoras;
    private RadioButton rb1,rb2,rb3,rb4,rb5;
    RadioButton[] arrradio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad1);

        btnAceptar=findViewById(R.id.btnAceptar);
        btnCancelar=findViewById(R.id.btnCancelar);

        txtFecha=findViewById(R.id.txtFecha);
        txtNombre=findViewById(R.id.txtNombre);
        txtApellidos=findViewById(R.id.txtApellidos);

        spinOrigen=findViewById(R.id.spinOrigen);
        spinDestino=findViewById(R.id.spinDestino);

        rgHoras=findViewById(R.id.rgHoras);
        rb1=findViewById(R.id.rb1);
        rb2=findViewById(R.id.rb2);
        rb3=findViewById(R.id.rb3);
        rb4=findViewById(R.id.rb4);
        rb5=findViewById(R.id.rb5);
        //QUE LOS SPINNERS TENGAN CONTENIDO
        final String[] arr= new String [] {"Bilbo" ,"Donostia","Gasteiz", "Laguardia", "Lekeitio", "Santurtzi", "Laudio"} ;
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arr);
        adaptador.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinOrigen.setAdapter(adaptador);
        spinDestino.setAdapter(adaptador);

        arrradio= new RadioButton[]{rb1, rb2, rb3, rb4, rb5};

    }
    public void cancelar(View view){
        Intent intent = new Intent(Actividad1.this, MainActivity.class);
        startActivity(intent);
    }
    public void aceptar(View view){
        Intent intent = new Intent(Actividad1.this, Actividad1_1.class);
        intent.putExtra("fecha",txtFecha.getText().toString() );
        intent.putExtra("nom",txtNombre.getText().toString() );
        intent.putExtra("apellido",txtApellidos.getText().toString() );

        intent.putExtra("origen",spinOrigen.getSelectedItem().toString() );
        intent.putExtra("destino",spinDestino.getSelectedItem().toString() );

        for (int i = 0; i <arrradio.length; i++) {
            if(arrradio[i].isChecked()){
                intent.putExtra("hora",arrradio[i].getText().toString());
            }
        }
        startActivityForResult(intent, 1235);
    }
    protected void onActivityResult(int requestCode,int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1235 && resultCode == RESULT_OK) {
            txtFecha.setText("");
            txtFecha.setHint("dd/mm/aaaa");

            txtNombre.setText("");
            txtApellidos.setText("");

            spinDestino.setSelection(0);
            spinOrigen.setSelection(0);
            for (int i = 0; i <arrradio.length; i++) {
                if(arrradio[i].isChecked()){
                    arrradio[i].setChecked(false);
                }
            }

        }
        if (requestCode == 1235 && resultCode == RESULT_CANCELED) {

        }
    }
}
