package by.tc.task04.server.handlers.implementations;

import java.util.List;
import by.tc.task04.entity.Paragraph;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.TextToken;
import by.tc.task04.entity.Word;
import by.tc.task04.transfer.RequestType;

public class Task11RequestHandler extends AbstractTextModifyingTaskRequestHandler {

    @Override
    protected void handleCloned(String content, Text cloned) {
        String[] params = content.split(" ");
        String start = params[0];
        String end = params[1];
        List<TextToken> paragraphs = cloned.getTextTokens();
        List<TextToken> sentences;
        List<TextToken> words;
        int indexStart, indexEnd;
        int internalIndexStart, internalIndexEnd;
        Word startWord, endWord;
        for (int p = 0; p < cloned.getTextTokens().size(); p++) {
            if (paragraphs.get(p) instanceof Paragraph) {
                sentences = paragraphs.get(p).getTextTokens();
                for (int s = 0; s < sentences.size(); s++) {
                    words = sentences.get(s).getTextTokens();
                    indexStart = -1;
                    indexEnd = -1;
                    internalIndexStart = -1;
                    internalIndexEnd = -1;
                    for (int w = 0; w < words.size() && indexStart == -1; w++) {
                        if (words.get(w) instanceof Word) {
                            internalIndexStart = words.get(w).getContent().indexOf(start);
                            indexStart = (internalIndexStart > 0) ? w : -1;
                        }
                    }
                    if (indexStart >= 0) {
                        for (int w = words.size() - 1; w >= indexStart && indexEnd == -1; w--) {
                            if (words.get(w) instanceof Word) {
                                internalIndexEnd = words.get(w).getContent().lastIndexOf(end);
                                indexEnd = (internalIndexEnd > 0) ? w : -1;
                            }
                        }
                        if (indexStart >= 0 && indexStart <= indexEnd) {
                            int internalIndexAfterEnd = internalIndexEnd + end.length();
                            startWord = (Word) words.get(indexStart);
                            if (indexEnd - indexStart > 0) {
                                endWord = (Word) words.get(indexEnd);
                                if (internalIndexStart == 0) {
                                    indexStart--;
                                } else {
                                    startWord.setContent(startWord.getContent().substring(0,
                                            internalIndexStart));
                                }
                                if (internalIndexAfterEnd == endWord.getContent().length()) {
                                    indexEnd++;
                                } else {
                                    endWord.setContent(
                                            endWord.getContent().substring(internalIndexAfterEnd));
                                }
                                if (indexEnd - indexStart > 1) {
                                    words.subList(indexStart + 1, indexEnd).clear();
                                }
                            } else if (indexEnd == indexStart
                                    && internalIndexEnd >= internalIndexStart) {
                                if (internalIndexStart == 0 && internalIndexAfterEnd == startWord
                                        .getContent().length()) {
                                    words.remove(indexStart);
                                } else {
                                    StringBuilder newWordBuilder =
                                            new StringBuilder(startWord.getContent());
                                    newWordBuilder.delete(internalIndexStart,
                                            internalIndexEnd + end.length());
                                    startWord.setContent(newWordBuilder.toString());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected RequestType getType() {
        return RequestType.TASK_11;
    }

}
