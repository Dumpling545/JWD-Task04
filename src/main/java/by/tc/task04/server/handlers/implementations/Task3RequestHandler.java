package by.tc.task04.server.handlers.implementations;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import by.tc.task04.entity.Paragraph;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.TextToken;
import by.tc.task04.entity.Word;
import by.tc.task04.transfer.RequestType;
import by.tc.task04.transfer.Response;

public class Task3RequestHandler extends AbstractTaskRequestHandler {

    @Override
    protected Response handle(String content, Text text) throws CloneNotSupportedException {
        Set<Word> words = new TreeSet<>();
        List<TextToken> paragraphs = text.getTextTokens().stream()
                .filter(t -> t instanceof Paragraph).collect(Collectors.toList());
        words.addAll(paragraphs.get(0).getTextTokens().get(0).getTextTokens().stream()
                .filter(t -> t instanceof Word).map(t -> (Word) t).collect(Collectors.toSet()));
        List<TextToken> sentences;
        Set<Word> temp;
        for (int p = 0; p < paragraphs.size(); p++) {
            sentences = paragraphs.get(p).getTextTokens();
            for (int s = 0; s < sentences.size(); s++) {
                temp = sentences.get(s).getTextTokens().stream().filter(t -> t instanceof Word)
                        .map(t -> (Word) t).collect(Collectors.toSet());
                if (p != 0 || s != 0)
                    words.removeAll(temp);
            }
        }
        String responseContent;
        if (words.size() == 0) {
            responseContent = "No such words";
        } else {
            responseContent = words.iterator().next().getContent();
        }
        return new Response(responseContent);
    }

    @Override
    protected RequestType getType() {
        return RequestType.TASK_3;
    }

}
