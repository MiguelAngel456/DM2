package ejercicio2;

import ejercicio1.Contable;

public class Actividad2 {
		
	public static void main(String[] args) {
		int n=4;
		for (int i = 1; i <=n; i++) {
			Contable2 c=new Contable2(i);
			c.start();
		}
		
		System.out.print("Fin programa");
	}
}
