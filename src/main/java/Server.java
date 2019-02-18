import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {

        while (true) {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(3000);
            } catch (IOException e) {
                System.err.println("Could not listen on port: 4444.");
                System.exit(1);
            }

            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            int inputLine = 1, outputLine = 0;

            while ((inputLine != outputLine)) {
                System.out.println("Server succes");
                out.println("Hello you are connected, Please buy our awesome merch now!!!");
                outputLine++;
            }
            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
        }
    }
}