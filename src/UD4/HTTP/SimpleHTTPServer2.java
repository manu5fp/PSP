package UD4.HTTP;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
public class SimpleHTTPServer2 {

  public static void main(String args[] ) throws Exception {
    final ServerSocket server = new ServerSocket(8080);
    System.out.println("HTTP Server Ver 2.0. Listening on port 8080...");
    while (true) {
      Socket clientSocket = server.accept();
      InputStreamReader isr =  new InputStreamReader(clientSocket.getInputStream());
      BufferedReader reader = new BufferedReader(isr);
      String line = reader.readLine();            
      while (!line.isEmpty()) {
          System.out.println(line);
          line = reader.readLine();
    }
  }
 }
}

