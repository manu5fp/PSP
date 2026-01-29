package UD4.HTTP;
import java.net.ServerSocket;
import java.net.Socket;
public class SimpleHTTPServer {

  public static void main(String args[] ) throws Exception {
    final ServerSocket server = new ServerSocket(8080);
    System.out.println("HTTP Server Ver 1.0. Listening on port 8080...");
    while (true) {
      final Socket client = server.accept();
      // 1. Read HTTP request from the client socket
      // 2. Prepare an HTTP response
      // 3. Send HTTP response to the client
      // 4. Close the socket
    }
  }
}
