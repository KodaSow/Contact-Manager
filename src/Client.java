import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("127.0.0.1", 8080);
        var requests = new ObjectOutputStream(socket.getOutputStream());
        var responses = new ObjectInputStream(socket.getInputStream());

        Scanner stdin = new Scanner(System.in);
        String input = "";

        while (!input.startsWith("exit")) {
            System.out.println(">>> ");
            input = stdin.nextLine();
            if (input.startsWith("list")) {
                requests.writeObject(new Request(RequestType.List));
                Response r = (Response) responses.readObject();
                System.out.println(r.contacts);
            }
        }

    }

}
