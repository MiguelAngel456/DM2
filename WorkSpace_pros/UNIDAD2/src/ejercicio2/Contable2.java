package ejercicio2;

public class Contable2 extends Thread{
	
	int n;
	public Contable2(int n) {
		this.n=n;
	}
	public void run(){
		for (int i=1;i<=3;i++) {
			System.out.println("Hilo "+ i);
		}
	}
}

