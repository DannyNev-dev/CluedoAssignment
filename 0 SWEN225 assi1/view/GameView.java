package view;

import java.awt.Dimension;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.GameController;
import controller.MainMenuController;

/**
 * @author Daniel,Ketaki,Victoria
 * This is the main game screen class
 * Creates Menus and popup windows
 * This handles creating suggestion & accusation panels
 * Combines the different view classes to create the main game screen
 * 
 *	
 */
public class GameView {

	JFrame gameScreen;
	JFrame mainMenu;

	BoardView boardView;
	//GameController gc;

	public GameView(BoardView aBoardView) {
		this.boardView = aBoardView;
		this.gameScreen = createGameFrame();
		this.gameScreen.setVisible(true);
	}

	private JFrame createGameFrame() {
		JFrame f = new JFrame("Game");
		f.setSize(new Dimension(800, 800));
		f.setJMenuBar(createMenu());

		f.add(boardView);

		return f;
	}

	private JMenuBar createMenu() {
		//Declare menu bar and menu components
		JMenuBar mb = new JMenuBar();
		JMenu menu, helpMenu;
		JMenuItem reset, newGame, controls;
		//Initialize menu components
		menu = new JMenu("Game");
		helpMenu = new JMenu("Help");
		reset = new JMenuItem("Reset");
		newGame = new JMenuItem("NewGame");
		controls = new JMenuItem("Controls");
		//Add components to menus
		menu.add(reset);
		menu.add(newGame);
		helpMenu.add(controls);
		//Add menus to menu bar
		mb.add(menu);
		mb.add(helpMenu);
		return mb;
	}

	/*public void setGc(GameController gc) {
		this.gc = gc;
	}*/

	public JFrame getGameScreen() {
		return this.gameScreen;
	}

	public JFrame getMainMenu() {
		return this.mainMenu;
	}

	public JFrame suggestionView() {
		JFrame f = new JFrame("Suggestions");
		f.setSize(new Dimension(800, 800));
		JPanel canvas = new JPanel(null);
		ArrayList<CardView> characterCards;// = gc.getCharCards();
		ArrayList<CardView> weaponCards;// = gc.getWeaponCards();
		//Create GUI components - TITLE
		JLabel label = new JLabel("Time To make a suggestion!");
		label.setFont(label.getFont().deriveFont(25.0f));
		label.setBounds(210, 10, 400, 50);
		JLabel textLabel = new JLabel("Select a character:");
		textLabel.setBounds(10, 60, 200, 50);
		// character cards
        /*int x = 10;
        for(CardView cv: characterCards) {
        	cv.setBounds(x,70,100,200);
        	canvas.add(cv);
        	add a radio button with the cv's name underneath each card
        	x+=110;
        }*/
		JLabel textLabel2 = new JLabel("Select a weapon:");
		textLabel2.setBounds(10, 400, 200, 50);
		// weapon cards
        /*x = 10;
        for(CardView cv: weaponCards) {
        	cv.setBounds(x,410,100,200);
        	canvas.add(cv);
        	add a radio button with the cv's name underneath each card
        	x+=110;
        }*/
		JButton confirmButton = new JButton("confirm");    //Button
		confirmButton.setBounds(325, 700, 150, 50);
		//Add components to the content pane
		canvas.add(label);
		canvas.add(textLabel);
		canvas.add(textLabel2);
		canvas.add(confirmButton);
		f.setContentPane(canvas);
		return f;
	}

	public void showOptions() {
		String text = "Click the Dice to roll it!\n"
				+ "-------------------------------\n"
				+ "Move using WASD";
		JOptionPane.showMessageDialog(gameScreen, text);
	}


	public static void main(String[] args) {
		new MainMenuController();
	}
}
