package ejercicio10;

public class Actividad10 extends Thread{
	//Método que asigna la prioridad recibida al thread indicado
		private static void asignarPrioridad(Thread thread, int prioridad)
		{
			thread.setPriority(prioridad);
		}
		
		public void run(){
			//Instanciamos los Hilos
			Hilo hilo1 = new Hilo();			
			Hilo hilo2 = new Hilo();
			
			//con los hilos hacemos los thread
			Thread thread1 = new Thread(hilo1);	
			Thread thread2 = new Thread(hilo2);	
			
			//Le damos el nombre a cada Thread
			setName("main");
			thread1.setName("Thread-0");
			thread2.setName("Thread-1");
			
			//les damos la prioridad
			//this.setPriority(5);
			this.asignarPrioridad(thread1, 3);
			this.asignarPrioridad(thread2, 7);
			
			//Mostramos por pantalla información
			System.out.println(getName() + " tiene la prioridad " + getPriority());
			System.out.println(thread1.getName() + " tiene la prioridad " + thread1.getPriority());
			System.out.println(thread2.getName() + " tiene la prioridad " + thread2.getPriority());
			
			//Enviamos a los hilos la información
			hilo1.mensaje("Ejecutando Hilo-prioridad " + thread1.getPriority());
			hilo2.mensaje("Ejecutando Hilo-prioridad " + thread2.getPriority());
			
			//Iniciamos la ejecución
			thread1.start();									
			thread2.start();									
					
			try 
			{
				thread1.join();									
				thread2.join();									
			} 
			//Controlamos una posible excepción
			catch (InterruptedException e) 						
			{
				System.out.println(e.getMessage());
			}
			System.out.print("Final programa\n");
		}
		
		public static void main(String arg[]){
			Actividad10 prueba = new Actividad10();
			prueba.start();
		}
}
