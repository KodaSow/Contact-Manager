


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
        char answer = 'y';
       // var should_exit = false;
        while (true){
            var socket = listener.accept();
            var requests = new ObjectInputStream(socket.getInputStream());
            var responses = new ObjectOutputStream(socket.getOutputStream());

            Request r = (Request) requests.readObject();


         //   while(answer == 'y'){
            switch (r.type) {
                case List: {
                    responses.writeObject(new Response(manager.listContacts()));
                    break;
                }
                case Create: {
                    List<Contact> contacts = (List<Contact>) r.getData();
                    for (Contact contact : contacts) {
                        manager.addContact(contact.getName(), contact.getPhoneNumber(), contact.getEmail());
                    }
                    break;
                }
                case Delete:{
                    String toRemove = (String) r.getData();
                    boolean removed = manager.removeContact(toRemove);//clm return false
                    if(removed){
                        responses.writeObject(new Response("not found"));
                    }
                    else{
                        responses.writeObject(new Response("removed"));
                    }
                    break;
                }
                case Get:{
                    String toGet = (String) r.getData();
                    Contact foundContact = manager.getContact(toGet);
                    if(foundContact == null){
                        responses.writeObject(new Response("not found"));
                        }
                    if(foundContact != null) {
                        responses.writeObject((new Response(foundContact.getName() + " " + foundContact.getPhoneNumber() + " " + foundContact.getEmail())));

                    } 
              
                }
                break;
            }
        }

    }}
