/*
Problem statement: 
Given a text paragraph or any paragraph taken by text file reading in Java, you have to find inflected words
and print their root forms. You need to create one text area for showing (or entering) 
the paragraph. When you click on a certain button, another text area will show the inflected words along with
their roots. You are not allowed to use any given libraries for this task.
 */

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

public class Main extends Frame implements ActionListener {
    JTextArea tf,tf2;
    JButton b;
    JScrollPane j1,j2;
    Main(String s) throws IOException {
        
        tf = new JTextArea();
        tf.setBounds(150, 100, 1300, 300);

        tf2 = new JTextArea();
        tf2.setBounds(150, 500, 1300, 300);
        
        j1 = new JScrollPane(tf, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        j2 = new JScrollPane(tf2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        j1.setBounds(150, 100, 1300, 300);
        j2.setBounds(150, 500, 1300, 300);
        b = new JButton("Stem Inflected words");
        b.setBounds(270, 425, 1000, 50);
        b.addActionListener(this);
        add(b);
        add(j1);
        add(j2);
        setSize(1800, 1500);
        setLayout(null);
        setVisible(true);
        File myObj = new File(s);
        Scanner myReader = new Scanner(myObj);
        while(myReader.hasNextLine()){
            String pargraph = myReader.nextLine();
            tf.append(pargraph + "\n");
            
        }
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Stemmer_word stemmer = new Stemmer_word();
            String paragraph = tf.getText();
            String cleanedParagraph = paragraph.replaceAll("[^a-zA-Z0-9\\s]", " ");
            String[] words = cleanedParagraph.split(" ");
            for (String word : words) {
                String s = stemmer.stemWord(word);
                if(s.length()!=0){
                    tf2.append(word + "-->" + s +"\n");
                }
                
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public static void main(String[] args) throws IOException{
        new Main(args[0]);
    }
}

class Stemmer_word {
    public String stemWord(String word) {
        String [] stopWords = {"a", "an", "and", "the", "is", "in", "it", "on", "at", "to", "of", "for", "with", "by",
        "I", "you", "he", "she", "we", "they", "me", "him", "her", "us", "them", "your", "his", "its",
        "our", "their", "mine", "yours", "hers", "ours", "theirs", "that", "this", "these", "those",
        "but", "if", "or", "because", "as", "until", "while", "since", "so", "than", "unless", "although",
        "though", "even", "once", "after", "before", "when", "where", "how", "why", "what", "which",
        "who", "whom", "whose", "which", "such", "whose", "am", "are", "is", "was", "were", "be", "been",
        "being", "have", "has", "had", "do", "does", "did", "doing", "can", "could", "will", "would",
        "shall", "should","ever","dynamic","minister", "may", "might", "must", "ought", "a", "an", "the","as","As"};
        ArrayList stopWords_arr = new ArrayList<>();
        for(String i:stopWords){
            stopWords_arr.add(i);
        }
        if(stopWords_arr.contains(word.toLowerCase())){
            return "";
        }
        String suffix = "";
        // Use a series of if-else statements to check the word for the suffix and
        // assign the suffix variable accordingly
        if (word.endsWith("ed")) {
            suffix = "ed";
        } else if (word.endsWith("ly")) {
            suffix = "ly";
        } else if (word.endsWith("ful")) {
            suffix = "ful";
        } else if (word.endsWith("est")) {
            suffix = "est";
        } else if (word.endsWith("ity")) {
            suffix = "ity";
        } else if (word.endsWith("ant")) {
            suffix = "ant";
        } else if (word.endsWith("ness")) {
            suffix = "ness";
        } else if (word.endsWith("es")) {
            suffix = "es";
        } else if (word.endsWith("ic")) {
            suffix = "ic";
        } else if (word.endsWith("er")) {
            suffix = "er";
        } else if (word.endsWith("ing")) {
            suffix = "ing";
        } else if (word.endsWith("s")) {
            suffix = "s";
        }

        else {
            return "";
        }
        // Declare a variable to store the stemmed word
        String stemmedWord = "";
        // Use a switch statement to choose the appropriate method to call based on the
        // suffix
        switch (suffix) {
            case "ed":
                stemmedWord = stemEd(word);
                break;
            case "ly":
                stemmedWord = stemLy(word);
                break;
            case "ful":
                stemmedWord = stemFul(word);
                break;
            case "est":
                stemmedWord = stemEst(word);
                break;
            case "ity":
                stemmedWord = stemIty(word);
                break;

            case "ant":
                stemmedWord = stemIty(word);
                break;
            case "ness":
                stemmedWord = stemNess(word);
                break;
            case "es":
                stemmedWord = stemEs(word);
                break;

            case "ic":
                stemmedWord = stemIc(word);
                break;

            case "er":
                stemmedWord = stemEr(word);
                break;

            case "ing":
                stemmedWord = stemIng(word);
                break;
            case "s":
                stemmedWord = stemS(word);
                break;

            default:
                return "";
        }
        // Print the original word and the stemmed word
        if (word.equals(stemmedWord)) {
            return "";
        } else {
            return stemmedWord;
        }

    }

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
        if (word.length() >= 5 && word.matches(".*[aeiou]red$|.*[aeiou]ted$|.*[aeiou]zed$")) {
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
     * @param word The input word to be stemmed.
     * @return The stemmed word.
     */
    public String stemIng(String word) {
        // Prioritize more specific rules for greater accuracy
        if(word.endsWith("ying")){
            return word.substring(0, word.length()-3);
        }
        if (word.length() >= 5 && (word.endsWith("nning") || word.endsWith("mming") || word.endsWith("pping") || word.endsWith("gging"))) {
            return word.substring(0, word.length() - 4);
        }

        if (word.length() >= 6 && word.endsWith("izing")) {
            return word.substring(0, word.length() - 3) + "e";
        }

        if (word.length() >= 4 && (word.endsWith("ying") || word.endsWith("ingly"))) {
            return word.substring(0, word.length() - 3);
        }

        if ((word.endsWith("king") || word.endsWith("ding") || word.endsWith("ping") || word.endsWith("ning") || word.endsWith("ging") || word.endsWith("hing"))
                && word.length() - 3 > 3) {
            return word.substring(0, word.length() - 3);
        }

        if (word.length() >= 5 && (word.endsWith("ling") || word.endsWith("gling") || word.endsWith("ating"))) {
            return word.substring(0, word.length() - 3) + "e";
        }

        // Handle general "ing" cases
        if (word.length() >= 3 && word.endsWith("ing")) {
            return word.substring(0, word.length() - 3) + "e";
        }

        // Return the original word if no rules apply
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
