package by.tc.task04.entity;

import java.util.List;

public class CodeExample implements TextToken, Cloneable {

    private String code;

    public CodeExample(String code) {
        this.code = code;
    }

    @Override
    public String getContent() {
        return code;
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
