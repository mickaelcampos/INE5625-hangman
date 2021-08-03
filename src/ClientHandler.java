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

        Hangman hangman = new Hangman();
        boolean first = true;
        
        while (true) {
            try {
                input = new Scanner(clientSocket.getInputStream()); // canal para receber do cliente
                output =  new PrintWriter(clientSocket.getOutputStream(), true); // canal para enviar ao cliente
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            if (first) { // executa apenas na primeira vez
                output.println(hangman.getStream());
                first = false;
            }

            char[] receivedData = input.nextLine().toCharArray(); // dados que o cliente enviou

            // aqui deve tratar se letra esta na palavra
            hangman.setStream(receivedData);

            output.println(hangman.getStream());
        }
    }

}
