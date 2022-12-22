package ejercicio11;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main (String[] args) throws IOException {

		int Puerto = 6004;

		ServerSocket servidor = new ServerSocket (Puerto);

		while (true) {

			Socket cliente= servidor.accept();

			System.out.println("Cliente Conectado.....");

			HiloServidor hilo = new HiloServidor (cliente);

			hilo.start();

		}

//	servidor.close();
//
//	cliente.close();

  }

}
