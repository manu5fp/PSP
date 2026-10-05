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
    public void ejecutar(int pid, String[] command) {
        ProcessBuilder pb;
        Process p;

        try {
            // Se construye un ProcessBuilder con el programa y el archivo a abrir.
            pb = new ProcessBuilder(command);

            // Se inicia el proceso externo
            p = pb.start();
            System.out.println("pid (" + pid + ") está activo:" + p.isAlive());
            esperar(p, pid);
            
        } catch (Exception e) {
            // En caso de error (programa no encontrado, permiso denegado, etc.)
            e.printStackTrace();
        }
    }
    private void esperar(Process p, int pid) {
        	try {
				p.waitFor();
	            System.out.println("pid (" + pid + ") está activo:" + p.isAlive());
	            System.out.println("pid:" + pid + " valor de salida:" + p.exitValue());
        	} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
        
    	int pid = 0;
        LanzadorProcesos lp = new LanzadorProcesos();
        String[] procesos = new String[] {"xed fichero.txt", "gnome-calculator"}; 
        for (String p : procesos) {
        	lp.ejecutar(pid, p.split(" ")); // Lanza el proceso
        	System.out.println("Proceso " + pid++ + " lanzado. Proceso principal avanzando ...");
        }
        
        System.out.println("Proceso principal terminado.");
    }
}