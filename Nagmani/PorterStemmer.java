// Required Module
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class PorterStemmer {
    public static void main(String[] args) {
        // Initializing a JFrame, JButton, JTextField objects
        JFrame frame = new JFrame("Stem your words");
        JButton button = new JButton("Process");
        JTextArea inputArea, outputArea;
        JLabel label1, label2;

        label1 = new JLabel("Enter text:");
        label1.setBounds(53, 70, 200, 30);

        label2 = new JLabel("Stemmed text:");
        label2.setBounds(53, 250, 200, 30);

        button.setBounds(250, 210, 100, 30);

        inputArea = new JTextArea();
        inputArea.setBounds(50, 100, 300, 100);

        outputArea = new JTextArea();
        outputArea.setBounds(50, 280, 300, 100);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get text from inputArea
                String inputText = inputArea.getText();
                // Split text into words
                String[] splitText = inputText.split("\\s+");
                ArrayList<String> wordsList = new ArrayList<>();

                // Filter out short words
                for (String word : splitText) {
                    if (word.trim().length() > 3) {
                        wordsList.add(word.trim());
                    }
                }

                // Initialize a hashmap
                Map<String, String> hashMap = new HashMap<>();
                // Create an instance of StemmerWord class
                StemmerWord stemmer = new StemmerWord();

                // Apply stemming rules
                for (String word : wordsList) {
                    String stemmedWord = stemmer.stem(word);
                    if (!stemmedWord.equals(word)) {
                        hashMap.put(word, stemmedWord);
                    }
                }

                // Build processedText
                StringBuilder processedText = new StringBuilder();
                for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                    processedText.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }
                outputArea.setText(processedText.toString()); // Output processedtext
            }
        });

        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        // Add elements to frame
        frame.add(inputArea);
        frame.add(outputArea);
        frame.add(button);
        frame.add(label1);
        frame.add(label2);

        frame.setSize(1000, 1000); // Frame size
        frame.setLayout(null); // layout style
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Auto Exit
    }
}

// Helper class to stem the words
class StemmerWord {
    public String stem(String word) {
        if (word.endsWith("ed")) {
            return stemEd(word);
        } else if (word.endsWith("ly")) {
            return stemLy(word);
        } else if (word.endsWith("ful")) {
            return stemFul(word);
        } else if (word.endsWith("est")) {
            return stemEst(word);
        } else if (word.endsWith("ity")) {
            return stemIty(word);
        } else if (word.endsWith("ant")) {
            return stemAnt(word);
        } else if (word.endsWith("ness")) {
            return stemNess(word);
        } else if (word.endsWith("es")) {
            return stemEs(word);
        } else if (word.endsWith("ic")) {
            return stemIc(word);
        } else if (word.endsWith("er")) {
            return stemEr(word);
        } else if (word.endsWith("ing")) {
            return stemIng(word);
        } else if (word.endsWith("s")) {
            return stemS(word);
        } else {
            return word;
        }
    }

    private String stemEd(String word) {
        // Rule: If the word ends with "ceed", replace "ceed" with "ess"
        if (word.length() >= 6 && word.endsWith("ceed")) {
            return word.substring(0, word.length() - 3) + "ess";
        }
        // Rule: If the word ends with "eed", remove "d" from the end
        if (word.length() >= 6 && word.endsWith("eed")) {
            return word.substring(0, word.length() - 1);
        }
        // Rule: If the word ends with "ied", replace "ied" with "y"
        if (word.length() >= 6 && word.endsWith("ied")) {
            return word.substring(0, word.length() - 3) + "y";
        }
        // Rule: If the word ends with "ered", remove "ed" from the end
        if (word.length() >= 5 && word.endsWith("ered")) {
            return word.substring(0, word.length() - 2);
        }
        // Rule: If the word ends with a vowel + "red" or "ted" or "zed", remove "d"
        // from the end
        if (word.length() >= 5 && word.matches(".*[aeiou]red$|.*[aeiou]ted$|.*[aeiou]zed$")) {
            return word.substring(0, word.length() - 1);
        }
        // Rule: If the word ends with "ed", remove "ed" from the end
        if (word.length() >= 6 && word.endsWith("ed")) {
            return word.substring(0, word.length() - 2);
        }
        return word;
    }

