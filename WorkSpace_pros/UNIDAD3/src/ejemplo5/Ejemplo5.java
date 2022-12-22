package ejemplo5;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ejemplo5 {

    public static void main (String[]args){

        String Host="localhost";

        int Puerto = 6000;

        try {

            // Abrimos Socket
            Socket Cliente = new Socket (Host, Puerto);

            InetAddress i = Cliente.getInetAddress();

            System.out.println("Puerto local: "+ Cliente.getLocalPort());

            System.out.println("Puerto Remoto: "+ Cliente.getPort());

            System.out.println("Host Remoto: "+ i.getHostName());

            System.out.println("IP Host Remoto: "+ i.getHostAddress().toString());

            Cliente.close();

        } catch (UnknownHostException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        } catch (IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }
    }
}
