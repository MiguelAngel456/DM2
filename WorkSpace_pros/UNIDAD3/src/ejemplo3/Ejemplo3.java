package ejemplo3;
import java.io.*;
import java.net.*;

public class Ejemplo3 {

    public static void main(String argv[]) {

        URL url=null;

        try {

            url = new URL ("https://www.euskadi.eus");

            URLConnection conex = url.openConnection();

            System.out.println("Cargando "+url);

            BufferedReader in;

            DataInputStream datos = new DataInputStream(conex.getInputStream());

            in = new BufferedReader (new InputStreamReader(datos));

            String inputLine;

            while ((inputLine = in.readLine()) != null)

                System.out.println(inputLine);

            in.close();

        }

        catch (MalformedURLException e) {

            e.printStackTrace();

        }

        catch (UnknownHostException e) {

            System.out.println("El host no existe o no responde");

        }

        catch (Exception e) {

            e.printStackTrace();

        }
    }
}

