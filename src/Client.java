

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
        int lives = 6;
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
                String receivedData = inputFromSocket.nextLine(); // dados que o servidor  enviou
                // deve pritnar a forca no estado inicial
                System.out.println(receivedData);
                String aux = receivedData;
                while (input.hasNextLine()) {
                    String dataToSend = input.nextLine();

                    output.println(dataToSend); // envia o texto escrito no terminal do Client
                    receivedData = inputFromSocket.nextLine(); // dados que o servidor  enviou
                    if (receivedData.equals(aux)) {
                        // nao acertou
                        System.out.println("errrou");
                        lives--;
                    }
                    System.out.println(lives);
                    drawHangman(lives);
                    System.out.println(receivedData);

                    aux = receivedData;
                    // servidor retorna as posicoes ou um false/ -1
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

    public static void drawHangman(int l) {
		if (l == 6) {
			System.out.println("|----------");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
		} else if (l == 5) {
			System.out.println("|----------");
			System.out.println("|    O");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
		} else if (l == 4) {
			System.out.println("|----------");
			System.out.println("|    O");
			System.out.println("|    |");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
		} else if (l == 3) {
			System.out.println("|----------");
			System.out.println("|    O");
			System.out.println("|   -|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
		} else if (l == 2) {
			System.out.println("|----------");
			System.out.println("|    O");
			System.out.println("|   -|-");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
		} else if (l == 1) {
			System.out.println("|----------");
			System.out.println("|    O");
			System.out.println("|   -|-");
			System.out.println("|   /");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
		} else {
			System.out.println("|----------");
			System.out.println("|    O");
			System.out.println("|   -|-");
			System.out.println("|   /|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
		}
	}
}
