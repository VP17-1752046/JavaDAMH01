package functions;

import utils.Dictionary;
import java.io.*;

public class ResetSlangList {
    private final Dictionary dictionary;
    private final String backupFile = "slang_backup.txt"; 

    public ResetSlangList(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void reset() {
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(backupFile));
                 PrintWriter writer = new PrintWriter(new FileWriter(dictionary.getFileSlang()))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    writer.println(line);
                }
            }

            dictionary.getSlangMap().clear();
            dictionary.loadFile();

        } catch (IOException e) {
            System.out.println("Error resetting dictionary: " + e.getMessage());
        }
    }
}
