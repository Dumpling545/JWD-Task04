package by.tc.task04.server.handlers.implementations;

import java.util.List;
import by.tc.task04.entity.Paragraph;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.TextToken;
import by.tc.task04.entity.Word;
import by.tc.task04.transfer.RequestType;

public class Task15RequestHandler extends AbstractTextModifyingTaskRequestHandler {
    private int type;
    private static final int FIRST = 0;
    private static final int LAST = 1;

    private char charToRemove(Word word) {
        if (type == LAST) {
            return word.getContent().charAt(word.getContent().length() - 1);

        } else {
            return word.getContent().charAt(0);
        }
    }

    private String removeChars(Word word) {
        StringBuilder sb = new StringBuilder(word.getContent());
        char cTr = charToRemove(word);
        for(int i = word.getContent().length() - 1 - type; i >= 1 - type; i--){
            if(sb.charAt(i) == cTr){
                sb.deleteCharAt(i);
            }
        }
        return sb.toString();
    }
    @Override
    protected void handleCloned(String content, Text cloned) {
        type = content.equals("last") ? LAST : FIRST;
        List<TextToken> paragraphs = cloned.getTextTokens();
        List<TextToken> sentences;
        List<TextToken> words;
        Word word;
        for (int p = 0; p < cloned.getTextTokens().size(); p++) {
            if (paragraphs.get(p) instanceof Paragraph) {
                sentences = paragraphs.get(p).getTextTokens();
                for (int s = 0; s < sentences.size(); s++) {
                    words = sentences.get(s).getTextTokens();
                    for (int w = 0; w < words.size(); w++) {
                        if (words.get(w) instanceof Word) {
                            word = (Word) words.get(w);
                            word.setContent(removeChars(word));
                        }
                    }
                }
            }
        }
    }

    @Override
    protected RequestType getType() {
        return RequestType.TASK_15;
    }

}
