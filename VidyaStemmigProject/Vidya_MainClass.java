import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Vidya_MainClass {
    public static void main(String[] args) {
        JFrame frame = new JFrame("WORD STEMMER");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel Label1 = new JLabel("ENTER YOUR TEXT ");
        Label1.setBounds(10, 20, 120, 25);
        frame.add(Label1);

        JTextArea Label1Area = new JTextArea();
        Label1Area.setBounds(10, 50, 760, 100);
        frame.add(Label1Area);

        JButton button = new JButton("PROCESS");
        button.setBounds(150, 170, 100, 25);
        frame.add(button);

        JLabel Label2 = new JLabel("STEMMED TEXT");
        Label2.setBounds(10, 200, 120, 25);
        frame.add(Label2);

        JTextArea Label2Area = new JTextArea();
        Label2Area.setBounds(10, 230, 760, 300);
        frame.add(Label2Area);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = Label1Area.getText();
                inputText = inputText.replaceAll("[\\p{Punct}&&[^']]", "");
                String[] words = inputText.split("\\s+");
                Map<String, String> stemmedWords = new HashMap<>();

                Stemmer_word stemmer = new Stemmer_word();

                for (String word : words) {
                    String stemmedWord = stemWord(word, stemmer);
                    if (!word.equals(stemmedWord)) {
                        stemmedWords.put(word, stemmedWord);
                    }
                }

                StringBuilder outputText = new StringBuilder();
                for (Map.Entry<String, String> entry : stemmedWords.entrySet()) {
                    outputText.append(entry.getKey()).append(" --> ").append(entry.getValue()).append("\n");
                }
                Label2Area.setText(outputText.toString());
            }

            private String stemWord(String word, Stemmer_word stemmer) {
                String suffix = determineSuffix(word);
                String stemmedWord = word;

                switch (suffix) {
                    case "ed":
                        stemmedWord = stemmer.stemEd(word);
                        break;
                    case "ly":
                        stemmedWord = stemmer.stemLy(word);
                        break;
                    case "ful":
                        stemmedWord = stemmer.stemFul(word);
                        break;
                    case "est":
                        stemmedWord = stemmer.stemEst(word);
                        break;
                    case "ity":
                        stemmedWord = stemmer.stemIty(word);
                        break;
                    case "ant":
                        stemmedWord = stemmer.stemIty(word); // Assuming same as "ity", update if needed
                        break;
                    case "ness":
                        stemmedWord = stemmer.stemNess(word);
                        break;
                    case "es":
                        stemmedWord = stemmer.stemEs(word);
                        break;
                    case "ic":
                        stemmedWord = stemmer.stemIc(word);
                        break;
                    case "er":
                        stemmedWord = stemmer.stemEr(word);
                        break;
                    case "ing":
                        stemmedWord = stemmer.stemIng(word);
                        break;
                    case "s":
                        stemmedWord = stemmer.stemS(word);
                        break;
                    default:
                        break;
                }

                return stemmedWord;
            }

            private String determineSuffix(String word) {
                if (word.endsWith("ed")) {
                    return "ed";
                } else if (word.endsWith("ly")) {
                    return "ly";
                } else if (word.endsWith("ful")) {
                    return "ful";
                } else if (word.endsWith("est")) {
                    return "est";
                } else if (word.endsWith("ity")) {
                    return "ity";
                } else if (word.endsWith("ant")) {
                    return "ant";
                } else if (word.endsWith("ness")) {
                    return "ness";
                } else if (word.endsWith("es")) {
                    return "es";
                } else if (word.endsWith("ic")) {
                    return "ic";
                } else if (word.endsWith("er")) {
                    return "er";
                } else if (word.endsWith("ing")) {
                    return "ing";
                } else if (word.endsWith("s")) {
                    return "s";
                } else {
                    return "";
                }
            }
        });

        frame.setLayout(null);
        frame.setVisible(true);
    }
}

