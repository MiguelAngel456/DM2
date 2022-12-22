package com.example.main_ejercicio;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements InicioSession.OnDialogoConfirmacionListener, Cerrar.OnDialogoConfirmacionListener{
    private ListView list;
    private Clientes[] arrClientes;
    private InicioSession dialogLogin;
    private Cerrar dialogCerrar;
    private Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=findViewById(R.id.listClientes);
        btnSalir=findViewById(R.id.btnSalir);
        arrClientes=new Clientes[] { new Clientes("Juan Carlos Perez",150 ),
                    new Clientes("Cristian Roman",254 ),
                    new Clientes("Adrian Garcia",25 ),
        };

        //Adaptador
        AdaptadorCliente adapt =
                new AdaptadorCliente(this, arrClientes);
        list.setAdapter(adapt);
        showLoginInicio();
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCerrar();
            }
        });

    }
    private void showLoginInicio() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        dialogLogin = new InicioSession();
        dialogLogin.show(fragmentManager, "Iniciar Session");
    }
    private void showCerrar() {
        FragmentManager fragmentManager = getSupportFragmentManager();
            dialogCerrar = new Cerrar();
        dialogCerrar.show(fragmentManager, "Cerrar");
    }

    class AdaptadorCliente extends ArrayAdapter<Clientes> {
        public AdaptadorCliente(@NonNull Context context, Clientes[] Clientes) {
            super(context, R.layout.list_item, Clientes);
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView,
                            @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.list_item, null);
            TextView nombre = (TextView)item.findViewById(R.id.lblnombreApe);
            nombre.setText(arrClientes[position].getNombreApe());
            TextView deuda = (TextView)item.findViewById(R.id.lblDeuda);
            deuda.setText("Deuda: "+arrClientes[position].getSuperficie()+"€");

            return (item);
        }
    }
//**************************************************************************************************

    public void onPossitiveButtonClick() {checklogin();}
    private void checklogin() {
        String username = ((EditText) dialogLogin.getDialog().findViewById(R.id.txtUsuario)).getText().toString();
        String password = ((EditText) dialogLogin.getDialog().findViewById(R.id.txtCOntraseña)).getText().toString();
        Log.println(Log.DEBUG, "username", username);
        Log.println(Log.DEBUG, "password", password);
        if (username.equals("usuario1") && password.equals("123456")) {
            Log.println(Log.DEBUG, "BIEN", "Login correcto");
        } else {
            Log.println(Log.DEBUG, "MAL", "Login incorrecto");
            finish();
        }
    }


}