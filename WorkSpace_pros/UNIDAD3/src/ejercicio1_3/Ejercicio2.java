package ejercicio1_3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;

public class Ejercicio2 {
	 public static void main( String[] args ) { 
	        InetAddress address[];
	        if(args[0].equals("")) {
	        	System.out.println("Se necesita una URL para obtener su direccion");
	        }else {
	        	try {
		            System.out.println( "Direccion asociadas a "+args[0].substring(args[0].indexOf(".")+1,args[0].lastIndexOf(".")) ); 
		            address = InetAddress.getAllByName( args[0] );
		            
		            for (int i = 0; i < address.length; i++) {
		            	System.out.println( address[i] );
					}
		            
		        } catch (UnknownHostException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
	        }
	        

	 }
}
