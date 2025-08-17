package UD2.ejemplos;
public class MiHilo extends Thread {
    @Override
    public void run() {
        System.out.println("Hola, soy el hilo " + getName());
    }

    public static void main(String[] args) {  
    	MiHilo m = new MiHilo();
    	Thread hilo = new Thread(m);
    	hilo.start();
    	
    	// directamente, sin necesidad de otra clase:
    	Thread thread = new Thread(){
    		  public void run(){
    		    System.out.println("Hola, soy el hilo "  + getName());
    		  }
    		};
    	thread.start();
    }
}         
