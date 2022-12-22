package ejercicio4;

public class Posicion implements Runnable{
	String pos;
	
	Posicion(String pos){
		this.pos=pos;
	}
	public void run() {
		for (int i=1;i<15;i++)
		System.out.println( pos+" "+ i );
		}

}
