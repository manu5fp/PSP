package UD4.HTTP;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteHTTP {

	private String host;
	private int port;

	public ClienteHTTP(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void get(String path) throws Exception {

		try (Socket socket = new Socket(host, port)) {

			// Enviar petición HTTP
			PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

			out.println("GET " + path + " HTTP/1.1");
			out.println("Host: " + host);
			out.println("User-Agent: TextWebClient");
			out.println("Connection: close");
			out.println(); // línea en blanco OBLIGATORIA

			// Leer respuesta
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String line;
			
			boolean body = false;
			boolean insideBody = false;
			System.out.println("\n\n Contenido de la página");
			System.out.println(" ----------------------\n\n");
			while ((line = in.readLine()) != null) {

			    if (line.isEmpty()) {
			        body = true;
			        continue;
			    }

			    if (!body) {
			        continue; // ignorar cabeceras
			    }

			    // Detectar inicio y fin del body
			    if (line.toLowerCase().contains("<body")) {
			        insideBody = true;
			        continue;
			    }

			    if (line.toLowerCase().contains("</body")) {
			        insideBody = false;
			        continue;
			    }

			    if (insideBody) {
			        String texto = cleanHtml(line);
			        if (!texto.isBlank()) {
			            System.out.println(texto);
			        }
			    }
			}
		}
	}

	// Limpieza MUY básica de HTML
	private String cleanHtml(String line) {
		return line.replaceAll("<[^>]*>", "").trim();
	}

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);

		System.out.print("Servidor: ");
		String host = sc.nextLine();

		System.out.print("Puerto: ");
		int port = sc.nextInt();
		sc.nextLine();

		ClienteHTTP client = new ClienteHTTP(host, port);
		client.get("/");

		sc.close();
	}
}
