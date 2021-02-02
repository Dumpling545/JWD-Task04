package by.tc.task04.entity;

import java.util.ArrayList;
import java.util.List;

public class Text implements TextToken, Cloneable {
    private List<TextToken> paragraphs;

    public Text() {
        paragraphs = new ArrayList<>();
    }

    public void addParagraph(Paragraph paragraph) {
        paragraphs.add(paragraph);
    }

    public void removeSentence(Paragraph paragraph) {
        paragraphs.remove(paragraph);
    }

    public void addCodeExample(CodeExample example) {
        paragraphs.add(example);
    }

    public void removeCodeExample(CodeExample example) {
        paragraphs.remove(example);
    }

    @Override
    public String getContent() {
        return null;
    }

    @Override
    public List<TextToken> getTextTokens() {
        return paragraphs;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Text cloned = (Text) super.clone();
        List<TextToken> newParagraphs = new ArrayList<>(paragraphs.size());
        Paragraph paragraph;
        CodeExample codeExample;
        for (TextToken token : paragraphs) {
            if(token instanceof Paragraph){
                paragraph = (Paragraph) token;
                newParagraphs.add((TextToken) paragraph.clone());
            } else if(token instanceof CodeExample){
                codeExample = (CodeExample) token; 
                newParagraphs.add((TextToken) codeExample.clone());
            }
        }
        cloned.paragraphs = newParagraphs;
        return cloned;
    }


}
