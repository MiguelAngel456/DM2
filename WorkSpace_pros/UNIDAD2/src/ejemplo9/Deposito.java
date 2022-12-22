package ejemplo9;

public class Deposito {

    private int elementos = 0;

    public synchronized void guardar() {
         if ( elementos == 0 )
            elementos++;
             System.out.println("Guardar:" + elementos);
         return;
        }  
    
    public synchronized void sacar() {
        if( elementos > 0 )
            elementos--;
             System.out.println("Sacar:" + elementos);
        return;
}
}

