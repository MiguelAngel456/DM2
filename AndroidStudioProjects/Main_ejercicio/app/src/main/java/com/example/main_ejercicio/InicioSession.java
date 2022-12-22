package com.example.main_ejercicio;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
public class InicioSession extends DialogFragment {
    private InicioSession.OnDialogoConfirmacionListener listener;
    public interface OnDialogoConfirmacionListener{
        void onPossitiveButtonClick(); //Eventos Botón Positivos
    }
    public void onAttach(Context context)
    {
        super.onAttach(context);
        try
        {
            listener = (OnDialogoConfirmacionListener) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString() + " no Implemento OnDialogoConfirmacionListener");
        }
    }
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            setCancelable(false);
            builder.setView(inflater.inflate(R.layout.inicio_session, null))
                    //Añadimos un botón de aceptar
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            listener.onPossitiveButtonClick();
                        }
                    });
                    //Añadimos un botón de cancelar
//                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            listener.onNegativeButtonClick();
//                            dialog.cancel();
//                        }
//                    });
            return builder.create();
        }

}


