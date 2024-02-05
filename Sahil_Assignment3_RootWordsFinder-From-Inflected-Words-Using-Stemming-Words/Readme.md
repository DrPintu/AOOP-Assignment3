# Inflected Word Finder

## Description
This Java program provides a graphical user interface (GUI) for finding inflected words and their root forms based on specified stemming rules. The stemming rules are implemented in the `WordStemmer` class, where various suffixes are handled to transform words into their root forms.

## Author
- **Author:** Sahil Kumar
- **Date:** 5 February 2024

## Usage
1. Run the program by executing the `InflectedWordFinder` class.
2. The GUI window will appear with an input text area, a button to find inflected words, and an output area for displaying the stemmed text.
3. Enter the desired text in the input area.
4. Click the "Find Inflected Words" button.
5. The program will display the original words and their stemmed forms in the output area.

## Stemming Rules
The stemming rules are applied to various suffixes, such as "ing," "ed," "s," "ly," "ful," "est," "ity," and more. Additional suffixes can be added to the `commonSuffixes` set in the `WordStemmer` class to enhance the accuracy of the stemming process.

## Modifying the Program
- To add more stemming rules for different suffixes, you can create additional `stemX` methods in the `WordStemmer` class, where "X" is the suffix.
- The `applyStemmingRule` method in the `WordStemmer` class directs the program to the appropriate stemming method based on the suffix.

## Example
```java
public class InflectedWordFinder {
    public static void main(String[] args) {
        new InflectedWordFinder();
    }
}
