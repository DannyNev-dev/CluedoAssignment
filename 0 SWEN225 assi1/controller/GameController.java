package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.Card;
import model.Player;
import view.CardView;
import view.GameView;
import model.Game;
import view.PlayerView;

public class GameController {
	//create model and view for the game, handle inputs and communication relating to the game
	GameView gv;
	Game gm;	//Model component of game

	BoardController boardController;	//board of the game
	ArrayList<CardController> cards;	//cards in the game

	PlayerView currentPlayer;

	ArrayList<CardView> weaponCards;
	ArrayList<CardView> characterCards;
	ArrayList<CardView> roomCards;

	public GameController(int playerNumber) {
		try {
			gm = new Game(playerNumber);
		} catch(IOException e) {
			System.out.println("ERROR: MAP FILE NOT FOUND");
		}

		initializeCards(); //not implemented, need to make sure model game is initialized prior to view related calls

		List<PlayerView> players = new ArrayList<PlayerView>();

		//create players
		for(int i = 0; i < gm.PLAYERS.length; i++) {
			if(gm.PLAYERS[i].getIsActive()) {	//check if active player
				players.add(new PlayerView(gm.PLAYERS[i]));
			}
		}

		//add boardController and BoardView to game
		boardController = new BoardController(gm.getBoard());
		this.gv = new GameView(boardController.view, players);

		addMenuActionListeners();
		//JFrame j = gv.suggestionView(); 		//for testing the suggestion screen
		//j.setVisible(true);
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
		for(int i = 0; i < gm.getCards().size(); i++) {
			cards.add(new CardController(gm.getCard(i)));

		}
	}

	//get all the char cards as components
	public ArrayList<CardView> getCharCards() {
		return this.characterCards;
	}
	public ArrayList<CardView> getWeaponCards() {
		// TODO Auto-generated method stub
		return this.weaponCards;
	}
	public ArrayList<CardView> getRoomCards(){
		return this.roomCards;
	}

}
