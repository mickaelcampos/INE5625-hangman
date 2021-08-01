
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static final int LISTENING_PORT = 6000;
    public static void main(String[] args) throws UnknownHostException, IOException {

        String hostAddress = "127.0.0.1";
        Socket client = null;
        Scanner input = null;
        Scanner inputFromSocket = null;
        PrintStream output = null;

        try { 
            client = new Socket(hostAddress, LISTENING_PORT);
            System.out.println("O client: " + client + " se conectou ao servidor!");
    
            while (true) {
                try {
                    input = new Scanner(System.in); // dados inseridos no meu terminal
                    output = new PrintStream(client.getOutputStream()); // canal para enviar ao server
                    inputFromSocket = new Scanner(client.getInputStream()); // canal para receber do servidor
                } catch (IOException e) {
                    e.printStackTrace();
                }

                while (input.hasNextLine()) {
                    output.println(input.nextLine()); // envia o texto escrito no terminal do Client
                    String receivedData = inputFromSocket.nextLine(); // dados que o servidor  enviou
                    System.out.println(receivedData);
                }
            }
        } catch (IOException ioe){
            System.out.println("Erro o se conectar com o servidor!"); // possivelmente servidor off
        } finally {
            
            if (client != null) {
                client.close();
            }
            if (output != null) {
                output.close();
            }
            if (input != null) {
                input.close();
            }
            if (inputFromSocket != null) {
                inputFromSocket.close();
            }

        }
        
    }
}
