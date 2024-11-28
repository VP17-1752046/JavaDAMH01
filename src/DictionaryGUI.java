import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import utils.Dictionary;
import java.util.ArrayList;
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
        JButton randomButton = new JButton("Random Slang");
        randomButton.addActionListener(e -> randomSlang());

        inputPanel.add(new JLabel("Enter slang word:"));
        inputPanel.add(inputField);
        inputPanel.add(searchButton);
        inputPanel.add(loadButton);
        inputPanel.add(randomButton);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        result = new JTextArea();
        result.setEditable(false);
        result.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
        mainPanel.add(new JScrollPane(result), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        
        JButton findByDefinitionButton = new JButton("Find by Definition");
        findByDefinitionButton.addActionListener(e -> findByDefinition());

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addSlangWord());

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> editSlangWord());

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteSlangWord());
        
        JButton showHistoryButton = new JButton("History");
        showHistoryButton.addActionListener(e -> showHistory());

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetSlang());

        JButton quizSlangButton = new JButton("Quiz Slang");
        quizSlangButton.addActionListener(e -> quizSlang());
        
        JButton quizDefButton = new JButton("Quiz Definition");
        quizDefButton.addActionListener(e -> quizDefinition());

        buttonPanel.add(findByDefinitionButton);
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(showHistoryButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(quizSlangButton);
        buttonPanel.add(quizDefButton);

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

    private void addSlangWord() {
        String slang = JOptionPane.showInputDialog(this, "Enter new slang word:");
        if (slang != null && !slang.trim().isEmpty()) {
            String definition = JOptionPane.showInputDialog(this, "Enter definition:");
            if (definition != null) {
                AddSlang adder = new AddSlang(dictionary);
                String message = adder.add(slang, definition);
                result.setText(message);
            }
        }
    }

    private void editSlangWord() {
        String slang = JOptionPane.showInputDialog(this, "Enter slang word to edit:");
        if (slang != null && !slang.trim().isEmpty() && dictionary.getSlangMap().containsKey(slang)) {
            String newDefinition = JOptionPane.showInputDialog(this, "Enter new definition:");
            if (newDefinition != null) {
                EditSlang editor = new EditSlang(dictionary);
                String message = editor.edit(slang, newDefinition);
                result.setText(message);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Slang word not found.");
        }
    }

    private void deleteSlangWord() {
        String slang = JOptionPane.showInputDialog(this, "Enter slang word to delete:");
        if (slang != null && !slang.trim().isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Confirm to delete this slang word?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                DeleteSlang deleter = new DeleteSlang(dictionary);
                String message = deleter.delete(slang);
                result.setText(message);
            }
        }
    }

    private void resetSlang() {
        ResetSlangList resetter = new ResetSlangList(dictionary);
        resetter.reset();
        result.setText("Dictionary has been resetted!");
    }

    private void randomSlang() {
        RandomSlang random = new RandomSlang(dictionary);
        result.setText("On this day slang word:\n\n" + random.getRandom());
    }

    private void quizSlang() {
        QuizSlang quiz = new QuizSlang(dictionary);
        List<String> options = new ArrayList<>();
        StringBuilder question = new StringBuilder();

        int correctAnswer = quiz.generateQuestion(options, question);

        JDialog QuizDialog = new JDialog(this, "Quiz Slang", true);
        QuizDialog.setLayout(new BorderLayout());
        QuizDialog.setSize(500, 200);
        QuizDialog.setLocationRelativeTo(this);

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel questionTitle = new JLabel("What is the definition of the slang word?");
        questionTitle.setFont(new Font("Arial", Font.BOLD, 16));
        questionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel slangWord = new JLabel(question.toString());
        slangWord.setFont(new Font("Arial", Font.BOLD, 18));
        slangWord.setAlignmentX(Component.CENTER_ALIGNMENT);

        questionPanel.add(questionTitle);
        questionPanel.add(Box.createVerticalStrut(10)); 
        questionPanel.add(slangWord);
        questionPanel.add(Box.createVerticalStrut(10)); 

        QuizDialog.add(questionPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        for (int i = 0; i < options.size(); i++) {
            String option = options.get(i);
            JButton answerButton = new JButton(option);
            int selectedIndex = i + 1; 
            answerButton.addActionListener(e -> {
                if (selectedIndex == correctAnswer) {
                    JOptionPane.showMessageDialog(QuizDialog, "Correct Answer!", "Correct", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(QuizDialog, "Opps!!! Wrong answer.", "Wrong", JOptionPane.ERROR_MESSAGE);
                }
                QuizDialog.dispose(); 
            });
            buttonPanel.add(answerButton);
        }

        QuizDialog.add(buttonPanel, BorderLayout.CENTER);
        QuizDialog.setVisible(true);
    }

    private void quizDefinition() {
        QuizDefinition quiz = new QuizDefinition(dictionary);
        List<String> options = new ArrayList<>();
        StringBuilder question = new StringBuilder();

        int correctAnswer = quiz.generateQuiz(options, question);

        JDialog quizDialog = new JDialog(this, "Quiz Definition", true);
        quizDialog.setLayout(new BorderLayout());
        quizDialog.setSize(500, 200);
        quizDialog.setLocationRelativeTo(this);

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel questionTitle = new JLabel("What is the slang word of this definition?");
        questionTitle.setFont(new Font("Arial", Font.BOLD, 16));
        questionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel definitionLabel = new JLabel(question.toString()); 
        definitionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        definitionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        questionPanel.add(questionTitle);
        questionPanel.add(Box.createVerticalStrut(10)); 
        questionPanel.add(definitionLabel);
        questionPanel.add(Box.createVerticalStrut(10)); 

        quizDialog.add(questionPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        for (int i = 0; i < options.size(); i++) {
            String option = options.get(i);
            JButton answerButton = new JButton(option);
            int selectedIndex = i + 1; 
            answerButton.addActionListener(e -> {
                if (selectedIndex == correctAnswer) {
                    JOptionPane.showMessageDialog(quizDialog, "Correct Answer!", "Correct", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(quizDialog, "Opps!!! Wrong answer.", "Wrong", JOptionPane.ERROR_MESSAGE);
                }
                quizDialog.dispose(); 
            });
            buttonPanel.add(answerButton);
        }

        quizDialog.add(buttonPanel, BorderLayout.CENTER);
        quizDialog.setVisible(true);
    }
}
