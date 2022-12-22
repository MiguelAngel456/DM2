package ejemplo9;

public class Productor extends Thread {

    private Deposito deposito;

    public Productor(Deposito d) {
        deposito = d; 
    }

    public void run() {
        for (int i=1;i<20 ;i++ )
            deposito.guardar(); 
        }
}

