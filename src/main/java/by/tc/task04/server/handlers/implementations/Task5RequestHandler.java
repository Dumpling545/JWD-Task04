package by.tc.task04.server.handlers.implementations;

import java.util.Collections;
import java.util.List;
import by.tc.task04.entity.Paragraph;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.TextToken;
import by.tc.task04.entity.Word;
import by.tc.task04.transfer.RequestType;

public class Task5RequestHandler extends AbstractTextModifyingTaskRequestHandler {

    @Override
    protected void handleCloned(String content, Text cloned) {
        List<TextToken> paragraphs = cloned.getTextTokens();
        List<TextToken> sentences;
        List<TextToken> words;
        int first = 0;
        int last = 0;
        for (int p = 0; p < paragraphs.size(); p++) {
            if (paragraphs.get(p) instanceof Paragraph) {
                sentences = paragraphs.get(p).getTextTokens();
                for (int s = 0; s < sentences.size(); s++) {
                    words = sentences.get(s).getTextTokens();
                    for (int w = 0; w < words.size(); w++) {
                        if (words.get(w) instanceof Word) {
                            first = w;
                            break;
                        }
                    }
                    for (int w = words.size() - 1; w >= 0; w--) {
                        if (words.get(w) instanceof Word) {
                            last = w;
                            break;
                        }
                    }
                    Collections.swap(words, first, last);
                }
            }
        }
    }

    @Override
    protected RequestType getType() {
        return RequestType.TASK_5;
    }
    
}
