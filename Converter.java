/**
 * David Killian
 * Summer 2022
 * 6/24/22
 * Professor Beyer
 * CMSC 350 6981
 */
import java.awt.event.*;
import javax.swing.*; import javax.swing.JOptionPane;
import java.util.Stack;

// make class 
public class Converter extends JFrame{
  // set variables 
  private static JLabel label;
  private static JTextArea userInput;
  private static JButton preToPost;
  private static JButton postToPre;
  private static String userString;

// main class
  public static void main(String[] args){
    //new instance of object, call gui setup method 
      Converter c = new Converter();
      c.GuiSetup();
    }

    public void GuiSetup() {
      // set up frame and label 
      JFrame frame = new JFrame("Prefix - Postfix Converter");
      label = new JLabel("Enter your expression below: ");
      label.setBounds(220,50,200,30);
      frame.add(label);
      // set up text area for user input 
      userInput = new JTextArea();
      userInput.setBounds(220, 100, 200, 18);
      frame.add(userInput);
      // set up button for PREFIX TO POSTFIX
      preToPost = new JButton("Prefix to PostFix");
      preToPost.setBounds(75, 200, 200, 30);
      frame.add(preToPost);
      // set up button for POSTFIX TO PREFIX
      postToPre = new JButton("PostFix to PreFix");
      postToPre.setBounds(325, 200, 200, 30);
      frame.add(postToPre);
      // set size of frame, set vis, set program to close when window closes 
      frame.setSize(650, 650);
      frame.setLayout(null);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      /**
       * Action listeners
       */
      preToPost.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          userString = userInput.getText();
          try{
            prePostCalc(userString);
          } catch(Exception er) {
            JOptionPane.showMessageDialog(null, "Error with PreFix expression");
          }
        }
      });
      // action listener for post to pre 
      postToPre.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          userString = userInput.getText();
          try{
            postToPreCalc(userString);
          } catch(Exception er) {
            JOptionPane.showMessageDialog(null, "Error with PostFix expression");
          }
        }
      });
    }
    // method for pre to post
    public void prePostCalc(String userString){
      // create new stack 
      Stack<String> s = new Stack<String>();
      // loop through input in reverse 
      for (int i = userString.length() - 1; i>=0; i--){
        // check if each character is an operator +-/*^ or not 
        if(checkOperator(userString.charAt(i))) {
          String s1 = s.peek();
          s.pop();
          String s2 = s.peek();
          s.pop();
          // set up temp string with operators and numbers popped from the stack
          String tempString = s1 + s2 + userString.charAt(i);
          // push temp string to stack
          s.push(tempString);
        } else {
          // empty stack
          s.push(userString.charAt(i) + "");
        }
      }
      // get post fix answer, post to user in a message dialog pane 
      String postFixAns = s.peek();
      JFrame frame = new JFrame("JOptionPane showMessageDialog example");
      JOptionPane.showMessageDialog(frame, postFixAns);
    }
    
    public void postToPreCalc(String userString){
      Stack<String> s = new Stack<String>();
      
      for (int i = 0; i<userString.length(); i++){
        if(checkOperator(userString.charAt(i))) {
          String s1 = s.peek();
          s.pop();
          String s2 = s.peek();
          s.pop();

          String tempString = userString.charAt(i) + s2 + s1;
          s.push(tempString);
        } else {
          s.push(userString.charAt(i) + "");
        }
      }
      String postFixAns = s.peek();
      JFrame frame = new JFrame("JOptionPane showMessageDialog example");
      JOptionPane.showMessageDialog(frame, postFixAns);
    }
    // method to check if each char is an operator or not 
    public boolean checkOperator(char c) {
      if(c == '+'){
        return true;
      }
      if(c == '-'){
        return true;
      }
      if(c == '*'){
        return true;
      }
      if(c == '/'){
        return true;
      }
      if(c == '^'){
        return true;
      }
      return false;
    }
}