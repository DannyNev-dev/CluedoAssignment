package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.CardController;
import controller.GameController;
import controller.MainMenuController;
import model.Player;

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

	//screens for the game
	JFrame gameScreen;
	JFrame mainMenu;
	JFrame makeSuggestionScreen;
	JFrame accusationInquiryScreen;
	JFrame refuteCardFoundScreen;
	JFrame noRefuteCardFoundScreen;

	//components for the game screen
	JButton rollDiceButton = new JButton("Roll Dice");
	List<ImageIcon> diceImages;
	JLabel firstDice;
	JLabel secondDice;

	//components for the make suggestion screen
	ButtonGroup charButGroup;
	ButtonGroup weapButGroup;
	JButton suggestConfirmButton;

	//components for the no refute card found screen
	JButton noRefuteBackButton;

	//components for the refute card found screen
	JLabel refuteCard;
	JButton refuteBackButton;

	BoardView boardView;	//for displaying the board
	PlayerView currentPlayer;	//for displaying current player's hand
	GameController gc;

	public GameView(BoardView aBoardView, PlayerView player,GameController gc) {
		this.boardView = aBoardView;
		this.currentPlayer = player;
		this.gameScreen = createGameFrame();
		this.gameScreen.setVisible(true);
		this.gc = gc;
	}

	private JFrame createGameFrame() {
		JFrame f = new JFrame("Game");
		f.setSize(new Dimension(1200, 1200));
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
		constraints.gridy = 1;
		constraints.ipady = 0;
		//add JPanel for dice
		JPanel rollDice = new JPanel();
		rollDice.setLayout(new BoxLayout(rollDice, BoxLayout.X_AXIS));
		//add components
		//dice pictures
		firstDice = new JLabel();
		secondDice =  new JLabel();
		//get dice image
		BufferedImage dicePictureBuffer;
		Image dicePicture;
		diceImages = new ArrayList<>();
		for(int i = 1; i < 7; i++) {
			try {
				dicePictureBuffer = ImageIO.read(new File("DiceFaces//Dice_"+String.valueOf(i)+".png"));
				diceImages.add(new ImageIcon(dicePictureBuffer.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
			} catch (IOException e) {
				System.out.println("dice picture not found!");
				return null;
			}
		}
		//at beginning of game draw first dice image
		firstDice.setIcon(diceImages.get(0));
		secondDice.setIcon(diceImages.get(0));
		rollDice.add(firstDice);
		rollDice.add(secondDice);
		rollDice.add(rollDiceButton);
		f.add(rollDice, constraints);

		//draw player's cards
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.ipadx = 100;
		constraints.ipady = 800;
		f.add(currentPlayer);

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

	/**
	 * create suggestion view screen which is for making suggestions
	 */
	public void suggestionView() {
		makeSuggestionScreen = new JFrame("Suggestions");
		makeSuggestionScreen.setDefaultCloseOperation(0);	//so user doesn't close window prematurely
		makeSuggestionScreen.setSize(new Dimension(800, 800));
		JPanel canvas = new JPanel(null);
		ArrayList<CardController> characterCards = gc.getCharCards();
		ArrayList<CardController> weaponCards = gc.getWeaponCards();

		//Create GUI components - TITLE
		JLabel label = new JLabel("Time To make a suggestion!");
		label.setFont(label.getFont().deriveFont(25.0f));
		label.setBounds(210, 10, 400, 50);
		JLabel textLabel = new JLabel("Select a character:");
		textLabel.setBounds(10, 60, 200, 50);
		// character cards
		charButGroup = new ButtonGroup();
        int x = 10;
        for(int i = 0; i < characterCards.size(); i++) {
        	CardView cv = characterCards.get(i).getView();
        	cv.setBounds(x,70,100,200);
        	JRadioButton jBut = new JRadioButton(); jBut.setText(cv.getName()); jBut.setBounds(x,275,100,50);
        	jBut.setActionCommand(characterCards.get(i).getModel().toString());
        	canvas.add(jBut);        	
        	canvas.add(cv);
        	charButGroup.add(jBut);
        	x+=110;
        }
		JLabel textLabel2 = new JLabel("Select a weapon:");
		textLabel2.setBounds(10, 400, 200, 50);
		// weapon cards
		weapButGroup = new ButtonGroup();
        x = 10;
        for(int i = 0; i < weaponCards.size(); i++) {
        	CardView cv = weaponCards.get(i).getView();
        	cv.setBounds(x,410,100,200);
        	JRadioButton jBut = new JRadioButton(); jBut.setText(cv.getName()); jBut.setBounds(x,615,100,50);
			jBut.setActionCommand(weaponCards.get(i).getModel().toString());
        	canvas.add(jBut);       	
        	canvas.add(cv);
        	weapButGroup.add(jBut);
        	x+=110;
        }
		suggestConfirmButton = new JButton("confirm");    //Button
		suggestConfirmButton.setBounds(325, 700, 150, 50);
		//Add components to the content pane
		canvas.add(label);
		canvas.add(textLabel);
		canvas.add(textLabel2);
		canvas.add(suggestConfirmButton);
		makeSuggestionScreen.setContentPane(canvas);
	}

	/**
	 * create accusation inquiry view screen
	 */
	public void accusationInquiryView() {
		accusationInquiryScreen = new JFrame("Want To Choose An Accusation?");
		accusationInquiryScreen.setDefaultCloseOperation(0);	//so user doesn't close window prematurely
		accusationInquiryScreen.setSize(new Dimension(800, 400));
		JPanel canvas = new JPanel(null);
		//add question title
		JLabel label = new JLabel("Do you want to make an accusation?");
		label.setFont(label.getFont().deriveFont(25.0f));
		label.setBounds(150, 10, 600, 50);

		JButton yesButton = new JButton("Yes");
		yesButton.setBounds(150, 200, 100, 50);
		JButton noButton = new JButton("No");
		noButton.setBounds(500, 200, 100, 50);

		canvas.add(label);
		canvas.add(yesButton);
		canvas.add(noButton);

		accusationInquiryScreen.setContentPane(canvas);
	}

	/**
	 * create no refute card found screen
	 */
	public void noRefuteCardFoundScreen() {
		noRefuteCardFoundScreen = new JFrame("No refute card found");
		noRefuteCardFoundScreen.setDefaultCloseOperation(0);	//so user doesn't close window prematurely
		noRefuteCardFoundScreen.setSize(new Dimension(400, 400));
		JPanel canvas = new JPanel();
		canvas.setLayout(new BoxLayout(canvas, BoxLayout.Y_AXIS));
		//add text
		JLabel title = new JLabel("No refute card found");
		title.setFont(title.getFont().deriveFont(25.0f));
		JLabel text = new JLabel("Suggestions added to accusations");

		noRefuteBackButton = new JButton("Back");
		noRefuteBackButton.setSize(new Dimension(50, 25));

		canvas.add(title);
		canvas.add(text);
		canvas.add(noRefuteBackButton);

		noRefuteCardFoundScreen.setContentPane(canvas);
	}

	/**
	 *
	 */
	public void refuteCardFoundScreen() {
		refuteCardFoundScreen = new JFrame("Refute card found");
		refuteCardFoundScreen.setDefaultCloseOperation(0);	//so user doesn't close window prematurely
		refuteCardFoundScreen.setSize(new Dimension(400, 400));
		//set layout to box
		JPanel canvas = new JPanel();
		canvas.setLayout(new BoxLayout(canvas, BoxLayout.Y_AXIS));
		//add text
		JLabel title = new JLabel("Refute card found!");
		title.setFont(title.getFont().deriveFont(25.0f));
		refuteCard = new JLabel();

		refuteBackButton = new JButton("Back");
		refuteBackButton.setSize(new Dimension(50, 25));

		canvas.add(title);
		canvas.add(refuteCard);
		canvas.add(refuteBackButton);

		refuteCardFoundScreen.setContentPane(canvas);
	}

	public void showOptions() {
		String text = "Click the Dice to roll it!\n"
				+ "-------------------------------\n"
				+ "Move using WASD";
		JOptionPane.showMessageDialog(gameScreen, text);
	}

	/**public void addPlayerView(PlayerView p) {
		players.add(p);
	}**/

	//for calling screens to the game controller
	public JFrame getMakeSuggestionScreen() { return  makeSuggestionScreen; }
	public JFrame getAccusationInquiryScreen() { return accusationInquiryScreen; }
	public JFrame getNoRefuteCardFoundScreen() { return noRefuteCardFoundScreen; }
	public JFrame getRefuteCardFoundScreen() { return refuteCardFoundScreen; }

	//suggestion screen components
	public ButtonGroup getCharButGroup() { return charButGroup; }
	public ButtonGroup getWeapButGroup() { return weapButGroup; }
	public JButton getSuggestConfirmButton() { return suggestConfirmButton;}

	//no refute card found components
	public JButton getNoRefuteBackButton() { return noRefuteBackButton; }
	public JButton getRefuteBackButton() { return refuteBackButton; }
	public void setRefuteCard(JLabel card) { this.refuteCard = card; }

	public JButton getRollDiceButton() {
		return rollDiceButton;
	}

	public BoardView getBoardView() {
		return boardView;
	}

	public void animateDice(int[] diceValues) {
		//to add later small animation for rolling dice
		//draw final images
		firstDice.setIcon(diceImages.get(diceValues[0]-1));
		secondDice.setIcon(diceImages.get(diceValues[1]-1));
	}


	public static void main(String[] args) {
		new MainMenuController();
	}
}
