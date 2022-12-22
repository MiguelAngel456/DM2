package ejercicio10;

public class Hilo extends Thread{
	private String mens;
	
	void mensaje(String mensj) {
		this.mens=mensj;
	}
	public void run()
	{
		System.out.println(mens);
	}
}
