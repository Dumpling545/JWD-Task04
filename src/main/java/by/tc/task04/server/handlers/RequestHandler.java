package by.tc.task04.server.handlers;

import by.tc.task04.server.context.ClientContext;
import by.tc.task04.server.exceptions.ParseException;
import by.tc.task04.transfer.Request;
import by.tc.task04.transfer.Response;

public interface RequestHandler {
    public Response handle(Request request, ClientContext context)
            throws CloneNotSupportedException, ParseException;
}