    private String stemLy(String word) {
        // Rule: If the word length is greater than or equal to 5, apply the rules
        if (word.length() >= 5) {
            // Rule: If the word ends with "aly", remove "y" from the end
            if (word.endsWith("aly")) {
                return word.substring(0, word.length() - 1);
            }
            // Rule: If the word ends with "ily", remove "ily" from the end and add "y"
            if (word.endsWith("ily")) {
                return word.substring(0, word.length() - 3) + "y";
            }
            // Rule: If the word ends with "ly", remove "ly" from the end
            if (word.endsWith("ly")) {
                return word.substring(0, word.length() - 2);
            }
        }
        return word;
    }

    private String stemFul(String word) {
        // Rule: If the word length is greater than or equal to 6 and the word ends with
        // "iful", replace "iful" with "y"
        if (word.length() >= 6 && word.endsWith("iful")) {
            return word.substring(0, word.length() - 4) + "y";
        }
        // Rule: If the word length is greater than or equal to 6 and the word ends with
        // "ful", remove "ful" from the end
        if (word.length() >= 6 && word.endsWith("ful")) {
            return word.substring(0, word.length() - 3);
        }
        return word;
    }

    private String stemEst(String word) {
        // Rule: If the word length is greater than or equal to 6, apply the rules
        if (word.length() >= 6) {
            // Rule: If the word ends with "iest", replace "iest" with "y"
            if (word.endsWith("iest")) {
                return word.substring(0, word.length() - 4) + "y";
            }
            // Rule: If the word ends with "llest", remove "llest" from the end
            if (word.endsWith("llest")) {
                return word.substring(0, word.length() - 4);
            }
            // Rule: If the word ends with "est", remove "est" from the end
            if (word.endsWith("est")) {
                return word.substring(0, word.length() - 3);
            }
        }
        return word;
    }

    private String stemIty(String word) {
        // Rule: If the word length is greater than or equal to 7, apply the rules
        if (word.length() >= 7) {
            // Rule: If the word ends with "ility", replace "ility" with "le"
            if (word.endsWith("ility")) {
                return word.substring(0, word.length() - 5) + "le";
            }
            // Rule: If the word ends with "vity" or "nity", remove "ity" from the end and
            // add "e"
            if (word.endsWith("vity") || word.endsWith("nity")) {
                return word.substring(0, word.length() - 3) + "e";
            }
            // Rule: If the word ends with "ity", remove "ity" from the end
            if (word.endsWith("ity")) {
                return word.substring(0, word.length() - 3);
            }
        }
        return word;
    }

    private String stemAnt(String word) {
        // Rule: If the word length is greater than or equal to 5 and the word ends with
        // "uant", replace "uant" with "y"
        if (word.length() >= 5 && word.endsWith("uant")) {
            return word.substring(0, word.length() - 4) + "y";
        }
        // Rule: If the word length is greater than or equal to 4 and the word ends with
        // "ant", remove "ant" from the end
        if (word.length() >= 4 && word.endsWith("ant")) {
            return word.substring(0, word.length() - 3);
        }
        return word;
    }

    private String stemNess(String word) {
        // Rule: If the word length is greater than or equal to 5 and the word ends with
        // "iness", replace "iness" with "y"
        if (word.length() >= 5 && word.endsWith("iness")) {
            return word.substring(0, word.length() - 5) + "y";
        }
        // Rule: If the word length is greater than or equal to 4 and the word ends with
        // "ness", remove "ness" from the end
        if (word.length() >= 4 && word.endsWith("ness")) {
            return word.substring(0, word.length() - 4);
        }
        return word;
    }

    private String stemEs(String word) {
        // Rule: If the word ends with "ies" and the length is greater than or equal to
        // 8, replace "ies" with "y"
        if (word.endsWith("ies") && word.length() >= 8) {
            return word.substring(0, word.length() - 3) + "y";
        }
        // Rule: If the word matches the pattern ".*[aeiou][^aeiou]es$", remove "es"
        // from the end
        if (word.matches(".*[aeiou][^aeiou]es$")) {
            return word.substring(0, word.length() - 1);
        }
        // Rule: If the word ends with "ves", replace "ves" with "f"
        if (word.endsWith("ves")) {
            return word.substring(0, word.length() - 3) + "f";
        }
        // Rule: If the word ends with "es", remove "es" from the end
        if (word.endsWith("es")) {
            return word.substring(0, word.length() - 2);
        }
        return word;
    }

