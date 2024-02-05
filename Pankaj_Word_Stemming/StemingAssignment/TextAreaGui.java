package StemingAssignment;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class TextAreaGui{
    public TextAreaGui(String s) throws IOException {
        JFrame f = new JFrame();
        String filePath = s;  // Replace with the actual file path
        String content = readFile(filePath);
        JTextArea area1 = new JTextArea();
        area1.setText("Original Text:\n" + "  "+ content);
        JTextArea area2 = new JTextArea("Stemmed Text : " + "\n");
        JScrollPane scrollPane1 = new JScrollPane(area1);
        JScrollPane scrollPane2 = new JScrollPane(area2);

        JButton b = new JButton("Click here to convert original text to stemmed text");
        area1.setBounds(350, 100, 1300, 300);
        area2.setBounds(350, 500, 1300, 300);
        scrollPane1.setBounds(350, 100, 1300, 300);
        scrollPane2.setBounds(350, 500, 1300, 300);
        b.setBounds(510, 425, 1000, 50);

        b.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                File fr = new File(s);
                // Create an instance of the Stemmer_word class
                Stemmer_word stemmer = new Stemmer_word();
                // Declare a variable to store the suffix
                String suffix = "";
                // Declare a variable to store the stemmed word
                String stemmedWord = "";
                try (Scanner sc = new Scanner(fr)) {
                    while (sc.hasNextLine()) {
                        String line = sc.nextLine();
                        String[] words = line.split(" ");
                        for (String s : words) {
                            if (s.contains(",") || s.contains(".")){
                                s = s.substring(0, s.length() - 1);
                            }
                            suffix = Word_Stemming.checkWordsEnd(s, suffix);
                            stemmedWord = Word_Stemming.stemmedWord(suffix, stemmedWord, stemmer, s);
                            if (stemmedWord.length() != 0){
                                area2.append("  " + s + " -> " + stemmedWord + "\n");
                            }
                        }
                    }
                } catch (IOException ex) {
                    // Code to handle the IOException
                    ex.printStackTrace();
                }
            }
        });
        f.add(scrollPane1);
        f.add(b);
        f.add(scrollPane2);
        f.setSize(1800, 1500);
        f.setLayout(null);
        f.setVisible(true);
    }

    private String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine());
                content.append("\n\n");
            }
        }
    return content.toString();
    }
}
