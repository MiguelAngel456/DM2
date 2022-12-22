package ejemplo10;

public class Ejemplo10 {

    public static void main( String[] arg ) {

        Deposito deposito = new Deposito();
        Productor productor = new Productor( deposito );
        Consumidor consumidor = new Consumidor( deposito );
        productor.start();
        consumidor.start();
    }
}

