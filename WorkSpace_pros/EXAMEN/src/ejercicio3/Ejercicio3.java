package ejercicio3;

public class Ejercicio3 {
		public static void main(String[] args) {

			Runtime r = Runtime.getRuntime();	
			
			String comando = "/bin/gnome-calendar";
					
			Process p;
			
			try {
				
				p = r.exec( comando );
			
			}
			catch (Exception e) {
				System.out.println("Error en: " + comando);
				e.printStackTrace();
			}
		}
}