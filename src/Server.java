import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int LISTENING_PORT = 6000;
    public static void main(String[] args) {

        Server server = new Server();

        try {
            server.initServer();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void initServer() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(LISTENING_PORT);
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            System.out.printf("Servidor inicializado em %s:%d.\n", hostAddress, LISTENING_PORT);
        
            while (true) {
                Socket client = serverSocket.accept(); // bloqueia execução
                startClientConnection(client);
                System.out.println("Novo cliente conectado!");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao criar o servidor!"); // porta em uso
            System.exit(1);
        } finally {
            serverSocket.close();
        }
    }
    
    private void startClientConnection(Socket client) {
        ClientHandler handler = new ClientHandler(client);
        Thread threadHandler = new Thread(handler);
        threadHandler.start();
    }
}
