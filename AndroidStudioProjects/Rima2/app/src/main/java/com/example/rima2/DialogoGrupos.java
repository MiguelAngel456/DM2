package com.example.rima2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogoGrupos extends DialogFragment {
    private final String[] alumn;

    public DialogoGrupos(String[] alumnos) {
        alumn=alumnos;
    }

    @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            //OPCION 1:Sólo se puede elegir una opción
            builder.setTitle("Grupos:")
                    .setItems(alumn, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
//                            Log.i ("Dialogos", "Opcion elegida: "+alumn[item]);
//                            Toast.makeText(getActivity(), "Has seleccionado "+
//                                    alumn[item], Toast.LENGTH_SHORT).show();
                        }
                    })
                    //Añadimos un botón de aceptar
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            return builder.create();
        }


}
