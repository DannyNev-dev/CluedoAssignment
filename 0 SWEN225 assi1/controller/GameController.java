package controller;

import view.GameView;

public class GameController {
	//create model and view for the game, handle inputs and communication relating to the game
	GameView gv;
	public GameController(int playerNumber) {
		this.gv = new GameView();
	}

}
