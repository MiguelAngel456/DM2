package unidad_1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Actividad11 {
	public static void main(String[] args){

        Runtime r=Runtime.getRuntime();
        String comando="java unidad_1/Ejemplo2"; // Para un Linux o un MAC
        Process p=null;
        try {
            p = r.exec (comando);
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader (new InputStreamReader(is));
            
            String linea;
            while((linea = br.readLine()) != null) // lee una linea
                System.out.println(linea);
            br.close();
            }
        catch (Exception e) {
                e.printStackTrace();
        }
    }

}
