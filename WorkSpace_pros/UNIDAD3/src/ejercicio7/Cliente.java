package ejercicio7;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente 
{

	public static void main (String[] args) 
	{
		String host = "localhost";
		int puerto = 6013;

		Socket cliente[] = new Socket[4];
		for(int i = 0; i < cliente.length; i++){
			try{
				System.out.println("PROGRAMA CLIENTE INICIANDO");
				Socket cliRecipiente = cliente[i];
				cliRecipiente = new Socket (host, puerto);
				
				System.out.println("Recibiendo mensaje del servidor:");
				DataInputStream dis = new DataInputStream(cliRecipiente.getInputStream());
				System.out.println(dis.readUTF());
				
				System.out.println();
				
				dis.close();
				cliRecipiente.close();
			} 
			catch (IOException e) 
			{
				System.out.println(e.getMessage());
			}
		}
	}
} 

