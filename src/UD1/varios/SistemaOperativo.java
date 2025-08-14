package UD1.varios;
/**
 * En este ejemplo vemos cómo detectar el sistema operativo en el que se está
 * ejecutando este programa.
 *
 * @author diego
 */
public class SistemaOperativo {

    private static final String soNombre = System
            .getProperty("os.name")
            .toLowerCase();
    private static final String soVersion = System
            .getProperty("os.version")
            .toLowerCase();
    private static final String soArquitectura = System
            .getProperty("os.arch")
            .toLowerCase();

    public static void main(String args[]) {
        System.out.println("Sistema operativo: " + soNombre);
        System.out.println("Versión: " + soVersion);
        System.out.println("Arquitectura: " + soArquitectura);

        if (esWindows()) {
            System.out.println("Es Windows");
        } else if (esMac()) {
            System.out.println("Es Mac");
        } else if (esLinux()) {
            System.out.println("Es Linux");
        } else if (esUnix()) {
            System.out.println("Es Unix");
        } else if (esSolaris()) {
            System.out.println("Es Solaris");
        } else {
            System.out.println("Sistema Operativo no contemplado.");
        }
    }

    public static boolean esWindows() {
        return (soNombre.contains("win"));
    }

    public static boolean esMac() {
        return (soNombre.contains("mac"));
    }

    public static boolean esLinux() {
        return (soNombre.contains("nux"));
    }

    public static boolean esSolaris() {
        return (soNombre.contains("sunos"));
    }

    public static boolean esUnix() {
        return (soNombre.contains("nix")
                || soNombre.contains("aix"));
    }
}
