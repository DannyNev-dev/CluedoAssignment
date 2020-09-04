package view;

import java.awt.*;
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

// TODO: Auto-generated Javadoc
/**
 * The Class GameView.
 *
 * @author Daniel, Ketaki, Victoria
 * This is the main game screen class
 * Creates Menus and popup windows
 * This handles creating suggestion & accusation panels
 * Combines the different view classes to create the main game screen
 */
public class GameView {

	/** The game screen. */
	//screens for the game
	JFrame gameScreen;
	
	/** The main menu. */
	JFrame mainMenu;
	
	/** The make suggestion screen. */
	JFrame makeSuggestionScreen;
	
	/** The accusation inquiry screen. */
	JFrame accusationInquiryScreen;
	
	/** The refute card found screen. */
	JFrame refuteCardFoundScreen;
	
	/** The no refute card found screen. */
	JFrame noRefuteCardFoundScreen;
	
	/** The choose accusation screen. */
	JFrame chooseAccusationScreen;
	
	/** The game over screen. */
	JFrame gameOverScreen;

	/** The roll dice button. */
	//components for the game screen
	JButton rollDiceButton = new JButton("Roll Dice");
	
	/** The dice images. */
	List<ImageIcon> diceImages;
	
	/** The first dice. */
	JLabel firstDice;
	
	/** The second dice. */
	JLabel secondDice;

	/** The char but group. */
	//components for the make suggestion screen
	ButtonGroup charButGroup;
	
	/** The weap but group. */
	ButtonGroup weapButGroup;
	
	/** The suggest confirm button. */
	JButton suggestConfirmButton;

	/** The accusation inquiry yes button. */
	//components for the accusations inquiry screen
	JButton accusationInquiryYesButton;
	
	/** The accusation inquiry no button. */
	JButton accusationInquiryNoButton;

	/** The accusations but group. */
	//components for the choose accusation screen
	ButtonGroup accusationsButGroup;
	
	/** The accusation confirm button. */
	JButton accusationConfirmButton;

	/** The no refute back button. */
	//components for the no refute card found screen
	JButton noRefuteBackButton;

	/** The refute card. */
	//components for the refute card found screen
	JLabel refuteCard;
	
	/** The refute back button. */
	JButton refuteBackButton;

	/** The board view. */
	BoardView boardView;	//for displaying the board
	
	/** The current player. */
	PlayerView currentPlayer;	//for displaying current player's hand
	
	/** The gc. */
	GameController gc;

	/**
	 * Instantiates a new game view.
	 *
	 * @param aBoardView the a board view
	 * @param player the player
	 * @param gc the gc
	 */
	public GameView(BoardView aBoardView, PlayerView player,GameController gc) {
		this.boardView = aBoardView;
		this.currentPlayer = player;
		this.gameScreen = createGameFrame();
		this.gameScreen.setVisible(true);
		this.gc = gc;
	}

