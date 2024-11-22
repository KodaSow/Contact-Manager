import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {
    List<Contact> contacts;
    Response(List<Contact> resp) {
        this.contacts = resp;
    }
}
