import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stemmingword {
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton stemButton;

    public Stemmingword() {
        JFrame frame = new JFrame("Stemming Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        inputTextArea = new JTextArea("Type something here or paste texts of a file. You can simply type a paragraph or the contents of the file");
        outputTextArea = new JTextArea("In this text area, you have to print the the inflected words (i.e) and their root forms");
        stemButton = new JButton("Stemming inflected word");

        stemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputTextArea.getText();
                String outputText = processText(inputText);
                outputTextArea.setText(outputText);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JScrollPane(inputTextArea));
        panel.add(stemButton);
        panel.add(new JScrollPane(outputTextArea));

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);
    }

    private String processText(String inputText) {
        StringBuilder result = new StringBuilder();
        String[] words = inputText.split("\\s+");
    
        Stemmer_word stemmer = new Stemmer_word();
    
        for (String word : words) {
            if (!(word.equalsIgnoreCase("this") || word.equalsIgnoreCase("has") || word.equalsIgnoreCase("that"))) {
                String root = stemmer.stemWord(word);
                if (!root.equals(word)) {
                    result.append(word).append(" --> ").append(root).append("\n");
                }
            } else {
                result.append(word).append(" ");
            }
        }
    
        return result.toString();
    }
    
    
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Stemmingword();
            }
        });
    }
}

class Stemmer_word {
    public String stemWord(String word) {
        String suffix = findSuffix(word);

        if (suffix.equals("")) {
            return word;
        }

        switch (suffix) {
            case "ed":
                return stemEd(word);
            case "ly":
                return stemLy(word);
            case "ful":
                return stemFul(word);
            case "est":
                return stemEst(word);
            case "ity":
                return stemIty(word);
            case "ant":
                return stemIty(word);
            case "ness":
                return stemNess(word);
            case "es":
                return stemEs(word);
            case "ic":
                return stemIc(word);
            case "er":
                return stemEr(word);
            case "ing":
                return stemIng(word);
            case "s":
                return stemS(word);
            case "'s":
                return stemS(word);
            default:
                return word;
        }
    }

    private String findSuffix(String word) {
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
        } else if (word.endsWith("'s")) {
            return "'s";    
        } else {
            return "";
        }
    }

    public String stemEd(String word) {
        if (word.length() >= 6 && word.endsWith("ceed")) {
            return word.substring(0, word.length() - 3) + "ess";
        }
        if (word.length() >= 6 && word.endsWith("eed")) {
            return word.substring(0, word.length() - 1);
        }

        if (word.length() >= 6 && word.endsWith("ied")) {
            return word.substring(0, word.length() - 3) + "y";
        }

        if (word.length() >= 5 && word.endsWith("ered")) {
            return word.substring(0, word.length() - 2);
        }

        if (word.length() >= 5 && word.matches(".[aeiou]red$|.[aeiou]ted$|.*[aeiou]zed$")) {
            return word.substring(0, word.length() - 1);
        }

        if (word.length() >= 6 && word.endsWith("ed")) {
            return word.substring(0, word.length() - 2);
        }

        return word;
    }

    public String stemLy(String word) {
        if (word.length() >= 5) {
            if (word.endsWith("aly")) {
                return word.substring(0, word.length() - 1);
            }

            if (word.endsWith("ily")) {
                return word.substring(0, word.length() - 3) + "y";
            }

            if (word.endsWith("ly")) {
                return word.substring(0, word.length() - 2);
            }
        }

        return word;
    }

    public String stemFul(String word) {
        if (word.length() >= 6 && word.endsWith("iful")) {
            return word.substring(0, word.length() - 4) + "y";
        }

        if (word.length() >= 6 && word.endsWith("ful")) {
            return word.substring(0, word.length() - 3);
        }

        return word;
    }

    public String stemEst(String word) {
        if (word.length() >= 6) {
            if (word.endsWith("iest")) {
                return word.substring(0, word.length() - 4) + "y";
            }

            if (word.endsWith("llest")) {
                return word.substring(0, word.length() - 4);
            }

            if (word.endsWith("est")) {
                return word.substring(0, word.length() - 3);
            }
        }
        return word;
    }

    public String stemIty(String word) {
        if (word.length() >= 7) {
            if (word.endsWith("ity")) {
                if (word.endsWith("ility")) {
                    return word.substring(0, word.length() - 5) + "le";
                } else if (word.endsWith("vity")) {
                    return word.substring(0, word.length() - 3) + "e";
                } else if (word.endsWith("nity")) {
                    return word.substring(0, word.length() - 3) + "e";
                } else {
                    return word.substring(0, word.length() - 3);
                }
            } else {
                return word;
            }
        } else {
            return word;
        }
    }

    public String stemNess(String word) {
        if (word.length() >= 5) {
            if (word.endsWith("iness")){
                word = word.substring(0, word.length() - 5) + "y";
            } else if (word.endsWith("ness")) {
                word = word.substring(0, word.length() - 4);
            }
        }
        return word;
    }

    public String stemEs(String word) {
        if (word.endsWith("es")) {
            if (word.endsWith("ies")) {
                if (word.length() >= 8) {
                    return word.substring(0, word.length() - 3) + "y";
                } else {
                    return word;
                }
            } else if (word.matches(".*[aeiou][^aeiou]es$")) {
                return word.substring(0, word.length() - 1);
            } else if (word.endsWith("ves")) {
                return word.substring(0, word.length() - 3) + "f";
            } else {
                return word.substring(0, word.length() - 2);
            }
        } else {
            return word;
        }
    }

    public String stemEr(String word) {
        if (word.length() >= 5 && word.endsWith("ier")) {
            return word.substring(0, word.length() - 3) + "y";
        }

        if (word.length() >= 7 && word.endsWith("izer")) {
            return word.substring(0, word.length() - 1);
        }

        if (word.length() >= 4 && word.endsWith("er")) {
            return word.substring(0, word.length() - 2);
        }

        return word;
    }

    public String stemIc(String word) {
        if (word.length() >= 6 && (word.endsWith("gic") || word.endsWith("mic"))) {
            return word.substring(0, word.length() - 2) + "y";
        }

        if (word.length() >= 5 && word.endsWith("atic")) {
            return word.substring(0, word.length() - 2) + "e";
        }

        if (word.length() >= 6 && word.endsWith("ic")) {
            return word.substring(0, word.length() - 2);
        }

        return word;
    }

    public String stemIng(String word) {
        if (word.length() >= 5 && (word.endsWith("nning") || word.endsWith("mming") || word.endsWith("pping") || word.endsWith("gging"))) {
            return word.substring(0, word.length() - 4);
        }

        if (word.length() >= 5 && (word.endsWith("ling") || word.endsWith("gging"))) {
            return word.substring(0, word.length() - 3) + "e";
        }

        if (word.length() >= 6 && word.endsWith("izing")) {
            return word.substring(0, word.length() - 3) + "e";
        }

        if (word.length() >= 5 && word.endsWith("ating")) {
            return word.substring(0, word.length() - 3) + "e";
        }

        if (word.length() >= 3 && word.endsWith("ing")) {
            return word.substring(0, word.length() - 3);
        }

        if (word.length() >= 4 && word.endsWith("ying")) {
            return word.substring(0, word.length() - 3) + "y";
        }

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
