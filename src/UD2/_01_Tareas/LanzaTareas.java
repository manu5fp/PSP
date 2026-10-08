package UD2._01_Tareas;

/**
 * Clase lanzadora que crea y gestiona varios hilos con tareas complejas.
 * 
 * Su objetivo es mostrar cómo se pueden iniciar múltiples hilos en paralelo
 * y esperar a que todos terminen antes de continuar la ejecución del programa.
 * 
 * @author manu
 */
public class LanzaTareas {

    public static void main(String[] args) throws InterruptedException {
        final int NUM_HILOS = 5;      // Número total de hilos a lanzar
        Thread[] hilos = new Thread[NUM_HILOS]; // Array para almacenar las referencias

        // Creación y arranque de los hilos
        for (int i = 0; i < NUM_HILOS; i++) {
            // Cada hilo ejecuta una nueva instancia de TareaCompleja
            hilos[i] = new Thread(new TareaCompleja());
            hilos[i].setName("Hilo-" + i); // Asignamos un nombre identificativo

            // Ejemplo opcional de ajuste de prioridades (comentado por defecto):
            // if (i == 1 || i == 2)
            //     hilos[i].setPriority(Thread.MAX_PRIORITY);
            // else
            //     hilos[i].setPriority(Thread.MIN_PRIORITY);

            hilos[i].start(); // Inicia la ejecución del hilo
        }

        // Esperar a que todos los hilos terminen
        for (Thread h : hilos) {
            h.join(); // El hilo principal se bloquea hasta que "h" finalice
        }

        // Solo se llega aquí cuando todos los hilos han completado su tarea
        System.out.println("Todos los hilos han terminado.");
    }
}
