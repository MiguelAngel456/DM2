package ejemplo10;

class Deposito{

    private int elementos = 0;

    public synchronized void guardar() {

        try{ 

            if( elementos > 0) { // Mas adelante se verá que no es correcto

                this.wait();
            }

        } catch( InterruptedException e ){}

        elementos++;

        System.out.println( "Guardar - numero elementos: " + elementos );

        this.notify();

    }

    public synchronized void sacar() { 

        try{ 

            if( elementos == 0) { // Mas adelante se verá que no es correcto

                this.wait();
            }

        } catch( InterruptedException e ){}

        elementos--;

        System.out.println( "Sacar - numero elementos: " + elementos );

        this.notify();
    } 
}
