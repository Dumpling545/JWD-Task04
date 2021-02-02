package by.tc.task04.server.handlers.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import by.tc.task04.entity.Paragraph;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.TextToken;
import by.tc.task04.entity.Word;
import by.tc.task04.transfer.Response;

public abstract class AbstractWordsExtractingTaskRequestHandler extends AbstractTaskRequestHandler {

    @Override
    protected final Response handle(String content, Text text) throws CloneNotSupportedException {
        Set<String> uniqueWords = new TreeSet<>();
        List<TextToken> paragraphs = text.getTextTokens();
        List<TextToken> sentences;
        List<TextToken> words;
        Word word;
        for (int p = 0; p < text.getTextTokens().size(); p++) {
            if (paragraphs.get(p) instanceof Paragraph) {
                sentences = paragraphs.get(p).getTextTokens();
                for (int s = 0; s < sentences.size(); s++) {
                    words = sentences.get(s).getTextTokens();
                    for (int w = 0; w < words.size(); w++) {
                        if (words.get(w) instanceof Word) {
                            word = (Word) words.get(w);
                            uniqueWords.add(word.getContent());
                        }
                    }
                }
            }
        }
        List<String> uniqueWordsList = new ArrayList<>(uniqueWords);
        return handleWords(content, uniqueWordsList);
    }
    protected abstract Response handleWords(String content, List<String> uniqueWordsList);
}
