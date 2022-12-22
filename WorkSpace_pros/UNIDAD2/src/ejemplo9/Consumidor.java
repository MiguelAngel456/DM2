package ejemplo9;
public class Consumidor extends Thread{

    private Deposito deposito;

    public Consumidor(Deposito d) {
        deposito = d; 
    }

    public void run() {
        for (int i=1;i<20 ;i++ )
            deposito.sacar();
    } 
}

