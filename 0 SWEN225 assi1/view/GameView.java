package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
	GameController gc;
	
	public GameView() {
		this.gameScreen = createGameFrame();
		this.gameScreen.setVisible(true);
	}

	private JFrame createGameFrame() {
		JFrame f = new JFrame("Game");
		f.setSize(new Dimension(800,800));
		f.setJMenuBar(createMenu());
		
		return f;
	}
	private JMenuBar createMenu() {
		//Declare menu bar and menu components
		JMenuBar mb = new JMenuBar();		
		JMenu menu, helpMenu;	
		JMenuItem reset,newGame,controls;
		//Initialize menu components
		menu = new JMenu("Game"); helpMenu = new JMenu("Help");		
		reset = new JMenuItem("Reset"); newGame = new JMenuItem("New Game"); controls = new JMenuItem("Controls");
		//Add components to menus
		menu.add(reset); menu.add(newGame);
		helpMenu.add(controls);
		//Add menus to menu bar
		mb.add(menu); mb.add(helpMenu);		
		return mb;	
	}
	public void setGc(GameController gc) {this.gc = gc;}
	
	public static void main(String[] args) {       
        new MainMenuController();      
    }
	
	

}
