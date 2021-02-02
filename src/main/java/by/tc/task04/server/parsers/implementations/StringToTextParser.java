package by.tc.task04.server.parsers.implementations;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import by.tc.task04.entity.CodeExample;
import by.tc.task04.entity.Paragraph;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.TextToken;
import by.tc.task04.server.exceptions.ParseException;
import by.tc.task04.server.parsers.Parser;

public class StringToTextParser implements Parser<String, Text> {
    private StringToTextParser() {
    }

    private static ThreadLocal<StringToTextParser> _threadLocal =
            new ThreadLocal<StringToTextParser>() {
                @Override
                protected StringToTextParser initialValue() {
                    return new StringToTextParser();
                }
            };

    public static StringToTextParser getInstance() {
        return _threadLocal.get();
    }

    @Override
    public Text parse(String src) throws ParseException{
        Text text = new Text();
        ResourceBundle regexBundle = ResourceBundle.getBundle(
                "by.tc.task04.server.parsers.implementations.properties.RegularExpressions");
        String codeExampleRegex = regexBundle.getString("codeExample");
        Pattern pattern = Pattern.compile(codeExampleRegex);
        Matcher matcher = pattern.matcher(src);
        int paragraphStart = 0;
        String paragraphText;
        while (matcher.find()) {
            paragraphText = src.substring(paragraphStart, matcher.start()).trim();
            if (!paragraphText.isEmpty())
                text.addParagraph(StringToParagraphParser.getInstance().parse(paragraphText));
            paragraphStart = matcher.end();
            text.addCodeExample(new CodeExample(matcher.group()));
        }
        paragraphText = src.substring(paragraphStart).trim();
        if (!paragraphText.matches(regexBundle.getString("paragraph"))){
            throw new ParseException(paragraphText);
        }
        if (!paragraphText.isEmpty())
            text.addParagraph(StringToParagraphParser.getInstance().parse(paragraphText));
        return text;
    }

    @Override
    public String restore(Text src) {
        StringBuilder builder = new StringBuilder();
        CodeExample codeExample;
        for (TextToken token : src.getTextTokens()) {
            if (token instanceof Paragraph) {
                builder.append(StringToParagraphParser.getInstance().restore((Paragraph) token));
            } else if (token instanceof CodeExample) {
                builder.append('\n');
                codeExample = (CodeExample) token;
                builder.append(codeExample.getContent());
                builder.append('\n');
            }
        }
        return builder.toString();
    }

}
