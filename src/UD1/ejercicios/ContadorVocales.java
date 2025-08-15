package UD1.ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * La clase ContadorVocales cuenta cuántas veces aparece una letra
 * determinada en un fichero de texto, sin distinguir mayúsculas de minúsculas,
 * y guarda el resultado en un archivo de salida.
 * 
 * <p>El flujo del programa es:</p>
 * <ol>
 *   <li>Leer el archivo de entrada línea por línea.</li>
 *   <li>Contar todas las ocurrencias de la letra indicada.</li>
 *   <li>Mostrar el total por consola.</li>
 *   <li>Escribir el total en un archivo de resultado.</li>
 * </ol>
 * 
 * Ejemplo de ejecución:
 * <pre>
 *   java ContadorVocales entrada.txt a resultado.txt
 * </pre>
 * 
 * @author manu
 */
public class ContadorVocales {

    /**
     * Realiza el recuento de una letra en un fichero.
     *
     * @param nfe  Nombre del fichero de entrada (texto a analizar).
     * @param letra Letra que se quiere contar (se ignora mayúscula/minúscula).
     * @param nfr  Nombre del fichero donde se guardará el resultado.
     */
    public void hacerRecuento(String nfe, String letra, String nfr) {
        long result = 0;
        File f = new File(nfe);

        // Mostrar ruta absoluta del fichero para depuración
        System.out.println(f.getAbsolutePath());

        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNext()) {
                String l = sc.nextLine();
                for (int i = 0; i < l.length(); i++) {
                    if (letra.equalsIgnoreCase(Character.toString(l.charAt(i)))) {
                        result++;
                    }
                }
            }

            // Mostrar el resultado por consola
            System.out.println(result);

            // Guardar el resultado en el fichero de salida
            try (PrintWriter pw = new PrintWriter(new File(nfr))) {
                pw.print(result);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método principal. Recibe los parámetros por línea de comandos:
     * <ol>
     *   <li>Nombre del fichero de entrada</li>
     *   <li>Letra a buscar</li>
     *   <li>Nombre del fichero de salida</li>
     * </ol>
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        ContadorVocales c = new ContadorVocales();

        String nombreFicheroEntrada = args[0];
        String letra = args[1];
        String nombreFicheroResultado = args[2];

        System.out.println(letra);
        c.hacerRecuento(nombreFicheroEntrada, letra, nombreFicheroResultado);
    }
}
