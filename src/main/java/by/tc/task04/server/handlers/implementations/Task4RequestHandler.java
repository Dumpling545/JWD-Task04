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

public class Task4RequestHandler extends AbstractTaskRequestHandler {

    @Override
    protected Response handle(String content, Text text) throws CloneNotSupportedException {
        int wordLength = Integer.parseInt(content);
        Set<String> uniqueWords = new TreeSet<>();
        List<TextToken> paragraphs = text.getTextTokens();
        List<TextToken> sentences;
        List<TextToken> words;
        for (int p = 0; p < text.getTextTokens().size(); p++) {
            if (paragraphs.get(p) instanceof Paragraph) {
                sentences = paragraphs.get(p).getTextTokens();
                for (int s = 0; s < sentences.size(); s++) {
                    words = sentences.get(s).getTextTokens();
                    if (words.get(words.size() - 1).getContent().contains("?")) {
                        uniqueWords.addAll(words.stream().filter(
                                t -> (t instanceof Word && t.getContent().length() == wordLength))
                                .map(t -> t.getContent()).collect(Collectors.toSet()));
                    }
                }
            }
        }
        String responseContent = String.join(" ", uniqueWords);
        return new Response(responseContent);
    }

    @Override
    protected RequestType getType() {
        return RequestType.TASK_4;
    }

}
