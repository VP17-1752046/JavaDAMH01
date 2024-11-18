package functions;

import utils.Dictionary;
import java.util.List;

public class ShowHistory {
    private final Dictionary dictionary;

    public ShowHistory(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public String show() {
        List<String> history = dictionary.getSearchHistory();
        StringBuilder result = new StringBuilder("Search History List:\n\n");
        for (String term : history) {
            result.append(term).append("\n");
        }
        return result.toString();
    }
}
