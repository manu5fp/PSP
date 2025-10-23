package UD2._03_Contador;

/**
 * La clase {@code ContadorSeguro} implementa un contador simple que puede
 * incrementarse y consultarse.
 * <p>
 * A pesar de su nombre, esta implementación <b>no es segura en entornos
 * concurrentes</b>, ya que los métodos {@link #incrementar()} y
 * {@link #getCuenta()} no están sincronizados. Si múltiples hebras
 * utilizan este contador al mismo tiempo, pueden producirse
 * condiciones de carrera y resultados inesperados.
 * </p>
 *
 * <h3>Uso típico:</h3>
 * <pre>{@code
 * ContadorSeguro contador = new ContadorSeguro();
 * contador.incrementar();
 * System.out.println("Cuenta = " + contador.getCuenta());
 * }</pre>
 *
 * <h3>Nota para estudiantes:</h3>
 * <p>
 * Este ejemplo suele usarse con fines didácticos para ilustrar:
 * </p>
 * <ul>
 *   <li>Cómo varias hebras pueden acceder simultáneamente a un mismo
 *       recurso (la variable {@code cuenta}).</li>
 *   <li>La necesidad de mecanismos de sincronización como
 *       {@code synchronized}, {@link java.util.concurrent.locks.Lock} o
 *       clases atómicas como {@link java.util.concurrent.atomic.AtomicInteger}.</li>
 * </ul>
 *
 * @author manu
 * @version 1.0
 */
class ContadorSeguro {

    /** Variable interna que almacena el valor del contador. */
    private int cuenta = 0;

    /**
     * Incrementa en una unidad el valor del contador.
     * <p>
     * Este método no está sincronizado. En entornos con múltiples
     * hilos concurrentes, su ejecución puede producir resultados
     * no deterministas.
     * </p>
     */
    public void incrementar() {
        cuenta++;
    }

    /**
     * Devuelve el valor actual del contador.
     *
     * @return el valor de la variable {@code cuenta}.
     */
    public int getCuenta() {
        return cuenta;
    }
}
