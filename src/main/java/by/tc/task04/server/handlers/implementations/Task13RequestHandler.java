package by.tc.task04.server.handlers.implementations;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import by.tc.task04.transfer.RequestType;
import by.tc.task04.transfer.Response;

public class Task13RequestHandler extends AbstractWordsExtractingTaskRequestHandler {
    class AmountOfCharacterComparator implements Comparator<String> {
        private char c;

        public AmountOfCharacterComparator(char c) {
            this.c = c;
        }

        @Override
        public int compare(String word1, String word2) {
            int amount1 = amount(word1);
            int amount2 = amount(word2);
            if (amount1 == amount2) {
                return word1.compareTo(word2);
            } else {
                return amount2 - amount1;
            }
        }

        private int amount(String word) {
            int n = 0;
            for(int i = 0; i < word.length(); i++){
                if(word.charAt(i) == c){
                    n++;
                }
            }
            return n;
        }

    }

    @Override
    protected Response handleWords(String content, List<String> uniqueWordsList) {
        char c = content.charAt(0);
        String responseContent = uniqueWordsList.stream().sorted(new AmountOfCharacterComparator(c))
                .collect(Collectors.joining(" "));
        return new Response(responseContent);
    }

    @Override
    protected RequestType getType() {
        return RequestType.TASK_13;
    }

}
