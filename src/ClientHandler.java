import java.io.*;
import java.net.*;
import java.util.List;

public class ClientHandler extends Thread {
    private Socket socket;
    private List<User> users;

    public ClientHandler(Socket socket, List<User> users) {
        this.socket = socket;
        this.users = users;
    }

    public void run() {
        try (InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             OutputStream output = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true)) {

            writer.println("Enter name:");
            String name = reader.readLine();

            writer.println("Enter password:");
            String password = reader.readLine();

            synchronized (users) {
                users.add(new User(name, password));
            }
            System.out.println("User " + name + " connected with password " + password);

            String text;
            writer.println("Enter your message (type 'exit' to quit):");
            writer.flush();
            while ((text = reader.readLine()) != null) {
                if (text.equals("exit")) {
                    break;
                }
                System.out.println("Received from " + name + " : " + text);
                writer.println("Echo: " + text);
                writer.flush();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
