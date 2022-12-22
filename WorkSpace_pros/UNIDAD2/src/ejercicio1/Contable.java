package ejercicio1;

public class Contable extends Thread{
	
	String pos;
	public Contable(String pos) {
		this.pos=pos;
	}
	public void run(){
		for (int i=1;i<100;i++) {
			System.out.println(pos+ i);
		}
	}
}

