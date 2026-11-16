package ud2.a3;


/**
 * Ejemplo que permite observar los seis estados de un hilo en Java:
 *
 * NEW:           hilo creado, pero todavía no iniciado.
 * RUNNABLE:      hilo preparado para ejecutarse o ejecutándose.
 * BLOCKED:       hilo esperando para conseguir un monitor.
 * WAITING:       hilo esperando indefinidamente a que otro actúe.
 * TIMED_WAITING: hilo esperando durante un tiempo determinado.
 * TERMINATED:    hilo cuya ejecución ha finalizado.
 */
public class EstadosHilo {

    // Objeto compartido que utilizaremos como monitor.
    private static final Object CERROJO = new Object();


    /**
     * Espera hasta que un hilo alcance el estado indicado.
     *
     * Consultamos periódicamente getState() porque los cambios
     * de estado se producen de forma concurrente.
     */
    private static void esperarEstado(Thread hilo, Thread.State estadoEsperado)
            throws InterruptedException {

        while (hilo.getState() != estadoEsperado) {
            Thread.sleep(5);
        }

        System.out.println(hilo.getName() + ": " + hilo.getState());
    }


    // ---------------------------------------------------------
    // HILO 1: TIMED_WAITING
    // ---------------------------------------------------------

    /**
     * Este hilo duerme durante 300 milisegundos.
     *
     * Durante ese tiempo estará en estado TIMED_WAITING.
     */
    static class HiloDurmiente implements Runnable {

        @Override
        public void run() {

            try {
                System.out.println("El durmiente comienza a dormir.");

                Thread.sleep(300);

                System.out.println("El durmiente termina de dormir.");

            } catch (InterruptedException e) {

                System.out.println("El durmiente ha sido interrumpido.");
            }
        }
    }


    // ---------------------------------------------------------
    // HILO 2: RUNNABLE
    // ---------------------------------------------------------

    /**
     * Este hilo ejecuta un bucle mientras no reciba una
     * solicitud de interrupción.
     *
     * No realiza ninguna operación de entrada/salida ni espera
     * a otros hilos: simplemente permanece ejecutándose.
     */
    static class HiloCalculador implements Runnable {

        @Override
        public void run() {

            System.out.println("El calculador comienza a trabajar.");

            while (!Thread.currentThread().isInterrupted()) {
                // El hilo permanece ocupado realizando trabajo.
            }

            System.out.println("El calculador termina.");
        }
    }


    // ---------------------------------------------------------
    // HILO 3: BLOCKED
    // ---------------------------------------------------------

    /**
     * Intenta entrar en un bloque synchronized.
     *
     * Si otro hilo tiene previamente el monitor CERROJO,
     * tendrá que esperar para poder entrar.
     */
    static class HiloBloqueado implements Runnable {

        @Override
        public void run() {

            System.out.println("El bloqueado intenta conseguir el cerrojo.");

            synchronized (CERROJO) {

                System.out.println("El bloqueado ha conseguido el cerrojo.");
            }
        }
    }


    // ---------------------------------------------------------
    // HILO 4: WAITING
    // ---------------------------------------------------------

    /**
     * Entra en un bloque synchronized y llama a wait().
     *
     * Al hacerlo, libera el monitor y queda esperando hasta
     * que otro hilo lo despierte mediante notify() o notifyAll().
     */
    static class HiloPaciente implements Runnable {

        @Override
        public void run() {

            synchronized (CERROJO) {

                try {
                    System.out.println("El paciente va a ejecutar wait().");

                    CERROJO.wait();

                    System.out.println("El paciente ha sido despertado.");

                } catch (InterruptedException e) {

                    System.out.println("El paciente ha sido interrumpido.");
                }
            }
        }
    }


    // ---------------------------------------------------------
    // MÉTODO PRINCIPAL
    // ---------------------------------------------------------

    public static void main(String[] args) throws InterruptedException {

        // =====================================================
        // 1. ESTADO NEW
        // =====================================================

        System.out.println("\n--- ESTADO NEW ---");

        Thread durmiente = new Thread(
                new HiloDurmiente(), "durmiente");

        // El hilo está creado, pero todavía no se ha iniciado.
        System.out.println(
                durmiente.getName() + ": " + durmiente.getState());


        // =====================================================
        // 2. ESTADOS TIMED_WAITING Y TERMINATED
        // =====================================================

        System.out.println("\n--- TIMED_WAITING Y TERMINATED ---");

        // start() inicia la ejecución del hilo.
        durmiente.start();

        // Esperamos hasta que entre en Thread.sleep().
        esperarEstado(durmiente, Thread.State.TIMED_WAITING);

        // join() espera a que el hilo termine.
        durmiente.join();

        // Una vez finalizado, su estado será TERMINATED.
        System.out.println(
                durmiente.getName() + ": " + durmiente.getState());


        // =====================================================
        // 3. ESTADO RUNNABLE
        // =====================================================

        System.out.println("\n--- ESTADO RUNNABLE ---");

        Thread calculador = new Thread(
                new HiloCalculador(), "calculador");

        calculador.start();

        // Esperamos hasta que el hilo esté en estado RUNNABLE.
        esperarEstado(calculador, Thread.State.RUNNABLE);

        // Solicitamos que termine mediante interrupt().
        calculador.interrupt();

        // Esperamos a que finalice realmente.
        calculador.join();


        // =====================================================
        // 4. ESTADOS WAITING Y BLOCKED
        // =====================================================

        System.out.println("\n--- WAITING Y BLOCKED ---");

        Thread paciente = new Thread(
                new HiloPaciente(), "paciente");

        Thread bloqueado = new Thread(
                new HiloBloqueado(), "bloqueado");


        // Iniciamos primero el hilo que ejecutará wait().
        paciente.start();

        // Esperamos a que alcance el estado WAITING.
        esperarEstado(paciente, Thread.State.WAITING);


        /*
         * El hilo principal entra en synchronized y adquiere
         * el monitor CERROJO.
         *
         * Mientras permanezca dentro de este bloque, ningún
         * otro hilo podrá adquirir ese mismo monitor.
         */
        synchronized (CERROJO) {

            // Iniciamos el segundo hilo.
            bloqueado.start();

            /*
             * El hilo bloqueado intenta entrar en synchronized,
             * pero el monitor pertenece al hilo principal.
             *
             * Por tanto, su estado será BLOCKED.
             */
            esperarEstado(bloqueado, Thread.State.BLOCKED);


            /*
             * Despertamos al hilo que estaba esperando en wait().
             *
             * notifyAll() no libera inmediatamente el monitor:
             * el hilo despertado tendrá que esperar a que el
             * hilo principal salga del bloque synchronized.
             */
            CERROJO.notifyAll();
        }


        // Esperamos a que ambos hilos terminen.
        bloqueado.join();
        paciente.join();


        System.out.println("\nTodos los hilos han terminado.");
    }
}
