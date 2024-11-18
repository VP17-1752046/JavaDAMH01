package functions;

import utils.Dictionary;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FindByDefinition {
    private final Dictionary dictionary;

    public FindByDefinition(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> find(String keyword) {
        List<String> results = new ArrayList<>();
        Map<String, String> slangMap = dictionary.getSlangMap();

        for (Map.Entry<String, String> entry : slangMap.entrySet()) {
            if (entry.getValue().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(entry.getKey() + "  ---  " + entry.getValue());
            }
        }

        if (!results.isEmpty()) {
            dictionary.getSearchHistory().add(keyword); 
        }
        
        return results;
    }
}
