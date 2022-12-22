package com.example.ev1_examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements DialogLogin.OnDialogoConfirmacionListener{

    private final String MSG_LOGIN_REQUIRED = "Usuario y contraseña necesarios para entrar en la aplicación";
    private final String MSG_LOGIN_INVALID = "Usuario y/o contraseña contraseña incorrectos. La aplicación se cerrará";
    private DialogLogin dialogLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showLogin();
    }

    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
    }

    private void showLogin() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        dialogLogin = new DialogLogin();
        dialogLogin.show(fragmentManager, "Login dialog");
    }

    @Override
    public void onPossitiveButtonClick() {
        checklogin();
    }

    @Override
    public void onNegativeButtonClick() {
       showExitAlert(MSG_LOGIN_REQUIRED);
    }

    private void checklogin() {
        String username = ((EditText) dialogLogin.getDialog().findViewById(R.id.editTextUsername)).getText().toString();
        String password = ((EditText) dialogLogin.getDialog().findViewById(R.id.editTextPassword)).getText().toString();
        Log.println(Log.DEBUG, "username", username);
        Log.println(Log.DEBUG, "password", password);
        if (username.equals("usuario") && password.equals("123456")) {
            Log.println(Log.DEBUG, "BIEN", "Login correcto");
        } else {
            Log.println(Log.DEBUG, "MAL", "Login incorrecto");
            showExitAlert(MSG_LOGIN_INVALID);
        }
    }

    private void showExitAlert(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¡¡¡ATENCIÓN!!!");
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void salir(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Información");
        builder.setMessage("Gracias y hasta pronto!");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void actividad1(View view){
        Intent intent = new Intent(this, Actividad1.class);
        startActivity(intent);
    }

    public void actividad2(View view){
        Intent intent = new Intent(this, Actividad2.class);
        startActivity(intent);
    }
}