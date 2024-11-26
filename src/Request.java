package Networking;

import java.io.Serializable;

public class Request implements Serializable {
   public RequestType type;
   public Object data;
   public Request(RequestType type) {
        this.type = type;
        this.data = null;//
    }
    public Request(RequestType type, Object data){

        this.type = type;
        this.data = data;
    }
    public Object getData(){
        return data;
    }
}

