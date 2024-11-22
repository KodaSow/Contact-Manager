import java.io.Serializable;

public class Request implements Serializable {
    RequestType type;
    Request(RequestType type) {
        this.type = type;
    }
}

