package Networking;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {
    private  String message;
    List<Contact> contacts;
    Response(List<Contact> resp) {
        this.contacts = resp;
    }
    public Response(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }


}
