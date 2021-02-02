package by.tc.task04.server.context;

import java.util.ArrayList;
import java.util.List;
import by.tc.task04.entity.Text;
import by.tc.task04.server.handlers.RequestHandler;
import by.tc.task04.server.handlers.implementations.*;

public class ClientContext {
    private Text text;
    private List<RequestHandler> requestHandlers;
    public ClientContext(){
        text = new Text();
        requestHandlers = new ArrayList<>();
        requestHandlers.add(new SetTextRequestHandler());
        requestHandlers.add(new Task1RequestHandler());
        requestHandlers.add(new Task2RequestHandler());
        requestHandlers.add(new Task3RequestHandler());
        requestHandlers.add(new Task4RequestHandler());
        requestHandlers.add(new Task5RequestHandler());
        requestHandlers.add(new Task6RequestHandler());
        requestHandlers.add(new Task7RequestHandler());
        requestHandlers.add(new Task8RequestHandler());
        requestHandlers.add(new Task9RequestHandler());
        requestHandlers.add(new Task10RequestHandler());
        requestHandlers.add(new Task11RequestHandler());
        requestHandlers.add(new Task12RequestHandler());
        requestHandlers.add(new Task13RequestHandler());
        requestHandlers.add(new Task14RequestHandler());
        requestHandlers.add(new Task15RequestHandler());
        requestHandlers.add(new Task16RequestHandler());
        //add any request handlers you want
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public List<RequestHandler> getRequestHandlers() {
        return requestHandlers;
    }

    public void setRequestHandlers(List<RequestHandler> requestHandlers) {
        this.requestHandlers = requestHandlers;
    }
}
