import java.io.*;
import java.net.*;

public class Alice {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(hostname, port)) {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            Thread serverListener = new Thread(new ServerListener(socket));
            serverListener.start();

            String userInput;
            while (true) {
                userInput = consoleReader.readLine();
                writer.println(userInput);
                if (userInput.equals("exit")) {
                    break;
                }
            }

        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
