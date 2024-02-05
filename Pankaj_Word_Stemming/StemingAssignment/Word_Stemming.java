package StemingAssignment;

public class Word_Stemming {
    public static String checkWordsEnd(String word, String suffix){
        // Use a series of if-else statements to check the word for the suffix and assign the suffix variable accordingly
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
        }
        else if (word.endsWith("ant")){
            suffix  = "ant";
        }
        else if (word.endsWith("ness")){
            suffix  = "ness";
        }
        else if (word.endsWith("es")){
            suffix  = "es";
        }
        else if (word.endsWith("ic")){
            suffix  = "ic";
        }
        else if (word.endsWith("er")){
            suffix  = "er";
        }
        else if (word.endsWith("ing")){
            suffix  = "ing";
        }
        else if (word.endsWith("s")){
            suffix  = "s";
        }
        else {
            return "";
        }
        return suffix;
    }

    public static String stemmedWord(String suffix, String stemmedWord, Stemmer_word stemmer, String word){

        // Use a switch statement to choose the appropriate method to call based on the suffix
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
                return "";
        }
        return stemmedWord;
    }


}
