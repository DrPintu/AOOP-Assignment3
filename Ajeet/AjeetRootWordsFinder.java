import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AjeetRootWordsFinder extends JFrame {

    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton findInflectedWordsButton;
    private Set<String> stopWords;

    public AjeetRootWordsFinder() {
        setTitle("Inflected Words Finder");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inputTextArea = new JTextArea();
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        inputTextArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Color.BLACK)
        ));

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        outputTextArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Color.BLACK)
        ));

        findInflectedWordsButton = new JButton("Stem Inflected Words");
        findInflectedWordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findInflectedWords();
            }
        });

        // Add common English words to the stopWords set
        stopWords = new HashSet<>(Arrays.asList(
                // Add your stop words here
        ));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(new JScrollPane(inputTextArea), gbc);

        gbc.gridy++;
        gbc.weighty = 0;
        mainPanel.add(findInflectedWordsButton, gbc);

        gbc.gridy++;
        gbc.weighty = 1;
        mainPanel.add(new JScrollPane(outputTextArea), gbc);

        add(mainPanel);
    }

    private void findInflectedWords() {
        String paragraph = inputTextArea.getText();
        java.util.List<String> words = extractWords(paragraph);
        java.util.List<String> inflectedWords = new ArrayList<>();

        for (String word : words) {
            String rootForm = StemmingHelper.getRootForm(word, stopWords);
            if (!rootForm.equals(word)) {
                inflectedWords.add(word + " --> " + rootForm);
            }
        }

        // Display the results in the outputTextArea
        outputTextArea.setText(String.join("\n", inflectedWords));
    }

    private java.util.List<String> extractWords(String text) {
        return Arrays.asList(text.split("\\s+"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AjeetRootWordsFinder rootWordsFinder = new AjeetRootWordsFinder();
                rootWordsFinder.setVisible(true);
            }
        });
    }
}

class StemmingHelper {
    public static String getRootForm(String word, Set<String> stopWords) {
        if (stopWords.contains(word.toLowerCase())) {
            return word;
        }

        Stemmer_word stemmer = new Stemmer_word();

        // Apply stemming rules using Stemmer_word
        word = stemmer.stemEd(word);
        word = stemmer.stemLy(word);
        word = stemmer.stemFul(word);
        word = stemmer.stemEst(word);
        word = stemmer.stemIty(word);
        word = stemmer.stemNess(word);
        word = stemmer.stemEs(word);
        word = stemmer.stemEr(word);
        word = stemmer.stemIc(word);
        word = stemmer.stemIng(word);
        word = stemmer.stemS(word);

        return word;
    }

    static class Stemmer_word {
        public String stemEd(String word) {
            if (word.length() >= 6 && word.endsWith("ceed")) {
                return word.substring(0, word.length() - 3) + "ess";
            }
            // If word length is greater than or equal to 6 and the word ends with eed, remove d from last
            if (word.length() >= 6 && word.endsWith("eed")) {
                return word.substring(0, word.length() - 1);
            }

            if (word.length() >= 6 && word.endsWith("ied")) {
                return word.substring(0, word.length() - 3) + "y";
            }

            if (word.length() >= 6 && word.endsWith("sed")) {
                return word.substring(0, word.length() - 2);
            }

            if (word.length() >= 5 && word.endsWith("ered")) {
                return word.substring(0, word.length() - 2);
            }

            // If word length is greater than or equal to 5 and the word ends with vowel + red or ted, remove d from last
            if (word.length() >= 5 && word.matches(".*[aeiou]red$|.*[aeiou]ted$|.*[aeiou]zed$")) {
                return word.substring(0, word.length() - 1);
            }

            // If word length is greater than or equal to 6 and the word ends with ed, remove ed from last
            if (word.length() >= 6 && word.endsWith("ed")) {
                return word.substring(0, word.length() - 2);
            }

            return word;
        }

        public String stemLy(String word) {
            // If the word length is greater than or equal to 5, apply the rules
            if (word.length() >= 5) {
                // If the word ends with aly, remove y from the end
                if (word.endsWith("aly")) {
                    return word.substring(0, word.length() - 1);
                }

                // If the word ends with ily, remove ily from the end and add y
                if (word.endsWith("ily")) {
                    return word.substring(0, word.length());
                }

                // If the word ends with ly, remove ly from the end
                if (word.endsWith("ly")) {
                    return word.substring(0, word.length() - 2);
                }
            }

            return word;
        }

        public String stemFul(String word) {
            // If the word ends with iful, remove iful from the end and add y
            if (word.length() >= 6 && word.endsWith("iful")) {
                return word.substring(0, word.length() - 4) + "y";
            }

            // If the word ends with full, remove ful from the end
            if (word.length() >= 6 && word.endsWith("ful")) {
                return word.substring(0, word.length() - 3);
            }

            return word;
        }

        public String stemEst(String word) {
            // If the word length is greater than or equal to 6, apply the rules
            if (word.length() >= 6) {
                // If the word ends with iest, remove iest from the end and add y
                if (word.endsWith("iest")) {
                    return word.substring(0, word.length() - 4) + "y";
                }

                // If the word ends with llest, remove lest from the end and add l
                if (word.endsWith("llest")) {
                    return word.substring(0, word.length() - 4);
                }

                // If the word ends with est, remove est from the end
                if (word.endsWith("est")) {
                    return word.substring(0, word.length() - 3);
                }
            }
            return word;
        }

