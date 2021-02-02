package by.tc.task04.transfer;

import java.io.Serializable;

public class Request implements Serializable{
    private static final long serialVersionUID = 670612773106760969L;
    private RequestType type;
    private String content;

    public Request(RequestType type) {
        this.type = type;
    }

    public Request(RequestType type, String content) {
        this(type);
        this.content = content;
    }

    public RequestType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
