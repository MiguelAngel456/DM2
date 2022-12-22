package ejemplo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Ejemplo2 {

	public static void main(String[] args) {

		try {

			// Obtener URL Absoluta
			// Base URL = www.gnu.org
			URL url1 = new URL("http://www.gnu.org");
			System.out.println("URL1: " + url1.toString());

			// Generar URL a partir de una URL base
			URL url2 = new URL(url1, "licenses/gpl.txt");
			System.out.println("URL2: " + url2.toString());

			// Generar URL a partir del protocolo, host y fichero
			URL url3 = new URL("http", "www.gnu.org", "/licenses/gpl.txt");
			System.out.println("URL3: " + url3.toString());

			// Generar URL a partir del protocolo, host, puerto y fichero
			URL url4 = new URL("http", "www.gnu.org", 80, "/licenses/gpl.txt");
			System.out.println("URL4: " + url4.toString() + "\n");

			// Abrir el buffer de lectura a partir de la URL

			try (BufferedReader in = new BufferedReader(new InputStreamReader(url4.openStream()))) {

				String inputLine;

				// Leemos el fichero "gpl.txt"

				System.out.println("/***** Contenido del fichero URL4 *****/n");

				while ((inputLine = in.readLine()) != null) {

					System.out.println(inputLine);

				}

			} catch (IOException ioe) {

				ioe.printStackTrace(System.err);

			}

		} catch (MalformedURLException mue) {

			mue.printStackTrace(System.err);

		}
	}
}
