package ejemplo7;

public class Historial {
	String[] mensajes = new String[1000];
    int pos = 0;
    
    public void agregar(String msg) {
        mensajes[pos] = msg;

        //System.out.println(pos+"\n\t"+mensajes[pos]);
        System.out.println("mensaje["+pos+"]= "+mensajes[pos]+"\n");
        pos++;        
        
    }

}
