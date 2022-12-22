package ejemplo1;

import java.net.InetAddress;
import java.net.UnknownHostException;

class Ejemplo1 {
    public static void main( String[] args ) { 
        InetAddress address;
        try {
            System.out.println( "Direccion IP de una URL, por nombre" ); 
            address = InetAddress.getByName( "www.google.com" );
            System.out.println( address );
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            System.out.println( "Direccion IP actual de LocalHost" );
            address = InetAddress.getLocalHost();
            System.out.println( address );
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
}
