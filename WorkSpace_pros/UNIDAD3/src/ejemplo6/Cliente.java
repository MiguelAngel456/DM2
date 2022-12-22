package ejemplo6;

//Cliente.java:
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

  public static void main (String[] args) {

      String Host="localhost";

      int Puerto = 6000;

      System.out.println("PROGRAMA CLIENTE INICIANDO");

      try {

          Socket Cliente = new Socket (Host, Puerto);
          
          /////// Flujo de salida al servidor
          DataOutputStream flujoSalida = new DataOutputStream (Cliente.getOutputStream());

          ////// Envío de un saludo al servidor
          flujoSalida.writeUTF("Saludos al servidor desde el cliente");
          
          //// Creación del flujo de entrada al servidor
          DataInputStream flujoEntrada = new DataInputStream (Cliente.getInputStream());

          ////// El servidor envía un mensaje al cliente
          System.out.println("Recibiendo mensaje del servidor: \n\t" + flujoEntrada.readUTF());

          ////// Cerrar streams y sockets
          flujoEntrada.close();
          flujoSalida.close();
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

