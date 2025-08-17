package UD2.ejemplos;

public class ControlHilos {
    public static void main(String[] args) throws InterruptedException {
        Thread hilo1 = new Thread(new Calculador(), "Hilo rápido");
        Thread hilo2 = new Thread(new Calculador(), "Hilo lento");

        hilo1.setPriority(Thread.MAX_PRIORITY);
        hilo2.setPriority(Thread.MIN_PRIORITY);

        hilo1.start();
        hilo2.start();

        hilo1.join();
        hilo2.interrupt();

        System.out.println("Programa finalizado");
    }
}
