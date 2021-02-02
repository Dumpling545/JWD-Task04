package by.tc.task04.entity;

import java.util.ArrayList;
import java.util.List;

public class Sentence implements TextToken, Cloneable {
    private List<TextToken> words;

    public Sentence() {
        words = new ArrayList<>();
    }

    public void addWord(Word word) {
        words.add(word);
    }

    public void removeWord(Word word) {
        words.remove(word);
    }

    public void addPunctuationMark(PunctuationMark mark) {
        words.add(mark);
    }

    public void removePunctuationMark(PunctuationMark mark) {
        words.remove(mark);
    }

    @Override
    public String getContent() {
        return null;
    }

    @Override
    public List<TextToken> getTextTokens() {
        return words;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        Sentence cloned = (Sentence) super.clone();
        List<TextToken> newWords = new ArrayList<>(words.size());
        Word word;
        PunctuationMark punctuationMark;
        for (TextToken token : words) {
            if(token instanceof Word){
                word = (Word) token;
                newWords.add((TextToken) word.clone());
            } else if(token instanceof PunctuationMark){
                punctuationMark = (PunctuationMark) token; 
                newWords.add((TextToken) punctuationMark.clone());
            }
        }
        cloned.words = newWords;
        return cloned;
    }
}
