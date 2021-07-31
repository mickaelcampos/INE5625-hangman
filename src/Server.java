import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static final int LISTENING_PORT = 5000;
    public static void main(String[] args) throws IOException {

        ServerSocket server = null;
        Scanner input = null;
        try {
            server = new ServerSocket(LISTENING_PORT);

            String hostAddress = InetAddress.getLocalHost().getHostAddress();
    
            System.out.printf("Servidor inicializado em %s:%d.\n", hostAddress, LISTENING_PORT);

            Socket client = server.accept(); // bloqueia execução

            System.out.println("Nova conexão com o cliente " + client.getInetAddress()
                .getHostAddress());

            input = new Scanner(client.getInputStream());

            while (input.hasNextLine()) {
                System.out.println(input.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao criar o servidor!");
            System.exit(1);
        } finally {
            server.close();
            if (input != null) {
                input.close();
            }
        }

    }
}
