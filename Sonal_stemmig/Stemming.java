import java.util.*;
import java.util.Hashtable;

public class Stemming {
    public Map<String, String> wordCollection(String[] collection) {

        Map<String, String> dict= new Hashtable<String, String>();

        Stemmer_word stemmer = new Stemmer_word();
        for(String word : collection){
            String suffix = "";

            if (word.endsWith("ed")) {
                suffix = "ed";

            } else if (word.endsWith("ly")) {
                suffix = "ly";

            } else if (word.endsWith("ful")) {
                suffix = "ful";

            } else if (word.endsWith("est")) {
                suffix = "est";

            } else if (word.endsWith("ity")){
                suffix  = "ity";

            } else if (word.endsWith("ant")){
                suffix  = "ant";

            } else if (word.endsWith("ness")){
                suffix  = "ness";

            } else if (word.endsWith("es")){
                suffix  = "es";

            } else if (word.endsWith("ic")){
                suffix  = "ic";

            } else if (word.endsWith("er")){
                suffix  = "er";

            } else if (word.endsWith("ing")){
                suffix  = "ing";

            } else if (word.endsWith("s")){
                suffix  = "s";

            }

            String stemmedWord = "";
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
                    stemmedWord = stemmer.stemIty(word);
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
                        continue;
            }
            dict.put(word, stemmedWord);
        }
        return dict;
    }
}

class Stemmer_word {
    public String stemEd(String word) {
        if (word.length() >= 6 && word.endsWith("eed")) {
            return word.substring(0, word.length() - 1);
        }

        if (word.length() >= 5 && word.endsWith("ied")) {
            return word.substring(0, word.length() - 3) + "y";
        }

        if (word.length() >= 6 && word.endsWith("ceed")) {
            return word.substring(0, word.length() - 3) + "ess";
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

            if (word.endsWith("ly")) {
                return word.substring(0, word.length() - 2);
            }
            
            if (word.endsWith("aly")) {
                return word.substring(0, word.length() - 1);
            }

            if (word.endsWith("ily")) {
                return word.substring(0, word.length() - 3) + "y";
            }

            
        }

        return word;
    }

    public String stemFul(String word) {
        if (word.length() >= 6 && word.endsWith("ful")) {
            return word.substring(0, word.length() - 3);
        }

        if (word.length() >= 6 && word.endsWith("iful")) {
            return word.substring(0, word.length() - 4) + "y";
        }

        return word;
    }

        public String stemEst(String word) {
        if (word.length() >= 6) {
            if (word.endsWith("llest")) {
                return word.substring(0, word.length() - 4);
            }

            if (word.endsWith("iest")) {
                return word.substring(0, word.length() - 4) + "y";
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
                }

                else if (word.endsWith("nity")) {
                    return word.substring(0, word.length() - 3) + "e";
                }

                else if (word.endsWith("vity")) {
                    return word.substring(0, word.length() - 3) + "e";
                }
                
                else {
                    return word.substring(0, word.length() - 3);
                }
            }

            else {
                return word;
            }
        }

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

    if (word.endsWith("es")) {

        if (word.endsWith("ies")) {

            if (word.length() >= 8) {

                return word.substring(0, word.length() - 3) + "y";
                }

                else {
                    return word;
                }
            }

            else if (word.matches(".*[aeiou][^aeiou]es$")) {

                return word.substring(0, word.length() - 1);
            }

            else if (word.endsWith("ves")) {

                return word.substring(0, word.length() - 3) + "f";
            }

            else {
                return word.substring(0, word.length() - 2);
            }
        }

        else {
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
            return word.substring(0, word.length() - 2 )+ "e";
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

    public String stemS(String word){

        if (word.endsWith("ss")){
            return word = word.substring(0, word.length());
        }
        if (word.endsWith("ious")){
            return word;
        }
        if (word.length()>= 3 && word.endsWith("s")){
            return word = word.substring(0, word.length()-1);
        }
        
        return word;
    }

}