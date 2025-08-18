package UD2.ejemplos;

public class ContadorApp {
    public static void main(String[] args) throws InterruptedException {
        ContadorSeguro contador = new ContadorSeguro();
        Thread[] hilos = new Thread[1000];
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(() -> contador.incrementar());
            hilos[i].start();
        }
        for (Thread h : hilos) 
        	h.join();
        
        System.out.println("Cuenta final: " + contador.getCuenta());
    }
}
