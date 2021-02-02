package by.tc.task04.server.handlers.implementations;

import java.util.List;
import by.tc.task04.entity.Paragraph;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.TextToken;
import by.tc.task04.entity.Word;
import by.tc.task04.transfer.RequestType;

public class Task12RequestHandler extends AbstractTextModifyingTaskRequestHandler {

    @Override
    protected void handleCloned(String content, Text cloned) {
        int wordLength = Integer.parseInt(content);
        List<TextToken> paragraphs = cloned.getTextTokens();
        List<TextToken> sentences;
        List<TextToken> words;
        for (int p = 0; p < cloned.getTextTokens().size(); p++) {
            if (paragraphs.get(p) instanceof Paragraph) {
                sentences = paragraphs.get(p).getTextTokens();
                for (int s = 0; s < sentences.size(); s++) {
                    words = sentences.get(s).getTextTokens();
                    words.removeIf(w -> {
                        int length = w.getContent().length();
                        char first = Character.toLowerCase(w.getContent().charAt(0));
                        return w instanceof Word && length == wordLength
                                && "qwrtpsdfghjklzxcvbnm".indexOf(first) >= 0;
                    });
                }
            }
        }
    }

    @Override
    protected RequestType getType() {
        return RequestType.TASK_12;
    }

}
