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

public class SaveFileFrame extends JFrame implements ActionListener {
   private JLabel pathLabel;    
   private JLabel nameLabel;      
   private JTextField nameField;
   private JTextField pathField; 
   private JButton saveButton;
   private JButton cancelButton;
   private String[] text;
   private String currentFilePath;
   private String currentFileName;


   // Constructor does everything
   SaveFileFrame(String currentFilePath, String currentFileName, String[] text) {
      this.text = text;
      this.currentFileName = currentFileName;
      this.currentFilePath = currentFilePath;
      GridBagConstraints layoutConst = null;

      // Set frame's title
      setTitle("Save");

      pathLabel = new JLabel("File Path:");
      nameLabel = new JLabel("File Name:");

      // Set all the fields and buttons
      pathField = new JTextField(15);
      pathField.setEditable(true);
      pathField.setText(currentFilePath);

      nameField = new JTextField(15);
      nameField.setEditable(true);
      nameField.setText(currentFileName);

      saveButton = new JButton("Save");
      saveButton.addActionListener(this);
      
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
      add(saveButton, layoutConst);
      
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

   //Activates when either button is pressed
   @Override
   public void actionPerformed(ActionEvent event){
        boolean repeat;
        // Get source of event
        JButton sourceEvent = (JButton) event.getSource();
        String fileThing = "";//Stores complete path+name of file
    
        // User pressed the save button
        if (sourceEvent == saveButton) {
            try{//Try-catch to get possible file exceptions
                String path = pathField.getText();
                String name = nameField.getText() + ".txt";
                fileThing = path + name;
                fileThing.replace("/", "\\");
                File myFile = new File(fileThing);
                FileWriter output = new FileWriter(myFile);
                PrintWriter p = new PrintWriter(output);
                for(String s : text) {
                    p.println(s);
                }//Writes to file, then closes window
                p.close();
                dispose();
            }
            catch(IOException a){
                System.out.println(a);
                nameField.setText("Invalid");//Tells user if is incorrect
            }
        }
        else if(sourceEvent == cancelButton){
            dispose();//If cancel is hit, just close the program
        }
    }

    public void main(){
        
        SaveFileFrame a = new SaveFileFrame(currentFilePath, currentFileName, text);//These four lines are all that
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Will be necessary for the main class
        a.pack();
        a.setVisible(true);
    }
}