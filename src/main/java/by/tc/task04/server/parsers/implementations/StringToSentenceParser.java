package by.tc.task04.server.parsers.implementations;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import by.tc.task04.entity.PunctuationMark;
import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.TextToken;
import by.tc.task04.entity.Word;
import by.tc.task04.server.parsers.Parser;

public class StringToSentenceParser implements Parser<String, Sentence> {

    private StringToSentenceParser() {
    }

    private static ThreadLocal<StringToSentenceParser> _threadLocal =
            new ThreadLocal<StringToSentenceParser>() {
                @Override
                protected StringToSentenceParser initialValue() {
                    return new StringToSentenceParser();
                }
            };

    public static StringToSentenceParser getInstance() {
        return _threadLocal.get();
    }

    @Override
    public Sentence parse(String src) {
        Sentence sentence = new Sentence();
        String wordOrPunctuationMarkRegex = ResourceBundle
                .getBundle(
                        "by.tc.task04.server.parsers.implementations.properties.RegularExpressions")
                .getString("wordOrPunctuationMark");
        Pattern pattern = Pattern.compile(wordOrPunctuationMarkRegex);
        Matcher matcher = pattern.matcher(src);
        while (matcher.find()) {
            if (matcher.group("word") != null) {
                sentence.addWord(new Word(matcher.group("word")));
            } else if (matcher.group("punctuationMark") != null) {
                sentence.addPunctuationMark(new PunctuationMark(matcher.group("punctuationMark")));
            }
        }
        return sentence;
    }

    @Override
    public String restore(Sentence src) {
        StringBuilder builder = new StringBuilder();
        Word word;
        PunctuationMark punctuationMark;
        TextToken token;
        for (int i = 0; i < src.getTextTokens().size(); i++) {
            token = src.getTextTokens().get(i);
            if (token instanceof Word) {
                word = (Word) token;
                if (i > 0)
                    builder.append(' ');
                builder.append(word.getContent());
            } else if (token instanceof PunctuationMark) {
                punctuationMark = (PunctuationMark) token;
                builder.append(punctuationMark.getContent());
            }
        }
        return builder.toString();
    }

}
