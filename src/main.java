import utils.Dictionary;
import javax.swing.*;

public class main {
    public static void main(String[] args) {
            Dictionary dictionary = new Dictionary();
            DictionaryGUI gui = new DictionaryGUI(dictionary);
            gui.setVisible(true);
    }
}

