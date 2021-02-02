package by.tc.task04.entity;

import java.util.List;

public class PunctuationMark implements TextToken, Cloneable {
    private String content;

    public PunctuationMark(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public List<TextToken> getTextTokens() {
        return null;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
