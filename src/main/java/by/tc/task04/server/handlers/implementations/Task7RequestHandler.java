package by.tc.task04.server.handlers.implementations;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import by.tc.task04.transfer.RequestType;
import by.tc.task04.transfer.Response;

public class Task7RequestHandler extends AbstractWordsExtractingTaskRequestHandler {

    class ProportionOfVowelsComparator implements Comparator<String> {
        private int countVowels(String word) {
            int vowelCounter = 0;
            for (int i = 0; i < word.length(); i++) {
                if ("eEyYuUiIoOaA".indexOf(word.charAt(i)) != -1)
                    vowelCounter++;
            }
            return vowelCounter;
        }

        @Override
        public int compare(String word1, String word2) {
            int vowels1 = countVowels(word1);
            int vowels2 = countVowels(word2);
            return vowels1 * word2.length() - vowels2 * word1.length();
        }

    }

    @Override
    protected Response handleWords(String content, List<String> uniqueWordsList) {
        String responseContent = uniqueWordsList.stream()
                .sorted(new ProportionOfVowelsComparator())
                .collect(Collectors.joining(" "));
        return new Response(responseContent);
    }

    @Override
    protected RequestType getType() {
        return RequestType.TASK_7;
    }
}

