package by.tc.task04.server.handlers.implementations;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import by.tc.task04.transfer.RequestType;
import by.tc.task04.transfer.Response;

public class Task8RequestHandler extends AbstractWordsExtractingTaskRequestHandler {
    class FirstConsonantComparator implements Comparator<String> {
        private char firstConsonant(String word) {
            char firstConsonant = 'a' - 1;
            for (int i = 0; i < word.length(); i++) {
                if ("eEyYuUiIoOaA".indexOf(word.charAt(i)) == -1) {
                    firstConsonant = word.charAt(i);
                    break;
                }
            }
            return firstConsonant;
        }

        @Override
        public int compare(String word1, String word2) {
            char fc1 = firstConsonant(word1);
            char fc2 = firstConsonant(word2);
            return fc1 - fc2;
        }

    }

    @Override
    protected Response handleWords(String content, List<String> uniqueWordsList) {
        String responseContent =
                uniqueWordsList.stream().filter(w -> "eEyYuUiIoOaA".indexOf(w.charAt(0)) != -1)
                        .sorted(new FirstConsonantComparator()).collect(Collectors.joining(" "));
        return new Response(responseContent);
    }

    @Override
    protected RequestType getType() {
        return RequestType.TASK_8;
    }

}
