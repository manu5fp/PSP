package UD2.ejemplos;

/**
 * Ejemplo de creación y lanzamiento de hilos en Java.
 *
 * En este programa se muestran dos formas distintas de crear y ejecutar hilos:
 * 
 *  1. A partir de una clase que hereda de Thread y redefine el método run().
 *  2. Directamente, mediante una clase anónima que redefine run().
 *
 * La salida por consola permitirá comprobar qué hilo está en ejecución en cada caso.
 * 
 *  @author manu
 */
public class MiHilo extends Thread {
    
    /**
     * Método que define la tarea que ejecutará el hilo cuando se llame a start().
     * Aquí simplemente se imprime un mensaje identificando el hilo actual.
     */
    @Override
    public void run() {
        System.out.println("Hola, soy el hilo " + getName());
    }

    /**
     * Método principal: crea y lanza diferentes hilos utilizando enfoques variados.
     */
    public static void main(String[] args) {  
        
        // 1) Crear un objeto de la clase MiHilo y lanzarlo como un nuevo hilo.
        MiHilo m = new MiHilo();
        Thread hilo = new Thread(m);
        hilo.start();
        
        // 2) Crear y lanzar un hilo directamente mediante una clase anónima.
        Thread thread = new Thread(){
            @Override
            public void run(){
                System.out.println("Hola, soy el hilo "  + getName());
            }
        };
        thread.start();
    }
}
