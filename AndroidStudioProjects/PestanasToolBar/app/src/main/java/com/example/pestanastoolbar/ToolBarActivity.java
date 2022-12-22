package com.example.pestanastoolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;



public class ToolBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            super.onCreateOptionsMenu(menu);
            //getMenuInflater().inflate(R.menu.main_menu, menu);
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main_menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            switch (id){
                case R.id.action_buscar:
                    Toast.makeText(this, "Opción BUSCAR seleccionada", Toast.LENGTH_LONG).show();
                    return true;

                case R.id.action_nuevo:
                    Toast.makeText(this, "Opción NUEVO seleccionada", Toast.LENGTH_LONG).show();
                    return true;

                case R.id.action_actividad2:
                    Intent intent = new Intent(this, PestanasEj1.class);
                    startActivity(intent);
                    Toast.makeText(this, "Opción ACTIVIDAD 2 seleccionada", Toast.LENGTH_LONG).show();
                    return true;

                case R.id.action_actividad3:
                    Intent intent2 = new Intent(this, PestanasEj3.class);
                    startActivity(intent2);
                    Toast.makeText(this, "Opción ACTIVIDAD 3 seleccionada", Toast.LENGTH_LONG).show();
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }