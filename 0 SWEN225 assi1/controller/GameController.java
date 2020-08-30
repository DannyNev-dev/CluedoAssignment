package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import model.Card;
import model.Player;
import view.CardView;
import view.GameView;
import model.Game;
import view.PlayerView;

public class GameController {
	//create model and view for the game, handle inputs and communication relating to the game
	GameView gv;	//View component of game
	Game gm;	//Model component of game

	BoardController boardController;	//board of the game
	ArrayList<CardController> cards;	//cards in the game

	boolean currentlyMoving = false;	//for checking  if in move player state

	PlayerView currentPlayer;
	//for move state
	int movesLeft = -1;	//moves left for current player
	List<Point> moveLog = new ArrayList<>();

	//for make suggestion state
	String selectedCharCard = null;
	String selectedWeapCard = null;

	Card[] possibleAccusation = null;	//possible accusation from make suggestions state

	@SuppressWarnings("static-access")
	public GameController(int playerNumber) {
		try {
			gm = new Game(playerNumber);
		} catch(IOException e) {
			System.out.println("ERROR: MAP FILE NOT FOUND");
		}
		
		cards = new ArrayList<CardController>();
		initializeCards(); //not implemented, need to make sure model game is initialized prior to view related calls

		List<PlayerView> players = new ArrayList<PlayerView>();

		//create players
		for(int i = 0; i < gm.PLAYERS.length; i++) {
			if(gm.PLAYERS[i].getIsActive()) {	//check if active player
				players.add(new PlayerView(gm.PLAYERS[i]));
			}
		}

		currentPlayer = players.get(0);	//get first player

		//add boardController and BoardView to game
		boardController = new BoardController(gm.getBoard());
		this.gv = new GameView(boardController.view, currentPlayer, this);

		addMenuActionListeners();
		addKeyListeners();

		gv.getRollDiceButton().addActionListener(e -> {rollDice();});		//add roll Dice button listener

		gv.suggestionView();	//create suggestion screen for later
		gv.accusationInquiryView();	//create accusation inquiry screen for later
		gv.noRefuteCardFoundScreen();	//create no refute card found screen for later
		gv.refuteCardFoundScreen();		//create refute card found screen for later
		//add the listeners
		addSuggestScreenListeners();
		addNoRefuteFoundListeners();
		addRefuteFoundListeners();
		addNoRefuteFoundListeners();

		//start game
		System.out.println("start game");
	}

	private void rollDice() {
		int[] diceNumbers = gm.rollDice();
		gv.animateDice(diceNumbers);
		gameTurn(diceNumbers); //now go to moving state
	}

	private void gameTurn(int[] diceNumbers) {
		if(currentPlayer.getModel().getCanWin()) {	//if player can win then they can do a turn
			gv.getRollDiceButton().setEnabled(false);	//disable rollDice button
			movesLeft = diceNumbers[0]+diceNumbers[1];	//get number of moves
			System.out.println("movesLeft = "+movesLeft);
			currentlyMoving = true;	//change state of game
		}
	}

