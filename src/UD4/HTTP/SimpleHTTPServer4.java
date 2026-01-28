package UD4.HTTP;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

public class SimpleHTTPServer4 {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening for connection on port 8080 ....");

        while (true) {
            try (Socket socket = server.accept()) {

                // Ruta al archivo HTML
                Path filePath = Path.of("index.htm");

                if (Files.exists(filePath)) {

                    byte[] fileContent = Files.readAllBytes(filePath);

                    String header =
                            "HTTP/1.1 200 OK\r\n" +
                            "Content-Type: text/html; charset=UTF-8\r\n" +
                            "Content-Length: " + fileContent.length + "\r\n" +
                            "\r\n";

                    OutputStream out = socket.getOutputStream();
                    out.write(header.getBytes(StandardCharsets.UTF_8));
                    out.write(fileContent);
                    out.flush();

                } else {
                    // Respuesta 404 si no existe el archivo
                    String response =
                            "HTTP/1.1 404 Not Found\r\n\r\n" +
                            "Archivo no encontrado";

                    socket.getOutputStream()
                          .write(response.getBytes(StandardCharsets.UTF_8));
                }
            }
        }
    }
}
