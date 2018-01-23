import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.io.*;
import java.util.*;

public class OpenFileFrame extends JFrame implements ActionListener {
   private JLabel pathLabel;    
   private JLabel nameLabel;      
   private JTextField nameField;  
   private JTextField pathField; 
   private JButton openButton;
   private JButton cancelButton;
   private String text;
   public boolean wait = true;
   public String openFilePath = "";
   public String openFileName = "";


   /* Constructor creates GUI components and adds GUI components
      using a GridBagLayout. */
    OpenFileFrame(String currentFilePath) {
      // Used to specify GUI component layout
      GridBagConstraints layoutConst = null;

      // Set frame's title
      setTitle("Open");

      pathLabel = new JLabel("File Path:");
      nameLabel = new JLabel("File Name:");

      // Set hourly and yearly salary
      pathField = new JTextField(15);
      pathField.setEditable(true);
      pathField.setText(currentFilePath);

      nameField = new JTextField(15);
      nameField.setEditable(true);

      openButton = new JButton("Open");
      openButton.addActionListener(this);
      
      cancelButton = new JButton("Cancel");
      cancelButton.addActionListener(this);
    
      // Use a GridBagLayout
      setLayout(new GridBagLayout());

      layoutConst = new GridBagConstraints();
      layoutConst.gridx = 0;
      layoutConst.gridy = 0;
      layoutConst.insets = new Insets(10, 10, 10, 10);
      add(pathLabel, layoutConst);

      layoutConst = new GridBagConstraints();
      layoutConst.gridx = 1;
      layoutConst.gridy = 0;
      layoutConst.insets = new Insets(10, 10, 10, 10);
      add(pathField, layoutConst);

      layoutConst = new GridBagConstraints();
      layoutConst.gridx = 0;
      layoutConst.gridy = 1;
      layoutConst.insets = new Insets(10, 10, 10, 10);
      add(nameLabel, layoutConst);

      layoutConst = new GridBagConstraints();
      layoutConst.gridx = 1;
      layoutConst.gridy = 1;
      layoutConst.insets = new Insets(10, 10, 10, 10);
      add(nameField, layoutConst);

      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(20, 60, 10, 10);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 1;
      layoutConst.gridy = 3;
      add(openButton, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(20, 10, 10, 0);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 0;
      layoutConst.gridy = 3;
      add(cancelButton, layoutConst);
        
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.pack();
      this.setVisible(true);
   }
    
    public String getText() {
    	return text;
    }

   //Activates upon hitting either button
   @Override
    public void actionPerformed(ActionEvent event){
        boolean repeat;
        // Get source of event
        JButton sourceEvent = (JButton) event.getSource();
        String fileThing = "";
    
        // User pressed the open button
        if (sourceEvent == openButton) {
            try{
                openFilePath = pathField.getText();//Adds what you opened to the instance variables,
                openFileName = nameField.getText();//Is what will autofill for save method later
                fileThing = openFilePath + openFileName + ".txt";
                fileThing.replace("/", "\\");
                File myFile = new File(fileThing);
                Scanner input = new Scanner(myFile);
                text = input.nextLine();
                while(input.hasNextLine()){
                    text = text + "\n" + input.nextLine(); //THis is in case someone has edited it, as a string has \n
                }                                          //As enter
                wait = false; //Once it has been opened, allows main to continue and display the text
                dispose();

            }
            catch(IOException a){
                System.out.println(a);
                nameField.setText("File Does Not Exist"); //Tells user if input is invalid
            }
        }
        else if(sourceEvent == cancelButton){
            dispose();//If you hit cancel, closes window
        }
        TextBoxFrame tbf = new TextBoxFrame(text);
        return;
    }

    public static void main(String[] args){
        String currentPath = "C:/Users/studentgvsc/Desktop/";

        OpenFileFrame a = new OpenFileFrame(currentPath);//These 7 lines are all you will need in the main class
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.pack();
        a.setVisible(true);    
        while(a.wait){ //This is here because having gui method doesn't 'pause' main like other methods, but we still
            System.out.print("");//have to wait for a.text to be filled
        }

        System.out.println(a.text); //You get the text out with a.text variable - this line is just to show it works

    }
}