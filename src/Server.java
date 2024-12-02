import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.List;
import java.util.Scanner;
////////////////////////////////////////
public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ContactListManager manager = new ContactListManager();

        manager.addContact("Alice", "123-456-7890", "alice@example.com");
        manager.addContact("Bob", "234-567-8901", "bob@example.com");

        ServerSocket listener = new ServerSocket(8080);
        var should_exit = false;
        while (true) {
            var socket = listener.accept();
            System.out.println("New connection accepted");
            System.out.println(socket.toString());
            var requests = new ObjectInputStream(socket.getInputStream());
            var responses = new ObjectOutputStream(socket.getOutputStream());




            while (!should_exit) {
                Request r = (Request) requests.readObject();
                switch (r.type) {
                    case List: {
                        List<Contact> resp = manager.listContacts();
                        System.out.println(resp);
                        responses.writeObject(new Response(resp));
                        responses.flush();
                        break;
                    }

                    case Create: {
                        List<Contact> contacts = (List<Contact>) r.getData();
                        for (Contact contact : contacts) {
                            manager.addContact(contact.getName(), contact.getPhoneNumber(), contact.getEmail());
                        }
                        break;
                    }
                    case Delete: {
                        String toRemove = (String) r.getData();
                        boolean removed = manager.removeContact(toRemove);//clm return false
                        if (removed) {
                            responses.writeObject(new Response("not found"));
                        } else {
                            responses.writeObject(new Response("removed"));
                        }
                        break;
                    }
                    case Get: {
                        String toGet = (String) r.getData();
                        Contact foundContact = manager.getContact(toGet);
                        if (foundContact == null) {
                            responses.writeObject(new Response("not found"));
                        }
                        if (foundContact != null) {
                            responses.writeObject((new Response(foundContact.getName() + " " + foundContact.getPhoneNumber() + " " + foundContact.getEmail())));

                        }
                        break;
                    }

                    case Exit: {
                        should_exit = true;
                        responses.writeObject(new Response("goodbye!"));
                        break;
                    }
                    case Update: {
                        Contact contact = (Contact) r.getData();
                        manager.updateContact(contact.getName(), contact.getPhoneNumber(), contact.getEmail());
                        responses.writeObject(new Response("ok"));
                        break;
                    }
                }
            }
            socket.close();
        }
    }
}
