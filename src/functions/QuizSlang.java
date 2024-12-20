package functions;

import utils.Dictionary;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class QuizSlang {
    private final Dictionary dictionary;

    public QuizSlang(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public int generateQuestion(List<String> options, StringBuilder question) {
        Map<String, String> slangMap = dictionary.getSlangMap();
        List<String> keys = new ArrayList<>(slangMap.keySet());
        Random quiz = new Random();

        String correctSlang = keys.get(quiz.nextInt(keys.size()));
        String correctDefinition = slangMap.get(correctSlang);

        options.add(correctDefinition); 

        while (options.size() < 4) {
            String randomDefinition = slangMap.get(keys.get(quiz.nextInt(keys.size())));
            if (!options.contains(randomDefinition)) {
                options.add(randomDefinition);
            }
        }

        Collections.shuffle(options);

        int correctIndex = options.indexOf(correctDefinition);

        question.append("").append(correctSlang);

        return correctIndex + 1; 
    }
}
