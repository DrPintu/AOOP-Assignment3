// Author : Anmol Kumar
// Date : 06 Feb 2024

import java.util.Scanner;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Word_Stemmer {
    public static void main(String[] args) {
        JFrame frame = new JFrame("The Stemming Interface");
        JButton button = new JButton("Click to Stem");
        JLabel label1, label2;

        label1=new JLabel("Text for Stem:");
        label1.setBounds(60,70, 120,40);

        label2=new JLabel("After Stemming: ");
        label2.setBounds(65,460, 120,40);

        button.setBounds(300,420,350, 60);

        JTextArea inputArea = new JTextArea();
        inputArea.setBounds(50,100, 1200,300);
        
        JTextArea outputArea = new JTextArea();
        outputArea.setBounds(50,500, 1200,250);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String inputText = inputArea.getText().toLowerCase();
    
                String[] splitText = inputText.split("\\s+");
                ArrayList<String> wordsList = new ArrayList<>();


                for (String word : splitText) {
                    if (word.trim().length() >= 3) {
                        wordsList.add(word.trim());
                    }
                }

                HashMap<String, String> wordhashMap = new HashMap<>();
     
                Stemmer_word stemmer = new Stemmer_word();
                
                for (String word : wordsList) {
                    String NewWord = "";
                    if (word.endsWith("ed")) {
                        NewWord = stemmer.stemEd(word);
                    } else if (word.endsWith("ly")){
                        NewWord = stemmer.stemLy(word);
                    } else if (word.endsWith("ful")){
                        NewWord = stemmer.stemFul(word);
                    } else if (word.endsWith("est")){
                        NewWord = stemmer.stemEst(word);
                    } else if (word.endsWith("ity")){
                        NewWord = stemmer.stemIty(word);
                    } else if (word.endsWith("ant")){
                        NewWord = stemmer.stemIty(word);
                    } else if (word.endsWith("ness")){
                        NewWord = stemmer.stemNess(word);
                    } else if (word.endsWith("es")){
                        NewWord = stemmer.stemEs(word);
                    } else if (word.endsWith("ic")){
                        NewWord = stemmer.stemIc(word);
                    } else if (word.endsWith("er")){
                        NewWord = stemmer.stemEr(word);
                    } else if (word.endsWith("ing")){
                        NewWord = stemmer.stemIng(word);
                    } else if (word.endsWith("s")){
                        NewWord = stemmer.stemS(word);
                    } 
                    if (NewWord.equals("")){
                        wordhashMap.put(word, word);
                    } else{
                        wordhashMap.put(word, NewWord);
                    }
                }
                String Stemmed_Text = "";
                for (HashMap.Entry<String, String> entry : wordhashMap.entrySet()) {
                    Stemmed_Text += "Original word --> " + entry.getKey()+"\n"+"Stemmed Word --> "+entry.getValue()+"\n";
                }
                outputArea.setText(Stemmed_Text); 
            }
        });

        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        frame.add(inputArea); 
        frame.add(outputArea); 
        frame.add(button);
        frame.add(label1); 
        frame.add(label2);

        frame.setSize(1200,1000);                   
        frame.setLayout(null);                       
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class Stemmer_word {

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
                }
                else if (word.endsWith("nity")) {
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
        if (word.endsWith("iness")){
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
            return word.substring(0, word.length() - 2 )+ "e";
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

        // If the word length is greater than or equal to 5 and the word ends with "ling" or "gging", replace "ling" with "le"
        if (word.length() >= 5 && word.endsWith("gging")) {
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
        if (word.endsWith("ing")) {
            if (word.length() >= 3 && word.endsWith("iting") || word.endsWith("uding") || word.endsWith("ving") || word.endsWith("sing")){
                return word.substring(0, word.length() - 3) + "e";
            }
            if ( word.endsWith("ling")){
                return word.substring(0, word.length() - 3);

            }
            return word.substring(0, word.length() - 3);
        }

        // If the word length is greater than or equal to 4 and the word ends with "ying", replace "ying" with "y"
        if (word.length() >= 4 && word.endsWith("ying")) {
            return word.substring(0, word.length() - 3) + "y";
        }

        // Return the original word if it doesn't match any of the rules
        return word;
    }

public String stemS(String word){

        if (word.endsWith("ss")){
            return word = word.substring(0, word.length());
        }
        if (word.length()>= 3 && word.endsWith("s")){
            return word = word.substring(0, word.length()-1);
        }
        return word;
    }

}

