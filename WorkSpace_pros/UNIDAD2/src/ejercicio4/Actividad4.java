package ejercicio4;

public class Actividad4 {
	public static void main(String arg[]) {
		Thread p = new Thread(new Posicion("Primero"));
		Thread p2 = new Thread(new Posicion("Segundo"));
		p.start();
		p2.start();
		System.out.println( "Fin programa ");
	}
}

