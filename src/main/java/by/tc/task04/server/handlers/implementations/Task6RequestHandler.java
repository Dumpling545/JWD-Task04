package by.tc.task04.server.handlers.implementations;

import java.util.Collections;
import java.util.List;
import by.tc.task04.transfer.RequestType;
import by.tc.task04.transfer.Response;

public class Task6RequestHandler extends AbstractWordsExtractingTaskRequestHandler {

    @Override
    protected Response handleWords(String content, List<String> uniqueWordsList) {
        Collections.sort(uniqueWordsList);
        char firstLetter = uniqueWordsList.get(0).charAt(0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < uniqueWordsList.size(); i++) {
            if (firstLetter != uniqueWordsList.get(i).charAt(0)) {
                sb.append('\n');
                firstLetter = uniqueWordsList.get(i).charAt(0);
            }
            sb.append(uniqueWordsList.get(i)).append(' ');
        }
        return new Response(sb.toString());
    }
    @Override
    protected RequestType getType() {
        return RequestType.TASK_6;
    }

}
