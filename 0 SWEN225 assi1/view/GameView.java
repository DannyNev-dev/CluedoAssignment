package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

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
	List<PlayerView> players;
	//GameController gc;

	public GameView(BoardView aBoardView, List<PlayerView> players) {
		this.boardView = aBoardView;
		this.players = players;
		this.gameScreen = createGameFrame();
		this.gameScreen.setVisible(true);
	}

	private JFrame createGameFrame() {
		JFrame f = new JFrame("Game");
		f.setSize(new Dimension(900, 900));
		f.setJMenuBar(createMenu());
		f.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		//draw board
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.ipadx = 700;
		constraints.ipady = 700;
		f.add(boardView, constraints);

		//draw roll dice panel
		constraints.gridx=  0;
		constraints.gridy = 2;
		constraints.ipady = 80;
		//add JPanel for dice
		JPanel rollDice = new JPanel();
		rollDice.setLayout(new BoxLayout(rollDice, BoxLayout.X_AXIS));
		//add components
		//dice pictures
		JLabel firstDice = new JLabel();
		JLabel secondDice =  new JLabel();
		//get dice image
		BufferedImage dicePictureBuffer;
		Image dicePicture;
		try {
			dicePictureBuffer = ImageIO.read(new File("DiceFaces//Dice_1.png"));
			dicePicture = dicePictureBuffer.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			System.out.println("dice picture not found!");
			return null;
		}
		firstDice.setIcon(new ImageIcon(dicePicture));
		secondDice.setIcon(new ImageIcon(dicePicture));
		JButton rollButton = new JButton("Roll Dice");
		rollDice.add(firstDice);
		rollDice.add(secondDice);
		rollDice.add(rollButton);
		f.add(rollDice, constraints);

		//draw player's cards
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.ipadx = 100;
		constraints.ipady = 800;
		PlayerView playerHand = players.get(0);
		f.add(playerHand);

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

	public void addPlayerView(PlayerView p) {
		players.add(p);
	}


	public static void main(String[] args) {
		new MainMenuController();
	}
}
