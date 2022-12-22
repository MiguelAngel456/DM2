package ejercicio8;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class Cliente {


	public static void main (String[] args) 
	{
		int puerto = 12358;
		byte buffer[] = new byte[1024];
		byte bufferRecibo[] = new byte[1024];
		
		try 
		{
			InetAddress destino = InetAddress.getLocalHost();
			//NUMERO ALEATORIO
			int randomNum = (int)(-128 + Math.random() * 256); 
			//PREPARAMOS PARA EL ENVIO
			ByteBuffer bbEnvio = ByteBuffer.allocate(4);
			bbEnvio.putInt(randomNum);
			buffer = bbEnvio.array();
			
			DatagramPacket envio = new DatagramPacket(buffer, buffer.length, destino, puerto);
			DatagramSocket socket = new DatagramSocket(34571);
			//ENVIAMOS
			socket.send(envio);
			System.out.println("Esperando respuesta...");
			//RECIBIMOS
			DatagramPacket recibo = new DatagramPacket(bufferRecibo, bufferRecibo.length);
			socket.receive(recibo);
			
			ByteBuffer bbRecibo = ByteBuffer.wrap(bufferRecibo);
			System.out.println("Esperando respuesta...: el cubo de " + randomNum + " es " + bbRecibo.getInt());
			
			System.out.println("Adiós...");
			socket.close();
		} 
		catch (UnknownHostException e)
		{
			System.out.println(e.getMessage());
		} 
		catch (SocketException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}

