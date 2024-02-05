import javax.swing.*;
import java.awt.event.*;
import java.util.Map;


public class SonalStemmingAssignment implements ActionListener{
    JLabel label_01, label_02;
    JTextArea textSection_01, textSection_02;
    JButton button;
    void TextExample() {
        JFrame f= new JFrame();
        label_01=new JLabel();
        label_02=new JLabel();
        textSection_01 = new JTextArea();
        textSection_02 = new JTextArea();

        label_01.setBounds(50,25,100,30);
        textSection_01.setBounds(550,80,800,150);
        label_02.setBounds(160,25,100,30);
        textSection_02.setBounds(550, 320, 800, 150);

        button = new JButton("Find Word");
        button.setBounds(850, 250, 120, 30);
        button.addActionListener(this);
        f.add(textSection_01);
        f.setSize(1920,1080);
        f.setLayout(null);
        f.add(textSection_02);
        f.setVisible(true);
        f.add(label_01);f.add(label_02);
        f.add(button);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }


    public void actionPerformed(ActionEvent e){
        String text_entered = textSection_01.getText();
        String words[]=text_entered.split("\\s");
        Stemming stem_word = new Stemming();
        Map<String, String> output;
        output = stem_word.wordCollection(words);
        String result = "";
        for(String i : output.keySet()){
            String add = i+" -----> "+output.get(i);
            result = result + add +"\n";
            textSection_02.setText(result);
        }
        }

    public static void main(String[] args) {
	    SonalStemmingAssignment Obj = new SonalStemmingAssignment();
	    Obj.TextExample();
        }
}