package by.tc.task04.server.handlers.implementations;

import java.util.stream.Collectors;
import by.tc.task04.entity.Paragraph;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.Word;
import by.tc.task04.transfer.RequestType;

public class Task16RequestHandler extends AbstractTextModifyingTaskRequestHandler {

    @Override
    protected void handleCloned(String content, Text cloned) {
        String[] params = content.split(" ", 4);
        int paragraph = Integer.parseInt(params[0]);
        int sentence = Integer.parseInt(params[1]);
        int wordLength = Integer.parseInt(params[2]);
        cloned.getTextTokens().stream().filter(t -> t instanceof Paragraph)
                .collect(Collectors.toList()).get(paragraph).getTextTokens().get(sentence)
                .getTextTokens().forEach(w -> {
                    if (w instanceof Word && w.getContent().length() == wordLength) {
                        ((Word) w).setContent(params[3]);
                    }
                });
    }

    @Override
    protected RequestType getType() {
        return RequestType.TASK_16;
    }

}