    private String stemEr(String word) {
        // Rule: If the word length is greater than or equal to 5 and ends with "ier",
        // replace "ier" with "y"
        if (word.length() >= 5 && word.endsWith("ier")) {
            return word.substring(0, word.length() - 3) + "y";
        }
        // Rule: If the word length is greater than or equal to 7 and ends with "izer",
        // remove "er" from the end
        if (word.length() >= 7 && word.endsWith("izer")) {
            return word.substring(0, word.length() - 1);
        }
        // Rule: If the word length is greater than or equal to 4 and ends with "er",
        // remove "er" from the end
        if (word.length() >= 4 && word.endsWith("er")) {
            return word.substring(0, word.length() - 2);
        }
        return word;
    }

    private String stemIc(String word) {
        // Rule: If the word length is greater than or equal to 6 and ends with "gic" or
        // "mic", replace "ic" with "y"
        if (word.length() >= 6 && (word.endsWith("gic") || word.endsWith("mic"))) {
            return word.substring(0, word.length() - 2) + "y";
        }
        // Rule: If the word length is greater than or equal to 5 and ends with "atic",
        // replace "atic" with "e"
        if (word.length() >= 5 && word.endsWith("atic")) {
            return word.substring(0, word.length() - 2) + "e";
        }
        // Rule: If the word length is greater than or equal to 6 and ends with "ic",
        // remove "ic" from the end
        if (word.length() >= 6 && word.endsWith("ic")) {
            return word.substring(0, word.length() - 2);
        }
        return word;
    }

    private String stemIng(String word) {
        // Rule: If the word length is greater than or equal to 5 and ends with "nning",
        // "mming", "pping", or "gging", remove "ing" from the end
        if (word.length() >= 5 && (word.endsWith("nning") || word.endsWith("mming") || word.endsWith("pping")
                || word.endsWith("gging"))) {
            return word.substring(0, word.length() - 4);
        }
        // Rule: If the word ends with "ssing", remove "ing" from the end
        if (word.endsWith("ssing")) {
            return word.substring(0, word.length() - 3);
        }
        // Rule: If the word length is greater than or equal to 5 and ends with "ling"
        // or "gging", replace "ing" with "e"
        if (word.length() >= 5 && (word.endsWith("ling") || word.endsWith("gging"))) {
            return word.substring(0, word.length() - 3) + "e";
        }
        // Rule: If the word length is greater than or equal to 6 and ends with "izing",
        // "ating", or "ying", replace "ing" with "e"
        if (word.length() >= 6 && (word.endsWith("izing") || word.endsWith("ating") || word.endsWith("ying"))) {
            return word.substring(0, word.length() - 3) + "e";
        }
        // Rule: If the word length is greater than or equal to 4 and ends with "ying",
        // replace "ying" with "y"
        if (word.length() >= 4 && word.endsWith("ying")) {
            return word.substring(0, word.length() - 4) + "y";
        }
        // Rule: If the word ends with "nting", remove "ing" from the end
        if (word.endsWith("nting")) {
            return word.substring(0, word.length() - 3);
        }
        // Rule: If the word length is greater than or equal to 3 and ends with "ing",
        // replace "ing" with "e"
        if (word.length() >= 3 && word.endsWith("ing")) {
            return word.substring(0, word.length() - 3) + "e";
        }
        return word;
    }

    private String stemS(String word) {
        // Rule: If the word ends with "ss", keep the word as it is
        if (word.endsWith("ss")) {
            return word.substring(0, word.length());
        }
        // Rule: If the word length is greater than or equal to 3 and ends with "s",
        // remove "s" from the end
        if (word.length() >= 3 && word.endsWith("s")) {
            return word.substring(0, word.length() - 1);
        }
        return word;
    }

}
