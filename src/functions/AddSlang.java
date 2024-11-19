package functions;

import utils.Dictionary;
import javax.swing.JOptionPane;

public class AddSlang {
    private final Dictionary dictionary;

    public AddSlang(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public String add(String slang, String definition) {
        if (dictionary.getSlangMap().containsKey(slang)) {
            String[] options = {"Overwrite", "Duplicate"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "The slang word \"" + slang + "\" already exists. Do you want to: ?",
                    "Duplicate Slang Word",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == 0) {
                dictionary.getSlangMap().put(slang, definition);
                dictionary.saveFile(); 
                return "Slang word overwritten: " + slang + "  ---  " + definition;
            } else if (choice == 1) {
                String newSlang = slang + "_copy"; 
                int counter = 1; 
                while (dictionary.getSlangMap().containsKey(newSlang)) {
                    newSlang = slang + "_copy" + counter;
                    counter++;
                }
                dictionary.getSlangMap().put(newSlang, definition);
                dictionary.saveFile();
                return "Slang word duplicated: " + newSlang + "  ---  " + definition;
            } else {
                return "Exit Add";
            }
        } else {
            dictionary.getSlangMap().put(slang, definition);
            dictionary.saveFile(); 
            return "Slang word added: " + slang + "  ---  " + definition;
        }
    }
}
