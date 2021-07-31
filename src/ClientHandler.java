import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Socket clientSocket = null;
    private Scanner input = null;
    private PrintWriter output = null;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                input = new Scanner(clientSocket.getInputStream()); // canal para receber do cliente
                output =  new PrintWriter(clientSocket.getOutputStream(), true); // caml para enviar ao cliente
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            String receivedData = input.nextLine(); // dados que o cliente enviou

            // tratar se letra esta na palavra
            System.out.println(receivedData);
            if (receivedData.equals("A")) {
                output.println("acertou!");
            } else {
                output.println("errrrrrrrrou!");
            }
        }
    }

}
