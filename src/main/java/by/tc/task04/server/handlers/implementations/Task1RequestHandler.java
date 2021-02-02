package by.tc.task04.server.handlers.implementations;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import by.tc.task04.entity.Paragraph;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.TextToken;
import by.tc.task04.entity.Word;
import by.tc.task04.transfer.RequestType;
import by.tc.task04.transfer.Response;

public class Task1RequestHandler extends AbstractTaskRequestHandler {
    class Item {
        private int frequency = 1;
        private int lastSentence;

        public Item(int lastSentence){
            this.lastSentence = lastSentence;
        }
        public void incrementFrequency(int sentence) {
            if (sentence != lastSentence) {
                frequency++;
                lastSentence = sentence;

            }
        }

        public int getFrequency() {
            return frequency;
        }
    }

    @Override
    protected Response handle(String content, Text text) throws CloneNotSupportedException {
        Map<Word, Item> map = new TreeMap<>();
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
                            if (map.containsKey(word)) {
                                map.get(word).incrementFrequency(s);
                            } else {
                                map.put(word, new Item(s));
                            }
                        }
                    }
                }
            }
        }
        Entry<Word, Item> maxEntry = Collections.max(map.entrySet(),
                (e1, e2) -> e1.getValue().getFrequency() - e2.getValue().getFrequency());
        String responseContent = String.format("Word %s, %d sentences",
                maxEntry.getKey().getContent(), maxEntry.getValue().getFrequency());
        return new Response(responseContent);
    }

    @Override
    protected RequestType getType() {
        return RequestType.TASK_1;
    }

}
