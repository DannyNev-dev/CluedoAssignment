package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import view.MainMenuView;

public class MainMenuController implements ActionListener{
	
	MainMenuView m;
	Component[] componentList;
	public MainMenuController(){
		this.m = new MainMenuView();		
		this.componentList = m.getContentPane().getComponents();
		JButton j = (JButton) componentList[3];
		j.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//when the button is pressed set the number of players and start the game 
		JTextField textField = (JTextField) componentList[2];		
		if(textField.getText() == null || !isNum(textField.getText())) {
			m.badInputDialog();			
			textField.setText("");
		}
		else {
			int i = Integer.parseInt(textField.getText());
			if(i < 3 || i > 6) {
				m.wrongNumDialog();				
				textField.setText("");
			}
			else {
				new GameController(i);	//If everything is ok with the input, create game controller
				m.setVisible(false);//make this frame not visible
				
			}
		}											
	}
	
	@SuppressWarnings("unused")
	private boolean isNum(String text) {	
		int numPlayers = 0;
		try {
			numPlayers = Integer.parseInt(text);
			//check that input is valid
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}			
}
