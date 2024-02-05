import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WordStemmerGUI extends JFrame implements ActionListener {
    JTextField inputTextArea;
    JTextArea outputTextArea;
    JButton stemButton;
    Stemmer_word stemmer;

    WordStemmerGUI() {
        inputTextArea = new JTextField();
        inputTextArea.setBounds(100, 50, 1350, 100);

        outputTextArea = new JTextArea();
        outputTextArea.setBounds(100, 280, 1350, 100);
        outputTextArea.setEditable(false);

        stemButton = new JButton("Stem Inflected Words");
        stemButton.setBounds(700, 200, 180, 30);
        stemButton.addActionListener(this);

        add(stemButton);
        add(inputTextArea);
        add(outputTextArea);

        stemmer = new Stemmer_word();  // Initialize the Stemmer_word instance

        setSize(1600, 600);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == stemButton) {
        String inputText = inputTextArea.getText();
        String[] words = inputText.split("\\s+"); // Split the input sentence into words
        StringBuilder stemmedWords = new StringBuilder();

        for (String word : words) {
            String stemmedWord = stemmer.stemWords(word);
            if (!word.equals(stemmedWord)) {
                stemmedWords.append(word).append(" --> ").append(stemmedWord).append("\n");
            }      
        }

        outputTextArea.setText(stemmedWords.toString().trim());


            }
}


    public static void main(String[] args) {
        new WordStemmerGUI();
    }
}