	/**
	 * Creates the game frame.
	 *
	 * @return the j frame
	 */
	private JFrame createGameFrame() {
		JFrame f = new JFrame("Game");
		f.setSize(new Dimension(990,800));
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
		//JPanel rollDice = new JPanel();
		//rollDice.setLayout(new BoxLayout(rollDice, BoxLayout.X_AXIS));
		//add components
		//dice pictures
		firstDice = new JLabel();
		secondDice =  new JLabel();
		//get dice image
		BufferedImage dicePictureBuffer;
		//Image dicePicture;
		diceImages = new ArrayList<>();
		for(int i = 1; i < 7; i++) {
			try {
				dicePictureBuffer = ImageIO.read(new File("DiceFaces//Dice_"+String.valueOf(i)+".png"));
				diceImages.add(new ImageIcon(dicePictureBuffer.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
			} catch (IOException e) {
				System.out.println("dice picture not found!");
				return null;
			}
		}
		//at beginning of game draw first dice image
		JPanel dicePanel = new JPanel();
		firstDice.setIcon(diceImages.get(0));
		secondDice.setIcon(diceImages.get(0));
		dicePanel.add(firstDice);
		dicePanel.add(secondDice);
		currentPlayer.add(dicePanel);
		currentPlayer.add(rollDiceButton);

		//draw player's cards
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.ipadx = 50;
		constraints.ipady = 700;
		f.add(currentPlayer);

		return f;
	}

	/**
	 * Creates the menu.
	 *
	 * @return the j menu bar
	 */
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

	/**
	 * Gets the game screen.
	 *
	 * @return the game screen
	 */
	public JFrame getGameScreen() {
		return this.gameScreen;
	}

	/**
	 * Gets the main menu.
	 *
	 * @return the main menu
	 */
	public JFrame getMainMenu() {
		return this.mainMenu;
	}

	/**
	 * create game over view screen.
	 */
	public void gameOverView() {
		gameOverScreen = new JFrame("Game Over");
		gameOverScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameOverScreen.setSize(600,400);
		gameOverScreen.setVisible(true);
		JPanel canvas = new JPanel(null);

		JLabel label = new JLabel("Game Over");
		label.setFont(label.getFont().deriveFont(25.0f));
		label.setBounds(210, 50, 400, 50);
		JLabel textLabel = null;
		if(currentPlayer.getModel().getCanWin()) { //if the player can win 
			textLabel = new JLabel("Player: " + currentPlayer.getName() + " Wins!");
			textLabel.setBounds(210, 150, 200, 50);
		}else { //if the player can't win 
			textLabel = new JLabel("Everyone Lost");
			textLabel.setBounds(235, 150, 200, 50);
		}

		canvas.add(label);
		canvas.add(textLabel);
		gameOverScreen.setContentPane(canvas);
	}

	/**
	 * create suggestion view screen which is for making suggestions.
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
	 * create accusation inquiry view screen.
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

		accusationInquiryYesButton = new JButton("Yes");
		accusationInquiryYesButton.setBounds(150, 200, 100, 50);
		accusationInquiryNoButton = new JButton("No");
		accusationInquiryNoButton.setBounds(500, 200, 100, 50);

		canvas.add(label);
		canvas.add(accusationInquiryYesButton);
		canvas.add(accusationInquiryNoButton);

		accusationInquiryScreen.setContentPane(canvas);
	}

	/**
	 * create no refute card found screen.
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
	 * Refute card found screen.
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

	/**
	 * create choose accusation view screen.
	 */
	public void chooseAccusationView() {
		chooseAccusationScreen = new JFrame("Choose accusation");
		chooseAccusationScreen.setDefaultCloseOperation(0);	//so user doesn't close window prematurely
		chooseAccusationScreen.setSize(new Dimension(800, 800));
		JPanel canvas = new JPanel(null);
		ArrayList<CardController> accusationCards = gc.getAccusationCards();

		JLabel label = new JLabel("Time to make an accusation:");
		label.setFont(label.getFont().deriveFont(25.0f));
		label.setBounds(210, 10, 400, 50);

		accusationsButGroup = new ButtonGroup();
		for(int i = 0; i < (accusationCards.size()/3); i++) { //for number of accusations
			int num = i+1;
			JLabel textLabel = new JLabel("Accusation " + num + ":");
			//accusation (room, character and weapon card)
			CardView cv1 = accusationCards.get(i).getView();
			cv1.setBounds(110, 70+(i*200), 200, 50);
			CardView cv2 = accusationCards.get(i+1).getView();
			cv2.setBounds(220, 70+(i*200), 200, 50);
			CardView cv3 = accusationCards.get(i+2).getView();
			cv3.setBounds(330, 70+(i*200), 200, 50);

			JRadioButton jBut = new JRadioButton();
			jBut.setActionCommand(Integer.toString(i));
			textLabel.setBounds(10, 60+(i*200), 200, 50);
			jBut.setBounds(10, 100+(i*200), 200, 50);
			canvas.add(textLabel);
			canvas.add(jBut);
			canvas.add(cv1);
			canvas.add(cv2);
			canvas.add(cv3);
			accusationsButGroup.add(jBut);
		}

		accusationConfirmButton = new JButton(" confirm ");    //Button
		accusationConfirmButton.setBounds(325, 700, 150, 50);

		canvas.add(label);
		canvas.add(accusationConfirmButton);
		chooseAccusationScreen.setContentPane(canvas);
	}

	/**
	 * Show options.
	 */
	public void showOptions() {
		String text = "Click the Dice to roll it!\n"
				+ "-------------------------------\n"
				+ "Move using WASD";
		JOptionPane.showMessageDialog(gameScreen, text);
	}

	/**
	 * public void addPlayerView(PlayerView p) {
	 * 		players.add(p);
	 * 	}*
	 *
	 * @return the game over screen
	 */

	public JFrame getGameOverScreen() {return gameOverScreen;}

	/**
	 * Gets the make suggestion screen.
	 *
	 * @return the make suggestion screen
	 */
	//for calling screens to the game controller
	public JFrame getMakeSuggestionScreen() { return  makeSuggestionScreen; }
	
	/**
	 * Gets the accusation inquiry screen.
	 *
	 * @return the accusation inquiry screen
	 */
	public JFrame getAccusationInquiryScreen() { return accusationInquiryScreen; }
	
	/**
	 * Gets the no refute card found screen.
	 *
	 * @return the no refute card found screen
	 */
	public JFrame getNoRefuteCardFoundScreen() { return noRefuteCardFoundScreen; }
	
	/**
	 * Gets the refute card found screen.
	 *
	 * @return the refute card found screen
	 */
	public JFrame getRefuteCardFoundScreen() { return refuteCardFoundScreen; }
	
	/**
	 * Gets the choose accusation screen.
	 *
	 * @return the choose accusation screen
	 */
	public JFrame getChooseAccusationScreen() {return chooseAccusationScreen;}

	/**
	 * Gets the char but group.
	 *
	 * @return the char but group
	 */
	//suggestion screen components
	public ButtonGroup getCharButGroup() { return charButGroup; }
	
	/**
	 * Gets the weap but group.
	 *
	 * @return the weap but group
	 */
	public ButtonGroup getWeapButGroup() { return weapButGroup; }
	
	/**
	 * Gets the suggest confirm button.
	 *
	 * @return the suggest confirm button
	 */
	public JButton getSuggestConfirmButton() { return suggestConfirmButton;}

	/**
	 * Gets the accusation inquiry yes button.
	 *
	 * @return the accusation inquiry yes button
	 */
	//accusation inquiry screen components
	public JButton getAccusationInquiryYesButton() {return accusationInquiryYesButton;}
	
	/**
	 * Gets the accusation inquiry no button.
	 *
	 * @return the accusation inquiry no button
	 */
	public JButton getAccusationInquiryNoButton() {return accusationInquiryNoButton;}

	/**
	 * Gets the accusations but group.
	 *
	 * @return the accusations but group
	 */
	//choose accusation screen components
	public ButtonGroup getAccusationsButGroup() {return accusationsButGroup;}
	
	/**
	 * Gets the accusation confirm button.
	 *
	 * @return the accusation confirm button
	 */
	public JButton getAccusationConfirmButton() {return accusationConfirmButton;}

	/**
	 * Gets the no refute back button.
	 *
	 * @return the no refute back button
	 */
	//no refute card found components
	public JButton getNoRefuteBackButton() { return noRefuteBackButton; }
	
	/**
	 * Gets the refute back button.
	 *
	 * @return the refute back button
	 */
	public JButton getRefuteBackButton() { return refuteBackButton; }
	
	/**
	 * Sets the refute card.
	 *
	 * @param card the new refute card
	 */
	public void setRefuteCard(JLabel card) { this.refuteCard = card; }

	/**
	 * Gets the roll dice button.
	 *
	 * @return the roll dice button
	 */
	public JButton getRollDiceButton() {
		return rollDiceButton;
	}

	/**
	 * Gets the board view.
	 *
	 * @return the board view
	 */
	public BoardView getBoardView() {
		return boardView;
	}

	/**
	 * Gets the JLabel for the first dice
	 * @return first dice label
	 */
	public JLabel getFirstDice() {
		return firstDice;
	}

	/**
	 * Gets the JLabel for the second dice
	 * @return second dice label
	 */
	public JLabel getSecondDice() {
		return secondDice;
	}

	/**
	 * Gets the list of images for the dice
	 * @return
	 */
	public List<ImageIcon> getDiceImages() {
		return diceImages;
	}

	/**
	 * Animate dice.
	 *
	 * @param diceValues the dice values
	 */
	public void animateDice(int[] diceValues) {
		//to add later small animation for rolling dice
		//draw final images
		firstDice.setIcon(diceImages.get(diceValues[0]-1));
		secondDice.setIcon(diceImages.get(diceValues[1]-1));
	}


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new MainMenuController();
	}
}
