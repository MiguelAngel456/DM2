package com.example.tanda2miguel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class actividad5 extends AppCompatActivity {
    private Button btnS,btnB;
    private ImageButton btnL;
    private TextView temp;
    int turno=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad5);

        btnB = findViewById(R.id.bajar);
        btnS = findViewById(R.id.subir);
        btnL = findViewById(R.id.btnluz);
        temp = findViewById(R.id.temp);
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num=Integer.parseInt(temp.getText().toString());
                num=num+1;
                temp.setText(String.valueOf(num));

            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num=Integer.parseInt(temp.getText().toString());
                num=num-1;
                temp.setText(String.valueOf(num));

            }
        });
        btnL.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            if(turno%2==0){
                btnL.setImageResource(R.drawable.be);
                turno--;
            }else{
                btnL.setImageResource(R.drawable.ba);
                turno++;
            }


            }
        });


    }




}
