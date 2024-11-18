import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import utils.Dictionary;
import functions.*;

public class DictionaryGUI extends JFrame {
    private Dictionary dictionary;
    private JTextField inputField;
    private JTextArea result;

    public DictionaryGUI(Dictionary dictionary) {
        this.dictionary = dictionary;
        setupUI();
    }

    private void setupUI() {
        setTitle("Slang Dictionary");
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(20);
        JButton searchButton = new JButton("Search Slang");
        searchButton.addActionListener(e -> searchSlangWord());
        inputField.addActionListener(e -> searchSlangWord());
        JButton loadButton = new JButton("Load Slang");
        loadButton.addActionListener(e -> showAllDefinitions());

        inputPanel.add(new JLabel("Enter slang word:"));
        inputPanel.add(inputField);
        inputPanel.add(searchButton);
        inputPanel.add(loadButton);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        result = new JTextArea();
        result.setEditable(false);
        result.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
        mainPanel.add(new JScrollPane(result), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        
        // JButton showAllButton = new JButton("Show All");
        JButton findByDefinitionButton = new JButton("Find by Definition");
        findByDefinitionButton.addActionListener(e -> findByDefinition());

        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        
        JButton showHistoryButton = new JButton("History");
        showHistoryButton.addActionListener(e -> showHistory());

        JButton randomButton = new JButton("Random Slang");
        JButton resetButton = new JButton("Reset");
        JButton quizButton = new JButton("Quiz");

        // buttonPanel.add(showAllButton);
        buttonPanel.add(findByDefinitionButton);
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(showHistoryButton);
        buttonPanel.add(randomButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(quizButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        showAllDefinitions();
    }

    private void showAllDefinitions() {
        StringBuilder allDefinitions = new StringBuilder();
        for (String slang : dictionary.getSlangMap().keySet()) {
            allDefinitions.append(slang).append("  ---  ").append(dictionary.getSlangMap().get(slang)).append("\n");
        }
        result.setText(allDefinitions.toString());
    }

    private void searchSlangWord() {
        String slang = inputField.getText().trim();
        FindSlangWord finder = new FindSlangWord(dictionary);
        String output = finder.find(slang);
        result.setText(output != null ? "Definition: " + output : "Slang word not found!");
    }

    private void findByDefinition() {
        String keyword = JOptionPane.showInputDialog(this, "Enter keyword to search definition:");
        if (keyword != null && !keyword.trim().isEmpty()) {
            FindByDefinition finder = new FindByDefinition(dictionary);
            List<String> output = finder.find(keyword);
            result.setText(output.isEmpty() ? "Slang word not found!" : String.join("\n", output));
        }
    }

    private void showHistory() {
        ShowHistory history = new ShowHistory(dictionary);
        result.setText(history.show());
    }
}
