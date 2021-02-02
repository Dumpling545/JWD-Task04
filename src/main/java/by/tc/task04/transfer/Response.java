package by.tc.task04.transfer;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = -99736532995418042L;
    private String content;

    public Response(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
