import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Socket clientSocket = null;
    private Scanner input = null;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                input = new Scanner(clientSocket.getInputStream());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            while (input.hasNextLine()) {
                System.out.println(input.nextLine());
            }
        }
    }

}
