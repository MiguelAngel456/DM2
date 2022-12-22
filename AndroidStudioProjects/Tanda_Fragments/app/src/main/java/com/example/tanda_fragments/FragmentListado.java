package com.example.tanda_fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentListado extends Fragment {
    private Manga[] datos = new Manga[] {
            new Manga("Solo leveling", "Sung Jinwoo es un cazador de rango E, considerado como el nivel más bajo dentro de la Asociación de cazadores, por lo que se le conoce como “El cazador más débil”. Día tras día pone en peligro su vida y se embarca en misiones del rango más bajo para poder sufragar los gastos de su madre enferma; sin embargo, en una de esas misiones, lo que en un principio parecía un raid de rango D termina por ser una mazmorra de un nivel inusitado y las cosas empiezan a torcerse… ¿Lograrán Sung y sus compañeros salir con vida? ¡Una historia llena de acción y monstruos al más puro estilo RPG!"),
            new Manga("Tokyo Ghoul","presenta una ciudad de Tokio en la que mueren personas inocentes a manos de ghouls, seres misteriosos que se alimentan de humanos. El protagonista es Kaneki Ken, un joven corriente que sobrevive fortuitamente al ataque de una ghoul. Sin embargo, para salir adelante le trasplantaron los órganos de la ghoul de la que fue víctima. Por ello, su vida cambia radicalmente al convertirse en un híbrido entre humano y ghoul."),
            new Manga("Killing Beast", "Killing Bites se trata de duelos entre seres mitad humano, mitad bestia. Dónde personas en todos lados del mundo apuestan por su favorito. Acá entra Nomoto, quién por cosas del destino quedará inmerso en este mundo, al lado de Hitomi, la Tejón."),
            new Manga("Kegan Ashura","Dado que los períodos Edo de Japón, existen arenas de gladiadores en ciertas áreas. En estos escenarios, los empresarios ricos y comerciantes contratan gladiadores luchar en combate sin armas, donde el ganador se lleva todo. Toki Taouma, apodado \"Ashura\", se une a estas arenas y devasta sus opositores. Su espectacular habilidad para aplastar a sus enemigos llama la atención de los grandes propietarios de negocios, entre ellos el presidente Nogi Group, Nogi Hideki. "),
            new Manga("Emperador Magico","Zhuo Yifan es un emperador mágico o puede llamarse un emperador demonio, porque tiene un antiguo libro del emperador que se llama el \"Libro de los Nueve Secretos\", fue atacado por los siete emperadores e incluso su discípulo lo traicionó. Y para no dejar que obtengan el libro Zhuo Yifan condujo el asesinato de su cuerpo así mismo del libro. Luego, su alma entra y vuelve a la vida en un criado familiar llamado Zhuo Fan. Debido a que una magia demoníaca lo está frenando, debe unir los recuerdos del niño y no puede ignorar a la familia y la amante a la que sirve. ¿Cómo es su vida reconstruyendo a su familia y volviendo a ser el más fuerte del continente...?"),
            new Manga("isekai shikkaku","Al igual que en \"No Longer Human\" (Ningen Shikkaku), el protagonista de esta historia intenta un doble suicidio con su amante ... pero es golpeado por un camión y convocado a otro mundo."),
            new Manga("LOs clase S que crie","Un hermano mayor y cazador de clase F que no puede alcanzar a su increíble hermano pequeño de clase S. Después de una serie de desdichas que lo llevaron a la desgracia arruinando su vida y alejándolo de su hermano, decidió convertirse en el \"cuidador perfecto\". Así, esta vez, en lugar de preocuparse sólo por él, apoyará silenciosamente a aquellos que son más capaces. Es lo que pensaba ... pero los de clase S son un poco ... raros.")
    };
    private ListView lstListado;
    private CorreoListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lstListado = (ListView)getView().findViewById(R.id.lstListado);
        lstListado.setAdapter(new AdaptadorCorreos(this));
//Asignamos el evento onItemClick() a la lista de los correos
        lstListado.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                if (listener != null)
                    listener.onCorreoSeleccionado(
                            (Manga)lstListado.getAdapter().getItem(position));
            }
        });
    }
    class AdaptadorCorreos extends ArrayAdapter<Manga> {
        Activity context;
        AdaptadorCorreos(Fragment context) {
            super(context.getActivity(), R.layout.listitem_manga, datos);
            this.context = context.getActivity();
        }
        @NonNull
        @Override
        public View getView(int position,
                            @Nullable View convertView,
                            @NonNull ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem_manga, null);
            ImageView img =item.findViewById(R.id.imgPortada);
            String resouceImg="R.drawable.img"+position;
//            int a=position
            img.setImageResource(R.drawable.img0);
            TextView lblAsunto = (TextView)item.findViewById(R.id.lblAsunto);
            lblAsunto.setText(datos[position].getAsunto());
            return (item);
        }
    }

    // public interface CorreoListener {
// void onCorreoSeleccionado(Correo c);
// }
    public void setCorreoListener (CorreoListener listener){
        this.listener = listener;
    }
}
