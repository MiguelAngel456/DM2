package ejercicio15;

public class Actividad15 {
	public static void main(String args[]) {
		Recurso a = new Recurso(); 
		Recurso b = new Recurso(); 
		Recurso c = new Recurso();
		Hilo h1 = new Hilo(a, b, c,"1"); 
		Hilo h2 = new Hilo(a, b, c,"2"); 
		Hilo h3 = new Hilo(a, b, c,"3");
		
		h1.start(); 
		h2.start();
		h3.start(); 
	}
}
