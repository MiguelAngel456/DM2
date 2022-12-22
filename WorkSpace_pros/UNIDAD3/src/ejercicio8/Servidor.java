package ejercicio8;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

public class Servidor {

	public static void main (String[] args) 
	{		
		int puerto = 12358;
		byte buffer[] = new byte[1024];
		
		try 
		{
			//RECIBIMOS EL EL NUMERO DEL CLIENTE
			DatagramSocket socket = new DatagramSocket(puerto);
			System.out.println("Esperando datagrama...");
			DatagramPacket recibo = new DatagramPacket(buffer, buffer.length);
			socket.receive(recibo);
			
			//LO PASAMOS A INT
			ByteBuffer bbRecibo = ByteBuffer.wrap(buffer);
			int numRecibido = bbRecibo.getInt();
			
			//PONEMOS LOS MENSAJES
			System.out.println("Vamos a calcular el cubo de: " + numRecibido);
			System.out.println("IP de origen: " + recibo.getAddress().getHostAddress());
			System.out.println("Puerto de origen: " + recibo.getPort());
			
			//PARA EL RESULTADO
			int resultado = (int)(Math.pow(numRecibido, 2));
			ByteBuffer bbEnvio = ByteBuffer.allocate(4);
			bbEnvio.putInt(resultado);
			byte bufferResultado[] = bbEnvio.array();
			DatagramPacket envio = new DatagramPacket(bufferResultado, bufferResultado.length, recibo.getAddress(), 34571);
		
			socket.send(envio);
			System.out.println("Enviamos el resultado...");
			
			System.out.println("Adiósss");
			socket.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	} 
}

