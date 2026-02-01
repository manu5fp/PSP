package UD4.FTP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class ClienteFTP {

    private String server;
    private int port;
    private String user;
    private String password;
    private FTPClient ftp;

    private Scanner sc;

    public ClienteFTP() {
        ftp = new FTPClient();
        sc = new Scanner(System.in);
    }

    // Conectar al servidor FTP
    public void conectar() {
        try {
            System.out.print("Servidor FTP: ");
            server = sc.nextLine();
            System.out.print("Puerto (21 por defecto): ");
            String portStr = sc.nextLine();
            port = portStr.isEmpty() ? 21 : Integer.parseInt(portStr);
            System.out.print("Usuario: ");
            user = sc.nextLine();
            System.out.print("Contraseña: ");
            password = sc.nextLine();

            ftp.connect(server, port);
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                System.out.println("No se pudo conectar al servidor FTP");
                return;
            }

            if (!ftp.login(user, password)) {
                ftp.disconnect();
                System.out.println("Login fallido. Credenciales incorrectas");
                return;
            }

            System.out.println("Conectado correctamente al FTP.");
        } catch (IOException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }

    // Mostrar contenido remoto
    public void mostrarContenidoRemoto() {
        if (!ftp.isConnected()) {
            System.out.println("No estás conectado al FTP.");
            return;
        }

        try {
            FTPFile[] files = ftp.listFiles("/");
            System.out.println("Contenido remoto en /:");
            for (FTPFile file : files) {
                String type = file.isDirectory() ? "<DIR>" : "<FILE>";
                System.out.printf("%-5s %s%n", type, file.getName());
            }
        } catch (IOException e) {
            System.out.println("Error al listar archivos remotos: " + e.getMessage());
        }
    }

    // Mostrar contenido local
    public void mostrarContenidoLocal() {
        System.out.print("Ruta local (vacío = actual): ");
        String path = sc.nextLine();
        File dir = path.isEmpty() ? new File(".") : new File(path);

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Ruta no válida");
            return;
        }

        System.out.println("Contenido local de " + dir.getAbsolutePath() + ":");
        for (File f : dir.listFiles()) {
            String type = f.isDirectory() ? "<DIR>" : "<FILE>";
            System.out.printf("%-5s %s%n", type, f.getName());
        }
    }

    // Subir fichero
    public void subirFichero() {
        if (!ftp.isConnected()) {
            System.out.println("No estás conectado al FTP.");
            return;
        }

        try {
            System.out.print("Nombre del fichero local a subir: ");
            String localFile = sc.nextLine();
            File file = new File(localFile);
            if (!file.exists() || !file.isFile()) {
                System.out.println("Archivo no encontrado.");
                return;
            }

            FileInputStream fis = new FileInputStream(file);
            boolean success = ftp.storeFile(file.getName(), fis);
            fis.close();

            if (success) {
                System.out.println("Fichero subido correctamente.");
            } else {
                System.out.println("Error al subir el fichero.");
            }
        } catch (IOException e) {
            System.out.println("Error al subir fichero: " + e.getMessage());
        }
    }

    // Descargar fichero
    public void descargarFichero() {
        if (!ftp.isConnected()) {
            System.out.println("No estás conectado al FTP.");
            return;
        }

        try {
            System.out.print("Nombre del fichero remoto a descargar: ");
            String remoteFile = sc.nextLine();
            System.out.print("Ruta local donde guardar (vacío = actual): ");
            String localPath = sc.nextLine();
            if (localPath.isEmpty()) localPath = ".";

            FileOutputStream fos = new FileOutputStream(localPath + File.separator + remoteFile);
            boolean success = ftp.retrieveFile(remoteFile, fos);
            fos.close();

            if (success) {
                System.out.println("Fichero descargado correctamente.");
            } else {
                System.out.println("Error al descargar el fichero.");
            }
        } catch (IOException e) {
            System.out.println("Error al descargar fichero: " + e.getMessage());
        }
    }

    // Cerrar conexión
    public void cerrar() {
        if (ftp != null && ftp.isConnected()) {
            try {
                ftp.logout();
            } catch (IOException ex) {
                System.out.println("Error al hacer logout: " + ex.getMessage());
            }
            try {
                ftp.disconnect();
            } catch (IOException ex) {
                System.out.println("Error al desconectar: " + ex.getMessage());
            }
        }
    }

    // Menú principal
    public void menu() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ FTP ===");
            System.out.println("1.- Conectar con servidor FTP");
            System.out.println("2.- Mostrar contenido remoto");
            System.out.println("3.- Mostrar contenido local");
            System.out.println("4.- Subir fichero");
            System.out.println("5.- Descargar fichero remoto");
            System.out.println("6.- Salir");
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    conectar();
                    break;
                case 2:
                    mostrarContenidoRemoto();
                    break;
                case 3:
                    mostrarContenidoLocal();
                    break;
                case 4:
                    subirFichero();
                    break;
                case 5:
                    descargarFichero();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    cerrar();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }

    public static void main(String[] args) {
        ClienteFTP client = new ClienteFTP();
        client.menu();
    }
}

