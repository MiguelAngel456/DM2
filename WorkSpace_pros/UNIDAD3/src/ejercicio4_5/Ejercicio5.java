package ejercicio4_5;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class Ejercicio5 {
	private static void visualizarConexion(URLConnection con)
	{
		System.out.println("Conexión con " + con.getURL().getHost());
		System.out.println("==========================");
		System.out.println("\tMétodo toString(): " + con.toString());
		System.out.println("\tMétodo getDate(): " + con.getDate());
		System.out.println("\tMétodo getContentType(): " + con.getContentType());
		
		System.out.println("\nCampos Cabecera con getHeaderField:");
		System.out.println("==========================");
		System.out.println("\tLínea 1: " + con.getHeaderField(0));
		System.out.println("\tLínea 2: " + con.getHeaderField(1));
		System.out.println("\tLínea 3: " + con.getHeaderField(2));
		System.out.println("\tLínea 4: " + con.getHeaderField(3));
		System.out.println("\tLínea 5: " + con.getHeaderField(4));
		
		System.out.println("\nCampos Cabecera con getHeaderFields:");
		System.out.println("==========================");
		System.out.println("Keep-Alive: " + con.getHeaderField("Keep-Alive"));
		System.out.println("null: " + con.getHeaderField(null));
		System.out.println("Server: " + con.getHeaderField("Server"));
		System.out.println("Connection: " + con.getHeaderField("Connection"));
		System.out.println("Content-Length: " + con.getHeaderField("Content-Length"));
		System.out.println("Date: " + con.getHeaderField("Date"));
		System.out.println("Content-Type: " + con.getHeaderField("Content-Type"));
		System.out.println("Location: " + con.getHeaderField("Location"));
		
		System.out.println();
	}

	public static void main(String[] args) 
	{
		try 
		{
			URL url = new URL("http", "www.vitoria-gasteiz.org", 80, "");
			URLConnection con = url.openConnection();
			visualizarConexion(con);
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}