        public String stemIty(String word) {
            // Check if the word length is 7 or more
            if (word.length() >= 7) {
                // Check if the word ends with ity
                if (word.endsWith("ity")) {
                    // Check if the word ends with ility
                    if (word.endsWith("ility")) {
                        // Remove ility and add le
                        return word.substring(0, word.length() - 5) + "le";
                    }
                    // Check if the word ends with vity
                    else if (word.endsWith("vity")) {
                        // Remove ity and add e
                        return word.substring(0, word.length() - 3) + "e";
                    } else if (word.endsWith("nity")) {
                        // Remove ity and add e
                        return word.substring(0, word.length() - 3) + "e";
                    }
                    // Otherwise, remove ity
                    else {
                        return word.substring(0, word.length() - 3);
                    }
                }
                // If the word does not end with ity, return the original word
                else {
                    return word;
                }
            }
            // If the word length is less than 7, return the original word
            else {
                return word;
            }
        }

        public String stemNess(String word) {
            if (word.length() >= 5) {
                if (word.endsWith("iness")) {
                    word = word.substring(0, word.length() - 5) + "y";
                } else if (word.endsWith("ness")) {
                    word = word.substring(0, word.length() - 4);
                }
            }
            return word;

        }

        public String stemEs(String word) {
            // Check if the word ends with es
            if (word.endsWith("es")) {
                // Check if the word ends with ies
                if (word.endsWith("ies")) {
                    // Check if the word length is 8 or more
                    if (word.length() >= 8) {
                        // Remove ies and add y
                        return word.substring(0, word.length() - 3) + "y";
                    }
                    // If the word length is less than 8, return the original word
                    else {
                        return word;
                    }
                }
                // Check if the word ends with vowel+consonant+es
                else if (word.matches(".*[aeiou][^aeiou]es$")) {
                    // Remove s
                    return word.substring(0, word.length() - 1);
                }
                // Check if the word ends with ves
                else if (word.endsWith("ves")) {
                    // Remove ves and add f
                    return word.substring(0, word.length() - 3) + "f";
                }
                // Otherwise, remove es
                else {
                    return word.substring(0, word.length() - 2);
                }
            }
            // If the word does not end with es, return the original word
            else {
                return word;
            }
        }


        public String stemEr(String word) {

            // If the word length is greater than or equal to 5 and the word ends with "ier", replace "ier" with "y"
            if (word.length() >= 5 && word.endsWith("ier")) {
                return word.substring(0, word.length() - 3) + "y";
            }

            // If the word length is greater than or equal to 7 and the word ends with "izer", remove "er" from the end
            if (word.length() >= 7 && word.endsWith("izer")) {
                return word.substring(0, word.length() - 1);
            }
            // If the word length is greater than or equal to 4 and the word ends with "er", remove "er" from the end
            if (word.length() >= 4 && word.endsWith("er")) {
                return word.substring(0, word.length() - 2);
            }
            // Return the original word if it doesn't match any of the rules
            return word;
        }

        public String stemIc(String word) {
            // If the word length is greater than or equal to 6 and the word ends with "gic" or "mic", replace "ic" with "y"
            if (word.length() >= 6 && (word.endsWith("gic") || word.endsWith("mic"))) {
                return word.substring(0, word.length() - 2) + "y";
            }

            // If the word length is greater than or equal to 5 and the word ends with "atic", remove "ic" from the end
            if (word.length() >= 5 && word.endsWith("atic")) {
                return word.substring(0, word.length() - 2) + "e";
            }
            // If the word length is greater than or equal to 6 and the word ends with "ic", remove "ic" from the end
            if (word.length() >= 6 && word.endsWith("ic")) {
                return word.substring(0, word.length() - 2);
            }
            // Return the original word if it doesn't match any of the rules
            return word;
        }

        /**
         * Stems words ending with "ing" based on specified rules.
         *
         * @param word The input word to be stemmed.
         * @return The stemmed word.
         */
        public String stemIng(String word) {

            if (word.length() >= 5 && (word.endsWith("nning") || word.endsWith("mming") || word.endsWith("pping") || word.endsWith("gging"))) {
                return word.substring(0, word.length() - 4);
            }

            // Add a custom rule for words ending in "ting"
            if (word.length() >= 6 && word.endsWith("ting")) {
                return word.substring(0, word.length() - 3) + "e";
            }

            // If the word length is greater than or equal to 5 and the word ends with "ling" or "gging", replace "ling" with "le"
            if (word.length() >= 5 && (word.endsWith("ling") || word.endsWith("gging"))) {
                return word.substring(0, word.length() - 3) + "e";
            }
            // If the word length is greater than or equal to 6 and the word ends with "izing", replace "izing" with "ize"
            if (word.length() >= 6 && word.endsWith("izing")) {
                return word.substring(0, word.length() - 3) + "e";
            }

            // If the word length is greater than or equal to 5 and the word ends with "ating", replace "ating" with "ate"
            if (word.length() >= 5 && word.endsWith("ating")) {
                return word.substring(0, word.length() - 3) + "e";
            }

            // If the word length is greater than or equal to 3 and the word ends with "ing", remove "ing" from the end
            if (word.length() >= 3 && word.endsWith("ing")) {
                return word.substring(0, word.length() - 3);
            }

            // If the word length is greater than or equal to 4 and the word ends with "ying", replace "ying" with "y"
            if (word.length() >= 4 && word.endsWith("ying")) {
                return word.substring(0, word.length() - 3) + "y";
            }

            // Return the original word if it doesn't match any of the rules
            return word;
        }

        public String stemS(String word) {

            if (word.endsWith("ss")) {
                return word = word.substring(0, word.length());
            }
            if (word.length() >= 3 && word.endsWith("s")) {
                return word = word.substring(0, word.length() - 1);
            }

            return word;
        }

    }
}
