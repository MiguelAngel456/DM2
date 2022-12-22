package ejercicio15;

import java.util.Calendar;

class Hilo extends Thread { 

    Recurso a; 
    Recurso b; 
    Recurso c;
    
    public Hilo(Recurso a, Recurso b,Recurso c,String nombre) { 
        super(nombre); 
        this.a = a; 
        this.b = b; 
        this.c=c;
    } 

    public void run(){ 
    	for (int i = 0; i < 5; i++) {
    		//System.out.println("----------------------------------------"+i);
    		Calendar calendario = Calendar.getInstance();
    		int hora =calendario.get(Calendar.HOUR_OF_DAY);
    		int minutos = calendario.get(Calendar.MINUTE);
    		int segundos = calendario.get(Calendar.SECOND);
    		
    		 System.out.println("Hilo " + this.getName() + " - "+hora+":"+minutos+":"+segundos); 
    	        synchronized(a) { 
    	        	
	    	        synchronized(b) {
	    	        	
		    	        synchronized(c) {
		    	        	
		    	        } 
	
	    	        } 
    	        } 
    		try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
       
    } 
}
class Recurso{ 
}
