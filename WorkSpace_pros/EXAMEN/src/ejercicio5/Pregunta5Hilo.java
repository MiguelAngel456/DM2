package ejercicio5;

public class Pregunta5Hilo extends Thread{
	
	private String m;
	
	public Pregunta5Hilo(String m) {
		this.m = m;
	}

	public void run() {
		for (int i = 1; i <= 3; i++) {
			System.out.println(m+" "+i);
		}
	}
}
