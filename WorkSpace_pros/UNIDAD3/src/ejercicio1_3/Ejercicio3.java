package ejercicio1_3;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ejercicio3 {
	public static void main(String[] args){
		String direcciones="";
		
			try {
				String dir = direcciones;
				InetAddress address[] = InetAddress.getAllByName(direcciones);
				
				System.out.println("Dirección IP: " + address[0].getHostAddress());
				System.out.println("Nombre: " + address[0].getHostName());
				for (int i = 0; i < address.length; i++)
					System.out.println(address[i]);
				
			} 
			catch (UnknownHostException e) {
				InetAddress local;
				InetAddress localAddresses[];
				try 
				{
					local = InetAddress.getLocalHost();
					localAddresses = InetAddress.getAllByName(local.getHostName());
					
					System.out.println("Dirección IP: " + localAddresses[0].getHostAddress());
					System.out.println("Nombre: " + localAddresses[0].getHostName());
					for (int i = 0; i < localAddresses.length; i++)
						System.out.println(localAddresses[i]);
				} 
				catch (UnknownHostException f){
					f.printStackTrace();
				}
			}

	}
}
