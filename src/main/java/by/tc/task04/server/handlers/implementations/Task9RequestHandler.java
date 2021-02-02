package by.tc.task04.server.handlers.implementations;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import by.tc.task04.transfer.RequestType;
import by.tc.task04.transfer.Response;

public class Task9RequestHandler extends AbstractWordsExtractingTaskRequestHandler {
    class NumberOfLetterInclusionsComparator implements Comparator<String> {
        private char c;

        public NumberOfLetterInclusionsComparator(char c) {
            this.c = c;
        }

        private int letterInclusions(String word) {
            int counter = 0;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == c) {
                    counter++;
                }
            }
            return counter;
        }

        @Override
        public int compare(String word1, String word2) {
            int li1 = letterInclusions(word1);
            int li2 = letterInclusions(word2);
            return (li1 == li2 ? word1.compareTo(word2) : li1 - li2);
        }

    }

    @Override
    protected Response handleWords(String content, List<String> uniqueWordsList) {
        String responseContent = uniqueWordsList.stream()
                .sorted(new NumberOfLetterInclusionsComparator(content.charAt(0)))
                .collect(Collectors.joining(" "));
        return new Response(responseContent);
    }

    @Override
    protected RequestType getType() {
        return RequestType.TASK_9;
    }

}