class Stemmer_word {
    public String stemEd(String word) {
        if (word.length() >= 6 && word.endsWith("ceed")) {
            return word.substring(0, word.length() - 3) + "ess";
        }
        // If word length is greater than or equal to 6 and the word ends with eed,
        // remove d from last
        if (word.length() >= 6 && word.endsWith("eed")) {
            return word.substring(0, word.length() - 1);
        }

        if (word.length() >= 6 && word.endsWith("ied")) {
            return word.substring(0, word.length() - 3) + "y";
        }

        if (word.length() >= 5 && word.endsWith("ered")) {
            return word.substring(0, word.length() - 2);
        }

        // If word length is greater than or equal to 5 and the word ends with vowel +
        // red or ted, remove d from last
        if (word.length() >= 5 && word.matches(".[aeiou]red$|.[aeiou]ted$|.*[aeiou]zed$")) {
            return word.substring(0, word.length() - 1);
        }

        // If word length is greater than or equal to 6 and the word ends with ed,
        // remove ed from last
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
                return word.substring(0, word.length() - 3) + "y";
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

        // If the word length is greater than or equal to 5 and the word ends with
        // "ier", replace "ier" with "y"
        if (word.length() >= 5 && word.endsWith("ier")) {
            return word.substring(0, word.length() - 3) + "y";
        }

        // If the word length is greater than or equal to 7 and the word ends with
        // "izer", remove "er" from the end
        if (word.length() >= 7 && word.endsWith("izer")) {
            return word.substring(0, word.length() - 1);
        }
        // If the word length is greater than or equal to 4 and the word ends with "er",
        // remove "er" from the end
        if (word.length() >= 4 && word.endsWith("er")) {
            return word.substring(0, word.length() - 2);
        }
        // Return the original word if it doesn't match any of the rules
        return word;
    }

    public String stemIc(String word) {
        // If the word length is greater than or equal to 6 and the word ends with "gic"
        // or "mic", replace "ic" with "y"
        if (word.length() >= 6 && (word.endsWith("gic") || word.endsWith("mic"))) {
            return word.substring(0, word.length() - 2) + "y";
        }

        // If the word length is greater than or equal to 5 and the word ends with
        // "atic", remove "ic" from the end
        if (word.length() >= 5 && word.endsWith("atic")) {
            return word.substring(0, word.length() - 2) + "e";
        }
        // If the word length is greater than or equal to 6 and the word ends with "ic",
        // remove "ic" from the end
        if (word.length() >= 6 && word.endsWith("ic")) {
            return word.substring(0, word.length() - 2);
        }
        // Return the original word if it doesn't match any of the rules
        return word;
    }

    /**
     * Stems words ending with "ing" based on specified rules.
     * 
     * The input word to be stemmed.
     * The stemmed word.
     */
    public String stemIng(String word) {

        if (word.length() >= 5 && (word.endsWith("nning") || word.endsWith("mming") || word.endsWith("pping")
                || word.endsWith("gging"))) {
            return word.substring(0, word.length() - 4);
        }

        // If the word length is greater than or equal to 5 and the word ends with
        // "ling" or "gging", replace "ling" with "le"
        if (word.length() >= 5 && (word.endsWith("ling") || word.endsWith("ving"))) {
            return word.substring(0, word.length() - 3) + "e";
        }
        // If the word length is greater than or equal to 6 and the word ends with
        // "izing", replace "izing" with "ize"
        if (word.length() >= 6 && word.endsWith("izing")) {
            return word.substring(0, word.length() - 3) + "e";
        }

        // If the word length is greater than or equal to 5 and the word ends with
        // "ating", replace "ating" with "ate"
        if (word.length() >= 5 && word.endsWith("ating")) {
            return word.substring(0, word.length() - 3) + "e";
        }

        // If the word length is greater than or equal to 3 and the word ends with
        // "ing", remove "ing" from the end
        if (word.length() >= 3 && word.endsWith("ing")) {
            return word.substring(0, word.length() - 3);
        }
        if (word.length() >= 3 && word.endsWith("ding")) {
            return word.substring(0, word.length() - 3) + "e";
        }
        if (word.length() >= 3 && word.endsWith("ving")) {
            return word.substring(0, word.length() - 3) + "e";
        }

        // If the word length is greater than or equal to 4 and the word ends with
        // "ying", replace "ying" with "y"
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