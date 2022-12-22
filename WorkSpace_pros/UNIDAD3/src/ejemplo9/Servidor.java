package ejemplo9;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Servidor {

    public static void main (String[] args) throws ClassNotFoundException {
        int Puerto = 6000;
        ServerSocket servidor;
        try {
            servidor = new ServerSocket (Puerto);
            System.out.println("ESPERANDO AL CLIENTE... ");
            Socket cliente = null;
            cliente = servidor.accept();

            // Flujo de salida hacia el cliente
            ObjectOutputStream outObjeto = new ObjectOutputStream (cliente.getOutputStream()); 
            
            // Creación del objeto y envío al cliente
            Persona per = new Persona ("Juan",20);
            outObjeto.writeObject(per);
            System.out.println("Envío: "+per.getNombre()+"*"+per.getEdad());
            
            // Flujo de entrada para leer el objeto
            ObjectInputStream inObjeto = new ObjectInputStream (cliente.getInputStream());
            Persona dato =(Persona) inObjeto.readObject();
            System.out.println("Recibo: "+dato.getNombre() + "*" + dato.getEdad());

            // Cierre de streams y sockets
            outObjeto.close();
            inObjeto.close();
            servidor.close();
        
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
}
