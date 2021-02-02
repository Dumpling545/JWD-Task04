package by.tc.task04.entity;

import java.io.Serializable;
import java.util.List;

public interface TextToken extends Serializable{
    public String getContent();
    public List<TextToken> getTextTokens();
}
