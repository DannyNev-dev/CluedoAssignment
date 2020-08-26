package view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MainMenuView extends JFrame{
	
	JFrame f;
	
	public MainMenuView(){
		 //set up the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        setSize(305,250);
        //set content pane to our desired JPanel object    
        setContentPane(createContentPane());
        setVisible(true);
	}
	
	private JPanel createContentPane() {
		//create panel and set background colour
		JPanel contentPane = new JPanel(null);
        contentPane.setBackground(Color.red);
        
        //Create GUI components
        JLabel label = new JLabel("Welcome to Cluedo!");	//Main Label
        label.setFont(label.getFont ().deriveFont (25.0f));
        label.setBounds(20,10, 250, 50);
        JLabel textLabel = new JLabel("Please enter the number of players: 3-6"); //Secondary Label
        textLabel.setBounds(25,100,250, 50);
        JTextField textInput = new JTextField();	//text field NOTE: change to formatted text field maybe
        textInput.setBounds(93,150,100, 20);
        JButton confirmButton = new JButton("confirm");	//Button
        confirmButton.setBounds(93,180,100, 20);
        //Add components to the content pane
        contentPane.add(label);
        contentPane.add(textLabel);
        contentPane.add(textInput);
        contentPane.add(confirmButton);
        
        return contentPane;
	}
}
