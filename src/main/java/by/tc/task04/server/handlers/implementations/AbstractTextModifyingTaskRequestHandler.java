package by.tc.task04.server.handlers.implementations;

import by.tc.task04.entity.Text;
import by.tc.task04.server.parsers.Parser;
import by.tc.task04.server.parsers.implementations.StringToTextParser;
import by.tc.task04.transfer.Response;

public abstract class AbstractTextModifyingTaskRequestHandler extends AbstractTaskRequestHandler {

    @Override
    protected final Response handle(String content, Text text) throws CloneNotSupportedException {
        Text cloned = (Text) text.clone();
        handleCloned(content, cloned);
        Parser<String, Text> parser = StringToTextParser.getInstance();
        return new Response(parser.restore(cloned));
    }

    protected abstract void handleCloned(String content, Text cloned);

}
