package ejemplo5;

public class Primero implements Runnable {
    public void run() {
        for (int i=1;i<10;i++)
            System.out.println( "Primero " + i );
            Thread.yield();
        }
}
