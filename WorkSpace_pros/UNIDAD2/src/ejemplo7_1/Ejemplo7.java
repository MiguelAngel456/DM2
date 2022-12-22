package ejemplo7_1;

public class Ejemplo7 {
	public static void main(String arg[]) {

        Historial historial= new Historial();
        Hilos p = new Hilos("Primero", historial);
        Hilos s = new Hilos("Segundo", historial);
        p.start();
        s.start();
    
    }

}
