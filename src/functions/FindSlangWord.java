package functions;

import utils.Dictionary;

public class FindSlangWord {
    private final Dictionary dictionary;

    public FindSlangWord(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public String find(String slang) {
        String definition = dictionary.getSlangMap().get(slang);
        if (definition != null) {
            dictionary.getSearchHistory().add(slang);
        }
        return definition;
    }
}
