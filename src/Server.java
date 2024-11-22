import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

public class Server {
public static void main(String[] args) throws IOException, ClassNotFoundException {
    ContactListManager manager = new ContactListManager();
    manager.addContact("Alice", "123-456-7890", "alice@example.com");
    manager.addContact("Bob", "234-567-8901", "bob@example.com");

    ServerSocket listener = new ServerSocket(8080);

    var should_exit = false;
    while (!should_exit) {
        var socket = listener.accept();
        var requests = new ObjectInputStream(socket.getInputStream());
        var responses = new ObjectOutputStream(socket.getOutputStream());

        while(true) {
            Request r = (Request) requests.readObject();
            switch (r.type) {
                case List:
                    responses.writeObject(new Response(manager.listContacts()));
                    break;
            }
        }


    }


        manager.listContacts();

        // Updating a contact
        manager.updateContact("Alice", "111-222-3333", "alice_new@example.com");
        Contact contact = manager.getContact("Alice");
        if (contact != null) {
            System.out.println("Retrieved: " + contact);
        }
        manager.removeContact("Bob");
        manager.listContacts();
    }}
