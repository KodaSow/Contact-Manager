//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;
import java.util.List;

class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
    }
}

class ContactListManager {
    private List<Contact> contacts;

    public ContactListManager() {
        contacts = new ArrayList<>();
    }

    public void addContact(String name, String phoneNumber, String email) {
        contacts.add(new Contact(name, phoneNumber, email));
        System.out.println("Contact added successfully.");
    }

    public void removeContact(String name) {
        contacts.removeIf(contact -> contact.getName().equalsIgnoreCase(name));
        System.out.println("Contact removed successfully.");
    }

    public void updateContact(String name, String newPhoneNumber, String newEmail) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                contact.setPhoneNumber(newPhoneNumber);
                contact.setEmail(newEmail);
                System.out.println("Contact updated successfully.");
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public Contact getContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        System.out.println("Contact not found.");
        return null;
    }

    public void listContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("Contact List:");
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ContactListManager manager = new ContactListManager();
        manager.addContact("Alice", "123-456-7890", "alice@example.com");
        manager.addContact("Bob", "234-567-8901", "bob@example.com");
        manager.listContacts();

        // Updating a contact
        manager.updateContact("Alice", "111-222-3333", "alice_new@example.com");
        Contact contact = manager.getContact("Alice");
        if (contact != null) {
            System.out.println("Retrieved: " + contact);
        }
        manager.removeContact("Bob");
        manager.listContacts();
    }
}