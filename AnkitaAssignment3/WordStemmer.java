import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class WordStemmer{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Word Stemmer");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel inputLabel = new JLabel("Enter your text:");
        inputLabel.setBounds(10, 20, 120, 25);
        panel.add(inputLabel);

        JTextArea inputTextArea = new JTextArea();
        inputTextArea.setBounds(10, 50, 360, 100);
        panel.add(inputTextArea);

        JButton processButton = new JButton("Process");
        processButton.setBounds(150, 170, 100, 25);
        panel.add(processButton);

        JLabel outputLabel = new JLabel("Stemmed text:");
        outputLabel.setBounds(10, 200, 120, 25);
        panel.add(outputLabel);

        JTextArea outputTextArea = new JTextArea();
        outputTextArea.setBounds(10, 230, 360, 50);
        panel.add(outputTextArea);

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputTextArea.getText();
                String[] words = inputText.split("\\s+");
                Map<String, String> stemmedWords = new HashMap<>();

                for (String word : words) {
                    String root = stemWord(word);
                    if (!word.equals(root)) {
                        stemmedWords.put(word, root);
                    }
                }

                StringBuilder outputText = new StringBuilder();
                for (Map.Entry<String, String> entry : stemmedWords.entrySet()) {
                    outputText.append(entry.getKey()).append(" --> ").append(entry.getValue()).append("\n");
                }
                outputTextArea.setText(outputText.toString());
            }

            private String stemWord(String word) {
                // Simplified stemming logic for demonstration
                if (word.endsWith("ing")) {
                    return word.substring(0, word.length() - 3);
                } else if (word.endsWith("s")) {
                    return word.substring(0, word.length() - 1);
                } else {
                    return word;
                }
            }
        });

        frame.setVisible(true);
    }
}