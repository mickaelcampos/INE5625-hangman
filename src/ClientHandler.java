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
        System.out.println("run 1");

        Hangman hangman = new Hangman();

        while (true) {
            System.out.println("aqui 1");

            try {
                input = new Scanner(clientSocket.getInputStream()); // canal para receber do cliente
                output =  new PrintWriter(clientSocket.getOutputStream(), true); // canal para enviar ao cliente
                System.out.println("aqui 2");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("aqui 3");

            char[] receivedData = input.nextLine().toCharArray(); // dados que o cliente enviou

            // aqui deve tratar se letra esta na palavra
            System.out.println(receivedData);
            hangman.setStream(receivedData);

            // output.println(hangman.getStream());
        }
    }

}
