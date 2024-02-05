
// importing required java packages

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class stemmingApplication {
    public static void main(String args[]) {
        // creating object of  JFrame, JButton, JTextField objects
        
        JButton button_icon = new JButton("Click Here To Process");
        JFrame frame_view = new JFrame("Stemming word Application");
        JTextArea inputArea,outputArea;
        JLabel label1,label2;
        label1 = new JLabel("Enter your text:");
        // setting the bound for label
        label1.setBounds(400,70,200,30);

        label2=new JLabel("Result:Stemmed text: ");
        label2.setBounds(400,250, 200,30);
        // setting the bound for button
        button_icon.setBounds(400,210,100, 30);

        inputArea=new JTextArea();
        // setting the bound for inputArea
        inputArea.setBounds(350,100, 300,100);

        outputArea=new JTextArea();
        // setting the bound for outputArea
        outputArea.setBounds(350,280, 300,100);

        button_icon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get text from inputArea
                String inputText = inputArea.getText();
                String[] splitText = inputText.split(" ");
                ArrayList<String> words_container = new ArrayList<>();
                for (String word : splitText) {
                    if (word.trim().length()>3){
                        words_container.add(word.trim());
                    }
                }
                // Initialize a hashmap
                Map<String, String> hashMap = new HashMap<>();
                // Create an instance of Stemmer_word class
                Stemmer stemmer = new Stemmer();
                
                for (String word : words_container) {
                    String stemmedWord = "";
                    if (word.endsWith("ed")) {
                        stemmedWord = stemmer.stemEd(word);
                    } else if (word.endsWith("ly")){
                        stemmedWord = stemmer.stemLy(word);
                    } else if (word.endsWith("ful")){
                        stemmedWord = stemmer.stemFul(word);
                    } else if (word.endsWith("est")){
                        stemmedWord = stemmer.stemEst(word);
                    } else if (word.endsWith("ity")){
                        stemmedWord = stemmer.stemIty(word);
                    } else if (word.endsWith("ant")){
                        stemmedWord = stemmer.stemIty(word);
                    } else if (word.endsWith("ness")){
                        stemmedWord = stemmer.stemNess(word);
                    } else if (word.endsWith("es")){
                        stemmedWord = stemmer.stemEs(word);
                    } else if (word.endsWith("ic")){
                        stemmedWord = stemmer.stemIc(word);
                    } else if (word.endsWith("er")){
                        stemmedWord = stemmer.stemEr(word);
                    } else if (word.endsWith("ing")){
                        stemmedWord = stemmer.stemIng(word);
                    } else if (word.endsWith("s")){
                        stemmedWord = stemmer.stemS(word);
                    } else {
                        continue;
                    }

                    if (stemmedWord.equals(word)){
                        continue;
                    } else{
                        hashMap.put(word, stemmedWord);
                    }
                }

                String processedText = "";
                for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                    processedText += entry.getKey() +": "+entry.getValue()+"\n";
                }
                outputArea.setText(processedText); // Output processedtext
            }
        });

        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        // Add elements to frame_view
        frame_view.add(inputArea); frame_view.add(outputArea); frame_view.add(button_icon);
        frame_view.add(label1); frame_view.add(label2);

        frame_view.setSize(800,800);                    // setting the Frame_view  size
        frame_view.setLayout(null);                          // setting the  layout style
        frame_view.setVisible(true);
        frame_view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}

// helper java class to stem the words (reference animesh code)
class Stemmer {


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

    
    public String stemLy(String word) {
        // word length >= 5, apply the rules
        if (word.length() >= 5) {
            // If the word ends with aly, remove y from the end
            if (word.endsWith("aly")) {
                return word.substring(0, word.length() - 1);
            }

            // word ends with ily, remove ily from the end and add y
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


    public String stemEd(String word) {
        if (word.length() >= 6 && word.endsWith("ceed")) {
            return word.substring(0, word.length() - 3) + "ess";
        }
        //word length >= 6 and the word ends with eed, remove d from last
        if (word.length() >= 6 && word.endsWith("eed")) {
            return word.substring(0, word.length() - 1);
        }
        if (word.length() >= 6 && word.endsWith("ied")) {
            return word.substring(0, word.length() - 3) + "y";
        }
        if (word.length() >= 5 && word.endsWith("ered")) {
            return word.substring(0, word.length() - 2);
        }
        //word length >= 5 and the word ends with vowel + red or ted, remove d from last
        if (word.length() >= 5 && word.matches(".*[aeiou]red$|.*[aeiou]ted$|.*[aeiou]zed$")) {
            return word.substring(0, word.length() - 1);
        }
        //word length >= 6 and the word ends with ed, remove ed from last
        if (word.length() >= 6 && word.endsWith("ed")) {
            return word.substring(0, word.length() - 2);
        }
        return word;
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
        if (word.endsWith("ssing")){
            return word.substring(0, word.length() - 3);
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
        // If the word length is greater than or equal to 4 and the word ends with "ying", replace "ying" with "y"
        if (word.length() >= 4 && word.endsWith("ying")) {
            return word.substring(0, word.length() - 4) + "y";
        }
        if (word.endsWith("nting")){
            return word.substring(0, word.length() - 3);
        }
        // If the word length is greater than or equal to 3 and the word ends with "ing", remove "ing" from the end
        if (word.length() >= 3 && word.endsWith("ing")) {
            return word.substring(0, word.length() - 3) + "e";
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