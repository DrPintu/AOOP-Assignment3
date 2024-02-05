# Stemming words GUI

This Java program provides a graphical user interface (GUI) for stemming words using the Porter stemming algorithm. The application allows users to input a text, process it, and view the stemmed words along with their original counterparts.


To run the Stemmeing word GUI, follow these steps:

1. Make sure you have Java installed on your machine.
2. Download the `PorterStemmer.java` file.
3. Open a terminal and navigate to the directory where the file is located.
4. Compile the Java code using the following command:

   ```bash
   javac PorterStemmer.java
   ```

5. Run the compiled code with:

   ```bash
   java PorterStemmer
   ```

6. The GUI window will appear, allowing you to enter text and click the "Process" button to see the stemmed results.

## Usage

1. Enter text in the "Enter text:" area.
2. Click the "Process" button to apply the Porter stemming algorithm.
3. View the stemmed words in the "Stemmed text:" area.

## Stemming Rules

The stemming rules are applied to the input words, covering various suffixes such as "ed," "ly," "ful," "est," "ity," "ant," "ness," "es," "ic," "er," "ing," and "s." Each rule is implemented in the `StemmerWord` class.

## Example

### Input
```
The quick brown foxes are jumping over the lazy dogs.
```

### Output
```
quick: quick
brown: brown
fox: fox
are: are
jumping: jump
over: over
lazy: lazi
dogs: dog
```

Author:
Nagmani Kumar
Student at Sitare University