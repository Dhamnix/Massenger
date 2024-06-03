import java.io.*;
import java.net.*;
import java.util.List;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws IOException {
        List<User> users = new ArrayList<>();
        ServerSocket serverSocket = new ServerSocket(12345);
        Socket aliceSocket = serverSocket.accept();
        Socket bobSocket = serverSocket.accept();

        ClientHandler aliceHandler = new ClientHandler(aliceSocket, users);
        ClientHandler bobHandler = new ClientHandler(bobSocket, users);

        aliceHandler.start();
        bobHandler.start();

        }
    }


