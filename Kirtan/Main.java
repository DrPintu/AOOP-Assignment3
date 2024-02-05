import javax.swing.*;
import java.awt.event.*;
import java.util.Map;


public class Main implements ActionListener{
    JLabel label1, label2;
    JTextArea textArea1, textArea2;
    JButton button;
    void TextArea() {
        JFrame f= new JFrame();
        label1=new JLabel();
        label2=new JLabel();
        textArea1=new JTextArea();
        textArea2=new JTextArea();

        label1.setBounds(50,25,100,30);
        label2.setBounds(160,25,100,30);
        textArea1.setBounds(550,80,800,150);
        textArea2.setBounds(550, 320, 800, 150);

        button=new JButton("Find Stem Word");
        button.setBounds(900,500,120,30);
        button.addActionListener(this);
        f.add(label1);f.add(label2);f.add(textArea1);f.add(textArea2);f.add(button);
        f.setSize(1920,1080);
        f.setLayout(null);
        f.setVisible(true);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }


    public void actionPerformed(ActionEvent e){
        String text=textArea1.getText();
        String words[]=text.split("\\s");
        Stemming stem = new Stemming();
        Map<String, String> output;
        output = stem.wordCollection(words);
        String outputString = "";
        for(String i : output.keySet()){
            String pair = i+" -----> "+output.get(i);
            outputString = pair+"\n";
            textArea2.append(outputString);
        }
        }


    public static void main(String[] args) {
	    Main stemObj = new Main();
	    stemObj.TextArea();
        }
}