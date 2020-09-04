package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import view.MainMenuView;

/**@author Daniel
 * 
 * The Class MainMenuController.
 * Handles starting and creating the game screen, and setting up listeners
 */
public class MainMenuController implements ActionListener{

	/** The m. */
	MainMenuView m;
	
	/** The component list. */
	Component[] componentList;
	
	/**
	 * Instantiates a new main menu controller.
	 */
	public MainMenuController(){
		this.m = new MainMenuView();
		this.componentList = m.getContentPane().getComponents();
		JButton j = (JButton) componentList[3];
		j.addActionListener(this);
	}

	/**
	 * Action performed.
	 *
	 * @param e the e
	 */
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

	/**
	 * Checks if is num.
	 *
	 * @param text the text
	 * @return true, if is num
	 */
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
