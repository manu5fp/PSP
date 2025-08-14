package UD1.ejemplos;

/**
 * La clase LanzadorProcesos permite ejecutar programas externos
 * en el sistema operativo desde una aplicación Java.
 * 
 * <p>Utiliza {@link ProcessBuilder} para crear y lanzar un proceso,
 * pasando como argumentos el nombre del programa y un fichero de texto.</p>
 * 
 * Ejemplo de uso:
 * <pre>
 *     LanzadorProcesos lp = new LanzadorProcesos();
 *     lp.ejecutar("xed"); // Abre el editor de texto Xed con "fichero.txt"
 * </pre>
 * 
 * Nota: La ruta del programa debe estar en el PATH o indicarse completa.
 * 
 * @author manu
 */
public class LanzadorProcesos {

    /**
     * Ejecuta un programa externo especificado por su ruta o nombre.
     * 
     * @param ruta Ruta o nombre del programa a ejecutar.
     *             Por ejemplo: "xed", "notepad", "/usr/bin/gedit".
     *             El programa se ejecutará con el argumento "fichero.txt".
     */
    public void ejecutar(String ruta) {
        ProcessBuilder pb;
        Process p;

        try {
            // Se construye un ProcessBuilder con el programa y el archivo a abrir.
            pb = new ProcessBuilder(ruta, "fichero.txt");

            // Se inicia el proceso externo
            p = pb.start();

        } catch (Exception e) {
            // En caso de error (programa no encontrado, permiso denegado, etc.)
            e.printStackTrace();
        }
    }

    /**
     * Método principal que lanza un editor de texto (Xed) y luego
     * imprime "Finalizado" en consola.
     * 
     * @param args Argumentos de línea de comandos (no se usan).
     */
    public static void main(String[] args) {
        String ruta = "xed"; // Nombre del programa (editor de texto en Linux Mint)
        
        LanzadorProcesos lp = new LanzadorProcesos();
        lp.ejecutar(ruta); // Lanza el editor con "fichero.txt"
        
        System.out.println("Finalizado");
    }
}