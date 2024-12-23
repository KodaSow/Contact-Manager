//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;
import java.util.List;

public class ContactListManager {
    private List<Contact> contacts;

    public ContactListManager() {
        contacts = new ArrayList<>();
    }

    public void addContact(String name, String phoneNumber, String email) {
        contacts.add(new Contact(name, phoneNumber, email));
        contacts = new ArrayList<Contact>(contacts);
        System.out.println("Contact added successfully.");
    }

    public boolean removeContact(String name) {
        contacts.removeIf(contact -> contact.getName().equalsIgnoreCase(name));
        contacts = new ArrayList<Contact>(contacts);
        // System.out.println("Contact removed successfully.");
        return false;//altered to false
    }

    public void updateContact(String name, String newPhoneNumber, String newEmail) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equalsIgnoreCase(name)) {
                contacts.set(i, new Contact(name, newPhoneNumber, newEmail));
                System.out.println("Contact updated successfully.");
                contacts = new ArrayList<Contact>(contacts);
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

    public List<Contact> listContacts() {
        return contacts;
    }

//    public void addContact(Object contact) {
//    }
}