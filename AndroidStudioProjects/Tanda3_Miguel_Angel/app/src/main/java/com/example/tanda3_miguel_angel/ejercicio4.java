package com.example.tanda3_miguel_angel;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ejercicio4 extends AppCompatActivity {
    private Button btnPregunta;
    private RadioButton rbNivel1,rbNivel2,rbNivel3;
    private TextView lblResp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio4);

        btnPregunta=findViewById(R.id.btPre);
        rbNivel1=findViewById(R.id.rbNivel1);
        rbNivel2=findViewById(R.id.rbNivel2);
        rbNivel3=findViewById(R.id.rbNivel3);
        lblResp=findViewById(R.id.lblconclusion);

        rbNivel1.setChecked(true);

        btnPregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nivel=0;
                if(rbNivel1.isChecked()){
                   nivel=1;
                }
                if(rbNivel2.isChecked()){
                    nivel=2;
                }
                if(rbNivel3.isChecked()){
                    nivel=3;
                }
                Intent intent = new Intent(ejercicio4.this, ejercicio4_1.class);
                intent.putExtra("niv",nivel);
                startActivityForResult(intent, 1235);
            }
        });

    }
    protected void onActivityResult(int requestCode,int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1235 && resultCode == RESULT_OK) {
            int  res = data.getExtras().getInt("resp");
            if(res==1){
                lblResp.setText("CORRECTO");
                lblResp.setTextColor(Color.GREEN);

            }else{
                lblResp.setText("INCORRECTO");
                lblResp.setTextColor(Color.RED);
            }
        }
    }
}
