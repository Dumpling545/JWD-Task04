package by.tc.task04.server.handlers.implementations;

import java.util.ArrayList;
import java.util.List;
import by.tc.task04.entity.Paragraph;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.TextToken;
import by.tc.task04.entity.Word;
import by.tc.task04.server.parsers.Parser;
import by.tc.task04.server.parsers.implementations.StringToParagraphParser;
import by.tc.task04.transfer.RequestType;
import by.tc.task04.transfer.Response;

public class Task2RequestHandler extends AbstractTaskRequestHandler {

    @Override
    protected Response handle(String content, Text text) throws CloneNotSupportedException {
        List<TextToken> sentences = new ArrayList<>();
        text.getTextTokens().stream().filter(t -> t instanceof Paragraph)
                .forEach(t -> sentences.addAll(t.getTextTokens()));
        sentences.sort((t1,
                t2) -> (int) (t1.getTextTokens().stream().filter(t -> t instanceof Word).count()
                        - t2.getTextTokens().stream().filter(t -> t instanceof Word).count()));
        Paragraph paragraph = new Paragraph();
        paragraph.getTextTokens().addAll(sentences);
        Parser<String, Paragraph> parser = StringToParagraphParser.getInstance();
        return new Response(parser.restore(paragraph));
    }

    @Override
    protected RequestType getType() {
        return RequestType.TASK_2;
    }



}
