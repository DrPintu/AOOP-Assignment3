/*
Problem Statement:
Given a text paragraph or any paragraph taken by text file reading in Java,
you have to find inflected words and print their root forms. You need to create 
one text area for showing (or entering) the paragraph. When you click on a certain 
button, another text area will show the inflected words along with their roots. 
You are not allowed to use any given libraries for this task.
 
As an example, consider the following text.

He was flying 10000 feet above. All passengers including him were enjoying the flight.....

Output may look like follows
flying -- > fly
Passengers --> Passenger
... and so on.

Name:- Bharat Suthar
Date:= 5/2/2024
*/
import javax.swing.*;
import java.awt.event.*;
import java.util.Map;


public class Assignment2 implements ActionListener{
    JLabel l1,l2;
    JTextArea area, area1;
    JButton b;
    void TextAreaExample() {
        JFrame f= new JFrame();
        l1=new JLabel("Text Area for User");
        l1.setBounds(375,70,200,30);
        l2=new JLabel("Stem words from the given text");
        l2.setBounds(1200,70,300,30);
        area=new JTextArea();
        area1=new JTextArea();
        area.setBounds(50,100,800,750);
        area1.setBounds(900, 100, 800, 750);
        b=new JButton("Run for Stemming");
        b.setBounds(750,900,200,30);
        b.addActionListener(this);
        f.add(l1);f.add(l2);f.add(area);f.add(area1);f.add(b);
        f.setSize(450,450);
        f.setLayout(null);
        f.setVisible(true);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }


    public void actionPerformed(ActionEvent e){
        String text=area.getText();
        String words[]=text.split("\\s");
        Stemming stem = new Stemming();
        Map<String, String> output;
        output = stem.wordCollection(words);
        String outputString;
        for(String i : output.keySet()){
            String pair = i+" -----> "+output.get(i);
            outputString = pair+"\n";
            area1.append(outputString);
        }
        }


    public static void main(String[] args) {
	    Assignment2 a = new Assignment2();
	    a.TextAreaExample();
        }
}

