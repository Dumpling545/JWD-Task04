package by.tc.task04.server.parsers.implementations;

import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import by.tc.task04.entity.Paragraph;
import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.TextToken;
import by.tc.task04.server.parsers.Parser;

public class StringToParagraphParser implements Parser<String, Paragraph> {

    private StringToParagraphParser() {
    }

    private static ThreadLocal<StringToParagraphParser> _threadLocal =
            new ThreadLocal<StringToParagraphParser>() {
                @Override
                protected StringToParagraphParser initialValue() {
                    return new StringToParagraphParser();
                }
            };

    public static StringToParagraphParser getInstance() {
        return _threadLocal.get();
    }

    @Override
    public Paragraph parse(String src) {
        Paragraph paragraph = new Paragraph();
        String sentenceRegex = ResourceBundle
                .getBundle(
                        "by.tc.task04.server.parsers.implementations.properties.RegularExpressions")
                .getString("sentence");
        Pattern pattern = Pattern.compile(sentenceRegex);
        Matcher matcher = pattern.matcher(src);
        while (matcher.find()) {
            paragraph.addSentence(StringToSentenceParser.getInstance().parse(matcher.group()));
        }
        return paragraph;
    }

    @Override
    public String restore(Paragraph src) {
        StringBuilder builder = new StringBuilder();
        TextToken token;
        for (int i = 0; i < src.getTextTokens().size(); i++) {
            token = src.getTextTokens().get(i);
            if (token instanceof Sentence) {
                builder.append(StringToSentenceParser.getInstance().restore((Sentence) token));
                if (i < src.getTextTokens().size() - 1)
                    builder.append(' ');
            }
        }
        return builder.toString();
    }

}
