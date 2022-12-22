package ejercicio1;

public class Hilo1 {
	public static void main(String[] args) {
		Contable c=new Contable("Primero");
		Contable c2=new Contable("Segundo");
		c.start();
		c2.start();
		System.out.print("Fin programa");
	}
}
