package utils;

import java.io.*;
import java.util.*;

public class Dictionary {
    private Map<String, String> slangMap = new LinkedHashMap<>();
    private List<String> searchHistory = new ArrayList<>();
    private final String fileSlang = "slang.txt";
    
    public Dictionary() {
        loadFile();
    }

    public String getFileSlang() {
        return fileSlang;
    }

    public Map<String, String> getSlangMap() {
        return slangMap;
    }

    public List<String> getSearchHistory() {
        return searchHistory;
    }

    public void loadFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileSlang))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("`", 2);
                if (parts.length == 2) {
                    slangMap.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    public void saveFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileSlang))) {
            for (Map.Entry<String, String> entry : slangMap.entrySet()) {
                writer.println(entry.getKey() + "`" + entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
