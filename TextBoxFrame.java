import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class TextBoxFrame extends JFrame implements ActionListener {
	JButton b1;
	JButton b2;
	JLabel l;
	JTextArea textArea;
	
	public TextBoxFrame(String t) {
		JFrame j = new JFrame();
		j.setTitle("Text Editor"); //editor? barely knew her!
		l = new JLabel("Mega Text Editor");
		textArea = new JTextArea(t, 20, 20);
		JScrollPane jsp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		j.setLayout(new FlowLayout());
		b1 = new JButton("Save File");
		b1.setBounds(100, 300, 120, 30);
		b1.addActionListener(this);
		b2 = new JButton("Open File");
		b2.setBounds(220, 300, 120, 30);
		b2.addActionListener(this);
		j.add(l);
		j.add(jsp);
		j.add(b1);
		j.add(b2);
		j.setSize(300, 450);
		j.setVisible(true);
		textArea.setEditable(true);
		pack();
	}
	
	public static void main(String[] args) {
		TextBoxFrame text = new TextBoxFrame("");
		text.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String currentPath = "C:/Users/studentgvsc/Desktop/";
		String currentName = "Enter File Name Here";
		String editorText = textArea.getText();
		String[] lines = editorText.split("\\n");
		JButton sourceEvent = (JButton) e.getSource();
		if(sourceEvent == b1) {
			SaveFileFrame sff = new SaveFileFrame(currentPath, currentName, lines);
			sff.setVisible(true);
		} else if(sourceEvent == b2) {
			OpenFileFrame off = new OpenFileFrame(currentPath);
			off.setVisible(true);			
			
		}
		return;
		
	}
	
}
