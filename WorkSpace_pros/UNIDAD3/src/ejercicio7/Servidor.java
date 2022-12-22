package ejercicio7;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main (String[] args) throws IOException{
		int puerto = 6013;
		ServerSocket servidor = new ServerSocket (puerto, 3);
		
		System.out.println("Esperando a los clientes...");
		
		for(int cont = 0; cont < 3; cont++){
			Socket cli = servidor.accept();

			DataOutputStream dos = new DataOutputStream (cli.getOutputStream());
			dos.writeUTF("Hola cliente " + (cont+1));
		
			dos.close();
		}
		servidor.close();
	} 
}
