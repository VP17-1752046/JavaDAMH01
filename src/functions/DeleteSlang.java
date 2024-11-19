package functions;

import utils.Dictionary;

public class DeleteSlang {
    private final Dictionary dictionary;

    public DeleteSlang(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public String delete(String slang) {
        if (dictionary.getSlangMap().remove(slang) != null) {
            dictionary.saveFile();
            return "Slang word deleted: " + slang;
        } else {
            return "Slang word not found.";
        }
    }
}
