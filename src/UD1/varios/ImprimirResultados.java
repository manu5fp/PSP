package UD1.varios;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Para este ejemplo vamos a diferenciar dos salidas de mensajes:
 * - Una por consola.
 * - Otra que escriba en un fichero.
 * 
 * En estos ejemplos usaremos el try-with-resources:
 * https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
 * @author diego
 */
public class ImprimirResultados {

    static PrintStream salidaConsola;

    private static final String FICHERO
            = ImprimirResultados.class.getName()
            + ".txt";

    public static void main(String args[]) {

        salidaConsola = new PrintStream(System.out);

        ImprimirResultados imprimir = new ImprimirResultados();

        imprimir.imprimirEnAmbosCanales("Esto se está escribiendo por consola y en fichero estándar.");
        imprimir.imprimirEnAmbosCanales("Esto se está escribiendo por consola y en fichero estándar.");
        imprimir.imprimirEnAmbosCanales("Esto se está escribiendo por consola y en otro fichero.", "otroFichero.txt");
        imprimir.imprimirEnAmbosCanales("Esto se está escribiendo por consola y en otro fichero.", "otroFichero.txt");
        imprimir.imprimirEnFichero("Esto se escribe únicamente en el fichero estándar.");
        imprimir.imprimirEnFichero("Esto se escribe únicamente en el OTRO fichero.",
                "otroFichero.txt");
    }

    /**
     * Este método usaría el FileOutputStream mediante un try-with-resources
     * De esta forma, no tenemos que preocuparnos por liberar el fichero, ya
     * que la máquina virtual se encargará de hacerlo en cuanto acabe el bloque
     * de código.
     *
     * @param textoSalida Texto a incluir en el fichero.
     * @param enFichero Fichero en el que se escribirá el texto.
     */
    public void imprimirEnFichero(String textoSalida, String enFichero) {

        try (PrintStream salidaFichero = new PrintStream(
                new FileOutputStream(enFichero, true))) {
            /**
             * Para imprimir en un fichero, AÑADIENDO el texto indicado al que
             * ya tiene el propio fichero, hay que hacerlo a través de un
             * FileOutputStream, indicando "true" en el parámetro "append"
             * (añadir) del constructor. De otro modo, cada vez que se intentase
             * escribir en el fichero, se macharía su contenido y sólo se
             * guardaría el último texto.
             */
            salidaFichero.append(textoSalida + "\n");
        } catch (FileNotFoundException ex) {
            salidaConsola.println("ERROR en " + ImprimirResultados.class.getName());
            salidaConsola.println("El fichero " + FICHERO + " no existe");
        }
    }

    /**
     * Imprime en el fichero por defecto: FICHERO.
     *
     * @param textoSalida Texto a incluir en el fichero.
     */
    public void imprimirEnFichero(String textoSalida) {

        this.imprimirEnFichero(textoSalida, FICHERO);

    }

    /**
     * Imprime el texto tanto por la salida estándar como en el fichero.
     *
     * @param textoSalida Texto a incluir en el fichero.
     * @param enFichero Fichero en el que se escribirá el texto.
     */
    public void imprimirEnAmbosCanales(String textoSalida, String enFichero) {
        // Imprimimos por consola...
        salidaConsola.println(textoSalida);

        // ...y por fichero
        this.imprimirEnFichero(textoSalida, enFichero);
    }

    /**
     * Imprime el texto tanto por la salida estándar como en el fichero por
     * defecto: FICHERO.
     *
     * @param textoSalida Texto a incluir en el fichero.
     */
    public void imprimirEnAmbosCanales(String textoSalida) {
        
        this.imprimirEnAmbosCanales(textoSalida, FICHERO);
        
    }


    /**
     * Este método usaría el FileOutputStream de forma normal. Es decir, usaría
     * el método .close() para liberar el fichero. Esto se puede evitar con el
     * try-catch with resources que se usa en el método imprimirEnFichero.
     *
     * @param textoSalida Texto a incluir en el fichero.
     * @param enFichero Fichero en el que se escribirá el texto.
     */
    public void imprimirEnFichero2(String textoSalida, String enFichero) {

        PrintStream salidaFichero = null;
        try {
            salidaFichero = new PrintStream(
                    new FileOutputStream(enFichero, true));
            /**
             * Para imprimir en un fichero, AÑADIENDO el texto indicado al que
             * ya tiene el propio fichero, hay que hacerlo a través de un
             * FileOutputStream, indicando "true" en el parámetro "append"
             * (añadir) del constructor. De otro modo, cada vez que se intentase
             * escribir en el fichero, se macharía su contenido y sólo se
             * guardaría el último texto.
             */
            salidaFichero.append(textoSalida + "\n");
            salidaFichero.close();
        } catch (FileNotFoundException ex) {
            salidaConsola.println("ERROR en " + ImprimirResultados.class.getName());
            salidaConsola.println("El fichero " + FICHERO + " no existe");
        } finally {
            if (salidaFichero != null) {
                salidaFichero.close();
            }
        }
    }
}
