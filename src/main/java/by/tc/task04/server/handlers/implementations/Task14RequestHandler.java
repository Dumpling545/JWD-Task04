package by.tc.task04.server.handlers.implementations;

import by.tc.task04.entity.Text;
import by.tc.task04.transfer.RequestType;
import by.tc.task04.transfer.Response;

public class Task14RequestHandler extends AbstractTaskRequestHandler {

    @Override
    protected Response handle(String content, Text text) throws CloneNotSupportedException {
        //Parser<String, Text> parser = StringToTextParser.getInstance();
        return new Response(":(");
    }

    @Override
    protected RequestType getType() {
        return RequestType.TASK_14;
    }
    
}
