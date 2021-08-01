
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static final int LISTENING_PORT = 6000;
    public static void main(String[] args) throws UnknownHostException, IOException {

        String hostAddress = "127.0.0.1";

        Socket client = new Socket(hostAddress, LISTENING_PORT);
        System.out.println("O client: " + client + " se conectou ao servidor!");
        Scanner input = new Scanner(System.in);

        PrintStream out = new PrintStream(client.getOutputStream());

        while (input.hasNextLine()) {
            out.println(input.nextLine());
        }
        out.close();
        input.close();
    }

}
