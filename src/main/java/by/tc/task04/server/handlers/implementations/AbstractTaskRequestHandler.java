package by.tc.task04.server.handlers.implementations;

import by.tc.task04.entity.Text;
import by.tc.task04.server.context.ClientContext;
import by.tc.task04.server.handlers.RequestHandler;
import by.tc.task04.transfer.Request;
import by.tc.task04.transfer.RequestType;
import by.tc.task04.transfer.Response;

public abstract class AbstractTaskRequestHandler implements RequestHandler {

    @Override
    public final Response handle(Request request, ClientContext context)
            throws CloneNotSupportedException {
        if (getType() == request.getType()) {
            return handle(request.getContent(), context.getText());
        } else {
            return null;
        }
    }

    protected abstract Response handle(String content, Text text) throws CloneNotSupportedException;

    protected abstract RequestType getType();
}
