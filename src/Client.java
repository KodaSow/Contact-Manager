

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class Client implements Serializable {
///////////////////////////////////////
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Socket socket = new Socket("127.0.0.1", 8080);
//        var requests = new ObjectOutputStream(socket.getOutputStream());
//        var responses = new ObjectInputStream(socket.getInputStream());

        Scanner stdin = new Scanner(System.in);
        String input = "";


            while (!input.startsWith("exit")) {
                Socket socket = new Socket("127.0.0.1", 8080);
                var requests = new ObjectOutputStream(socket.getOutputStream());
                var responses = new ObjectInputStream(socket.getInputStream());

                System.out.println("add, list, remove or exit? >>> ");
                input = stdin.nextLine();
                if (input.startsWith("list")) {
                    requests.writeObject(new Request(RequestType.List));

                    Response r = (Response) responses.readObject();
                    System.out.println(r.contacts);
                }
                else if (input.startsWith("add")) {

                    Scanner userAdd = new Scanner(System.in);
                    System.out.println("Enter new contact's name");
                    String name = userAdd.nextLine();
                    name = name;
                    System.out.println("Enter new contact's phone number");
                    String phoneNumber = userAdd.nextLine();
                    System.out.println("Enter new contact's email address");
                    String email = userAdd.nextLine();
                    Contact newContact = new Contact(name,phoneNumber,email);
                    requests.writeObject(new Request(RequestType.Create, List.of(newContact)));

                 //   input = "";
                }
                else if (input.startsWith("remove")){
                    Scanner willRemove = new Scanner(System.in);
                    System.out.println("Enter name of contact to remove");
                    String nameRemove = willRemove.nextLine();
                    requests.writeObject(new Request(RequestType.Delete, nameRemove));
                    Response r = (Response) responses.readObject();
                    System.out.println(r.getMessage());
                }
                else if(input.startsWith("get")){
                    Scanner getName = new Scanner(System.in);
                    System.out.println("Enter name to find: ");
                    String findName = getName.nextLine();
                    requests.writeObject(new Request(RequestType.Get, findName));
                    Response r = (Response) responses.readObject();
                    System.out.print(r.getMessage() + " ");
                    System.out.println();
                    
                }
                socket.close();

            }

    }

}
