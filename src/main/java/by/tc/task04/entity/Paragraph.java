package by.tc.task04.entity;

import java.util.ArrayList;
import java.util.List;

public class Paragraph implements TextToken, Cloneable {
    private List<TextToken> sentences;

    public Paragraph() {
        sentences = new ArrayList<>();
    }

    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    public void removeSentence(Sentence sentence) {
        sentences.remove(sentence);
    }

    @Override
    public String getContent() {
        return null;
    }

    @Override
    public List<TextToken> getTextTokens() {
        return sentences;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Paragraph cloned = (Paragraph) super.clone();
        List<TextToken> newSentences = new ArrayList<>(sentences.size());
        Sentence sentence;
        for (TextToken token : sentences) {
            if (token instanceof Sentence) {
                sentence = (Sentence) token;
                newSentences.add((TextToken) sentence.clone());
            } 
        }
        cloned.sentences = newSentences;
        return cloned;
    }
}
