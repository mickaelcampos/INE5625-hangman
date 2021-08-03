

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
            System.out.println("\n \n \n----------------------------------------------\n \n \n");
    
            while (true) {
                try {
                    input = new Scanner(System.in); // dados inseridos no meu terminal
                    output = new PrintStream(client.getOutputStream()); // canal para enviar ao server
                    inputFromSocket = new Scanner(client.getInputStream()); // canal para receber do servidor
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.print("Digite aqui a letra: ");
                String receivedData = inputFromSocket.nextLine(); // dados que o servidor  enviou
                drawHangman(lives);
                System.out.println("A palavra é: " + receivedData);
                System.out.print("Insira a próxima letra: ");

                String aux = receivedData;

                while (input.hasNextLine()) {
                    String dataToSend = input.nextLine();

                    output.println(dataToSend); // envia o texto escrito no terminal do Client
                    receivedData = inputFromSocket.nextLine(); // dados que o servidor  enviou

                    System.out.print("\033[H\033[2J");  
                    System.out.flush();  

                    if (receivedData.equals(aux)) {
                        // nao acertou
                        System.out.println("Você errou!");
                        lives--;
                        if(lives == 0) {
                            System.out.println("O jogo acabou!");
                            client.close();
                            output.close();
                            input.close();
                            inputFromSocket.close();
                        }
                    }
                    drawHangman(lives);
                    System.out.println("A palavra é: " + receivedData);
                    System.out.print("Insira a próxima letra: ");
    
                    aux = receivedData;
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
