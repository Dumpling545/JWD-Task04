package by.tc.task04.server.handlers.implementations;

import by.tc.task04.entity.Text;
import by.tc.task04.server.context.ClientContext;
import by.tc.task04.server.exceptions.ParseException;
import by.tc.task04.server.handlers.RequestHandler;
import by.tc.task04.server.parsers.Parser;
import by.tc.task04.server.parsers.implementations.StringToTextParser;
import by.tc.task04.transfer.Request;
import by.tc.task04.transfer.RequestType;
import by.tc.task04.transfer.Response;

public class SetTextRequestHandler implements RequestHandler {

    @Override
    public Response handle(Request request, ClientContext context) throws ParseException{
        if(request.getType() == RequestType.SET_TEXT){
            Parser<String, Text> parser = StringToTextParser.getInstance();
            Text text = parser.parse(request.getContent());
            context.setText(text);
            return new Response("OK");
        } else {
            return null;
        }
    }
    
}
