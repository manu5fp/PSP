package UD4.HTTP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

public class SimpleHTTPServer5 {

	// Contador global del servidor
	private static int contadorVisitas = 0;

	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(8080);
		System.out.println("Listening on port 8080...");

		while (true) {
			try (Socket socket = server.accept()) {

				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				// Leer la primera línea de la petición
				String requestLine = in.readLine();
				System.out.println(requestLine);

				OutputStream out = socket.getOutputStream();

				// Ignorar favicon.ico
				if (requestLine != null && requestLine.startsWith("GET /favicon.ico")) {

					String response = "HTTP/1.1 204 No Content\r\n\r\n";
					out.write(response.getBytes(StandardCharsets.UTF_8));

				} else {
					contadorVisitas++;
					System.out.println(contadorVisitas);
				}

				// Leer y procesar HTML
				Path filePath = Path.of("index.html");
				String html = Files.readString(filePath, StandardCharsets.UTF_8);

				html = html.replace("{{FECHA}}", new Date().toString());
				html = html.replace("{{VISITAS}}", String.valueOf(contadorVisitas));

				byte[] body = html.getBytes(StandardCharsets.UTF_8);

				String header = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html; charset=UTF-8\r\n"
						+ "Content-Length: " + body.length + "\r\n" + "\r\n";

				out.write(header.getBytes(StandardCharsets.UTF_8));
				out.write(body);
				out.flush();

			}
		}
	}
}