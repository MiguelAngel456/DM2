package ejercicio4;

public class Ejercicio4 {
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 5; i++) {
				Ejercicio4Hilos p = new Ejercicio4Hilos();
				Ejercicio4Hilos s = new Ejercicio4Hilos();
				Ejercicio4Hilos t = new Ejercicio4Hilos();
				
				p.start();
				s.start();
				t.start();
			}
		} catch (Exception e) {}
	}
}
