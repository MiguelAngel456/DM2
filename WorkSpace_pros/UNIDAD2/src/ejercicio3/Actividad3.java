package ejercicio3;

import ejercicio1.Contable;

public class Actividad3 {
		
	public static void main(String[] args) {
			Contable3 c=new Contable3();
		//	c.start();
			
			System.out.println("el nombre del hilo es  "+c.getName()+" y tiene prioridad "+c.getPriority());

			c.setName("SUPER-HILO-DM2");
			c.setPriority(6);
			System.out.println("Ahora el nombre del hilo es  "+c.getName()+" y tiene prioridad "+c.getPriority());
			
			System.out.print("Fin programa");
			
			
	}
}
