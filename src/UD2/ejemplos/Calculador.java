package UD2.ejemplos;

public class Calculador implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " calculando..." + i);
            try {
                Thread.sleep((long) (Math.random() * 2000));
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrumpido");
                return;
            }
        }
        System.out.println(Thread.currentThread().getName() + " finalizado.");
    }
}