	/**
	 * key listeners for the game screen
	 */
	private void addKeyListeners() {
		//add keyListener
		gv.getGameScreen().setFocusable(true);
		gv.getGameScreen().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override	//checking for WASD
			public void keyPressed(KeyEvent e) {
				boolean doMove = true;
				char charPressed = ' ';
				switch(e.getKeyCode()) {
					case KeyEvent.VK_W:	//up
						charPressed = 'w';
						break;
					case KeyEvent.VK_A:	//left
						charPressed = 'a';
						break;
					case KeyEvent.VK_S:
						charPressed = 's';
						break;
					case KeyEvent.VK_D:
						charPressed = 'd';
						break;
					default:
						doMove = false;
						System.out.println("Invalid move");
				}
				if(doMove && (currentlyMoving)) {
					System.out.println("movesLeft = "+movesLeft);
					int y = currentPlayer.getY();
					int x =  currentPlayer.getX();
					int moveValue = gm.move(currentPlayer.getModel(), charPressed, moveLog);
					boardController.update();	//get the updated view of the board based from the model
					if(moveValue == 0) {
						movesLeft--;
					}
					//repaint board
					gv.getBoardView().repaint();
					gv.getBoardView().revalidate();

					//check if ran out of moves
					if(movesLeft == 0) {
						System.out.println("ran out of moves");
						currentlyMoving = false;	//exit moving state
						gv.getGameScreen().setFocusable(false);	//unfocus game screen don't need it for now
						if(gm.checkIfRoom(currentPlayer.getModel())) {//check if in room
							//if so change to suggestion state
							gv.getMakeSuggestionScreen().setVisible(true);
							gv.getMakeSuggestionScreen().setFocusable(true);
						} else {	//if not got the accusation inquiry state
							gv.getAccusationInquiryScreen().setVisible(true);
							gv.getAccusationInquiryScreen().setFocusable(true);
						}
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
	}

	/**
	 * key listeners for the make suggestion screen
	 */
	private void addSuggestScreenListeners() {
		//add radioButton listeners
		Enumeration<AbstractButton> iterator = gv.getCharButGroup().getElements();
		while(iterator.hasMoreElements()) {
			AbstractButton b = iterator.nextElement();
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedCharCard = b.getActionCommand();
				}
			});
		}
		iterator = gv.getWeapButGroup().getElements();
		while(iterator.hasMoreElements()) {
			AbstractButton b = iterator.nextElement();
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedWeapCard = b.getActionCommand();
				}
			});
		}
		//add confirm button listener
		gv.getSuggestConfirmButton().addActionListener(e -> {
			if(selectedCharCard != null && selectedWeapCard != null) {	//check that there is something selected for both char and weapon
				//if so move to suggestion refute state
				gv.getMakeSuggestionScreen().setFocusable(false);	//close make suggestion window
				gv.getMakeSuggestionScreen().setVisible(false);
				//create the suggestion from the selected cards
				Card[] suggestion = currentPlayer.getModel().makeSuggestion(boardController.model, gm, selectedCharCard, selectedWeapCard);
				Card refuteCard = gm.refuteSuggestion(suggestion, currentPlayer.getModel());	//compare with the other players' hands
				if(refuteCard == null) {	//if no refute found
					possibleAccusation = suggestion;	//got accusation now
					gv.getNoRefuteCardFoundScreen().setFocusable(true);	//go to no refute card found screen
					gv.getNoRefuteCardFoundScreen().setVisible(true);	//go to no refute card found screen

				} else {	//if refute found
					CardController refuteControl = new CardController(gm.refuteSuggestion(suggestion, currentPlayer.getModel()));
					gv.setRefuteCard(refuteControl.getView());	//set picture in refute found screen to refute card
					//repaint refute found frame
					gv.getRefuteCardFoundScreen().repaint();
					gv.getRefuteCardFoundScreen().revalidate();
					gv.getRefuteCardFoundScreen().setFocusable(true);	//go to no refute card found screen
					gv.getRefuteCardFoundScreen().setVisible(true);	//go to refute card found screen

				}
			}
			//if not stay in this state
		});
	}

	/**
	 * key listeners for the no refute card found screen
	 */
	private void addNoRefuteFoundListeners() {
		//back button listener
		gv.getNoRefuteBackButton().addActionListener(e -> {
			//create the accusation and add it to the game model's list of accusations
			gm.getAccusations().add(possibleAccusation);
			//close no refute card found screen
			gv.getNoRefuteCardFoundScreen().setFocusable(false);
			gv.getNoRefuteCardFoundScreen().setVisible(false);

			//go to accusation inquiry state
			gv.getAccusationInquiryScreen().setFocusable(true);
			gv.getAccusationInquiryScreen().setVisible(true);

		});
	}

	/**
	 * key listeners for the refute card found screen
	 */
	private void addRefuteFoundListeners() {
		//back button listener
		gv.getRefuteBackButton().addActionListener(e -> {
			//close no refute card found screen
			gv.getRefuteCardFoundScreen().setFocusable(false);
			gv.getRefuteCardFoundScreen().setVisible(false);

			//go to accusation inquiry state
			gv.getAccusationInquiryScreen().setFocusable(true);
			gv.getAccusationInquiryScreen().setVisible(true);

		});
	}

	private void addMenuActionListeners() {
		JMenuBar menubar1 = this.gv.getGameScreen().getJMenuBar();
		for (int i = 0; i < menubar1.getMenuCount(); i++) {
			JMenu menu1 = menubar1.getMenu(i);
			for (int j = 0; j < menu1.getMenuComponentCount(); j++) {
				java.awt.Component comp = menu1.getMenuComponent(j);
				if (comp instanceof JMenuItem) {
					JMenuItem menuItem1 = (JMenuItem) comp;
					System.out.println("MenuItem:" + menuItem1.getText());
					switch (menuItem1.getText()) {
						case "Reset":
							menuItem1.addActionListener(e -> {resetGame();});
							break;
						case "NewGame":
							menuItem1.addActionListener(e -> {newGame();});
							break;
						case "Controls":
							menuItem1.addActionListener(e -> {showControlScreen();});
							break;
					}
				}
			}
		}
	}

	private void showControlScreen() {
		this.gv.showOptions(); //tells the view to show the options dialog
	}
	// create a completely new game, exit program and re open mainmenu
	private void newGame() {
	}
	/* Reset the game with the same number of players,delete current game data and
	 create a new game with the same number of players
	 If it proves to be too difficult just leave this part out*/
	private void resetGame() {

	}
	//takes data of the cards from the model and creates 3 decks of card view objects that represent each card
	private void initializeCards() {
		//ask game for lists of cards, create view cards passing in the name of each card, add these cards to the local card lists
		//go through each card in game and create card controller
		for(int i = 0; i < gm.getCardsForViewer().size(); i++) {
			cards.add(new CardController(gm.getCardsForViewer().get(i)));
		}
	}
	//get all the char cards as components
	public ArrayList<CardController> getCharCards() {
		ArrayList<CardController> cc = new ArrayList<>();
		System.out.println("cards size in getChar = "+cards.size());
		for(CardController cardC : cards) {
			String name = cardC.model.toString();
			if(name=="Miss Scarlet"||name=="Colonel Mustard"||name=="Mrs White"||name=="Mr Green"||name=="Mrs Peacock"||name=="Professor Plum"){
				cc.add(cardC);
			}
		}
		return cc;
	}							
	public ArrayList<CardController> getWeaponCards() {
		ArrayList<CardController> cc = new ArrayList<>();
		for(CardController cardC : cards) {
			String name = cardC.model.toString();
			if(name=="candlestick"||name=="dagger"||name=="lead pipe"||name=="spanner"||name=="revolver"||name=="rope"){
				cc.add(cardC);
			}
		}
		return cc;
	}
	public ArrayList<CardView> getRoomCards(){
		return null;
	}

	public PlayerView getCurrentPlayer() {
		return currentPlayer;
	}
}
