package ejemplo4;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejemplo4 {

    public static void main (String[]args){

        int Puerto = 6001;

        try {

            ServerSocket Servidor = new ServerSocket (Puerto);

            System.out.println("Hay un servidor escuchando en el puerto " + Servidor.getLocalPort());

            // Realiza la conexión con cliente 1
            Socket cliente1=Servidor.accept();

            // Realiza la conexión con cliente 2
            Socket cliente2=Servidor.accept();

            // Cierra la conexión
            Servidor.close();

        } catch (IOException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();

        } 
    }
}
