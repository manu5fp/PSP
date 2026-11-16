package ud2.a5_synchronized.e01_contador_seguro;

/** Contador correcto: todos los accesos a cuenta usan el monitor de this. */
class ContadorSeguro {
    private int cuenta = 0;

    public synchronized void incrementar() {
        cuenta++; // Leer, sumar y escribir no se intercalan con otro hilo
    }

    public synchronized int getCuenta() {
        return cuenta;
    }
}
