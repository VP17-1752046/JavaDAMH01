package functions;

import utils.Dictionary;

public class EditSlang {
    private final Dictionary dictionary;

    public EditSlang(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public String edit(String slang, String newDefinition) {
        if (dictionary.getSlangMap().containsKey(slang)) {
            dictionary.getSlangMap().put(slang, newDefinition);
            dictionary.saveFile();
            return "Edited slang word: " + slang + "  ---  " + newDefinition;
        } else {
            return "Slang word not found.";
        }
    }
}
