package ejemplo7_1;

public class Historial {
	String[] mensajes = new String[1000];
    int pos = 0;
    
    public synchronized void agregar(String msg) {
        mensajes[pos] = msg;
        
        System.out.println("mensaje["+pos+"]= "+mensajes[pos]+"\n");
        pos++;        
        
    }

}
