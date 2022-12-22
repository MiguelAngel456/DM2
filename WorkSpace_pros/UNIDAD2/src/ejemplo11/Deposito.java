package ejemplo11;

class Deposito{

    private int elementos = 0;

    public synchronized void guardar() {

        try{ 

            while ( elementos > 0) {
            	
            	 this.wait();
            }

        } catch( InterruptedException e ){}

        elementos++;

        System.out.println( "Guardar - numero elementos: " + elementos );

        this.notify();

    }

    public synchronized void sacar() { 

        try{ 

            while( elementos == 0) {

                this.wait();
            }

        } catch( InterruptedException e ){}

        elementos--;

        System.out.println( "Sacar - numero elementos: " + elementos );

        this.notify();
    } 
}  

