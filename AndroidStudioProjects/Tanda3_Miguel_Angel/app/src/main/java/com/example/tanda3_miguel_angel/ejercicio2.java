package com.example.tanda3_miguel_angel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ejercicio2 extends AppCompatActivity {
    private TextView num1,num2,result,correcto,mal;
    private Button btnComprobar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio2);


        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        result = findViewById(R.id.resultado);
        mal = findViewById(R.id.mal);
        btnComprobar = findViewById(R.id.btnComprobar);
        correcto = findViewById(R.id.correcto);

        num1.setText(String.valueOf((int)(Math.random()*101)));
        num2.setText(String.valueOf((int)(Math.random()*101)));

        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num1s =num1.getText().toString();
                    Intent intent = new Intent(ejercicio2.this, ejercicio2_1.class);
                    //intent.putExtra("num1",num1s );
                    intent.putExtra("num1",num1.getText().toString() );
                    intent.putExtra("num2",num2.getText().toString() );
                    intent.putExtra("result",result.getText().toString() );
                    startActivityForResult(intent, 1235);



            }
        });


    }
    protected void onActivityResult(int requestCode,int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1235 && resultCode == RESULT_OK) {
            String res = data.getExtras().getString("condicion");
            if(res.equals("mal")){
                int m=Integer.parseInt(mal.getText().toString());
                m++;
                mal.setText(String.valueOf(m));
            }else{
                int m=Integer.parseInt(correcto.getText().toString());
                m++;
                correcto.setText(String.valueOf(m));
            }

        }
    }
}
