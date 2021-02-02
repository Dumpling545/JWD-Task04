package by.tc.task04.entity;

import java.util.List;

public class Word implements TextToken, Cloneable, Comparable {
    private String content;

    public Word(String content) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Word other = (Word) obj;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        return true;
    }

    @Override
    public int compareTo(Object o) {
        Word other = (Word) o;
        return content.compareTo(other.content);
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
