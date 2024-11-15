import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import utils.Dictionary;

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

        inputPanel.add(new JLabel("Enter slang word:"));
        inputPanel.add(inputField);
        inputPanel.add(searchButton);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        result = new JTextArea();
        result.setEditable(false);
        mainPanel.add(new JScrollPane(result), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        
        JButton showAllButton = new JButton("Show All");
        JButton findByDefinitionButton = new JButton("Find by Definition");
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JButton showHistoryButton = new JButton("History");
        JButton randomButton = new JButton("Random Slang");
        JButton resetButton = new JButton("Reset");
        JButton quizSlangButton = new JButton("Quiz: Slang to Definition");
        JButton quizDefinitionButton = new JButton("Quiz: Definition to Slang");

        buttonPanel.add(showAllButton);
        buttonPanel.add(findByDefinitionButton);
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(showHistoryButton);
        buttonPanel.add(randomButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(quizSlangButton);
        buttonPanel.add(quizDefinitionButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}
