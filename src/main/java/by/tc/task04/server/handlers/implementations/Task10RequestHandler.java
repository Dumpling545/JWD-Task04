package by.tc.task04.server.handlers.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import by.tc.task04.entity.Paragraph;
import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.TextToken;
import by.tc.task04.entity.Word;
import by.tc.task04.server.parsers.Parser;
import by.tc.task04.server.parsers.implementations.StringToSentenceParser;
import by.tc.task04.transfer.RequestType;
import by.tc.task04.transfer.Response;

public class Task10RequestHandler extends AbstractTaskRequestHandler {

    class SentenceWrapper {
        private Sentence sentence;
        private int[] wordsFrequency;
        private boolean active = false;

        public SentenceWrapper(Sentence sentence, int size) {
            this.sentence = sentence;
            wordsFrequency = new int[size];
        }

        public boolean isActive() {
            return active;
        }

        public void setFrequency(int index, int value) {
            if (!active) {
                active = value > 0;
            }
            wordsFrequency[index] = value;
        }

        public String getString(String[] words) {
            Parser<String, Sentence> parser = StringToSentenceParser.getInstance();
            StringBuilder sb = new StringBuilder(parser.restore(sentence));
            sb.append('\n');
            for (int i = 0; i < words.length; i++) {
                sb.append(words[i]).append(": ").append(wordsFrequency[i]).append("  ");
            }
            return sb.toString();
        }
    }
    class Pair implements Comparable {
        private int frequency;
        private String word;

        public Pair(int frequency, String word) {
            this.frequency = frequency;
            this.word = word;
        }

        public String getWord() {
            return word;
        }

        @Override
        public int compareTo(Object o) {
            Pair other = (Pair) o;
            return other.frequency - frequency;
        }

        public Object getFrequency() {
            return frequency;
        }

    }

    @Override
    protected Response handle(String content, Text text) throws CloneNotSupportedException {
        final String[] targetWords = content.split(" ");
        int[] frequencies = new int[targetWords.length];
        List<TextToken> paragraphs = text.getTextTokens();
        List<TextToken> sentences;
        List<TextToken> words;
        List<SentenceWrapper> sentenceWrappers = new ArrayList<>();
        for (int p = 0; p < text.getTextTokens().size(); p++) {
            if (paragraphs.get(p) instanceof Paragraph) {
                sentences = paragraphs.get(p).getTextTokens();
                for (int s = 0; s < sentences.size(); s++) {
                    SentenceWrapper wrapper =
                            new SentenceWrapper((Sentence) sentences.get(s), targetWords.length);
                    words = sentences.get(s).getTextTokens().stream().filter(t -> t instanceof Word)
                            .collect(Collectors.toList());
                    for (int j = 0; j < targetWords.length; j++) {
                        final int finalJ = j;
                        int value = (int) words.stream()
                                .filter(t -> t.getContent().equals(targetWords[finalJ])).count();
                        wrapper.setFrequency(j, value);
                        frequencies[j] += value;
                    }
                    sentenceWrappers.add(wrapper);
                }
            }
        }
        StringBuilder responseContentBuilder =
                new StringBuilder(sentenceWrappers.stream().filter(sw -> sw.isActive())
                        .map(sw -> sw.getString(targetWords)).collect(Collectors.joining("\n\n")));

        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < targetWords.length; i++) {
            pairs.add(new Pair(frequencies[i], targetWords[i]));
        }
        responseContentBuilder.append("\n\n");
        responseContentBuilder.append(pairs.stream().sorted()
                .map(p -> String.format("%s (%d)", p.getWord(), p.getFrequency()))
                .collect(Collectors.joining(" ")));
        return new Response(responseContentBuilder.toString());
    }

    @Override
    protected RequestType getType() {
        return RequestType.TASK_10;
    }

}
