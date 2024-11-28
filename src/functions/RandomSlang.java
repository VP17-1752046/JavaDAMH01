package functions;

import utils.Dictionary;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class RandomSlang {
    private final Dictionary dictionary;

    public RandomSlang(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public String getRandom() {
        List<String> keys = new ArrayList<>(dictionary.getSlangMap().keySet());
        String randomSlang = keys.get(new Random().nextInt(keys.size()));
        return randomSlang + "  ---  " + dictionary.getSlangMap().get(randomSlang);
    }
}
