package ejemplo7_1;

public class Hilos extends Thread{
	String mensaje;
    Historial historial;
    
    public Hilos (String msg, Historial h){
        mensaje = msg;
        historial = h;
    }
    
    public void run() {
        for (int i=1;i<=20;i++){
                historial.agregar( mensaje );
                this.yield();
        }
    }

}
