package controller;

import model.Board;
import view.BoardView;

/**
 * @author Victoria
 * The Class BoardController.
 * Handles getting the model boards tiles and converting them to the viewable class type to be drawn
 */
public class BoardController {
    
    /** The view. */
    //attributes
    BoardView view;
    
    /** The model. */
    Board model;

    /** The tiles. */
    TileController tiles[][];

    /**
     * Instantiates a new board controller.
     *
     * @param aModel the a model
     */
    @SuppressWarnings("static-access")
	BoardController(Board aModel) {
        model = aModel;
        view = new BoardView();

        tiles = new TileController[model.HEIGHT][model.WIDTH];

        //add the tiles' and tokens' data into boardView and tileControllers
        //int tokenCounter = 0;
        for(int i = 0; i < model.HEIGHT; i++) {
            for (int j = 0; j < model.WIDTH; j++) {
                //adding tile data
                tiles[i][j] = new TileController(model.getTileAt(j, i));
                view.addTile(i, j, tiles[i][j].getTileView());
            }
        }
    }

    /**
     * Updates the viewer of the board based on the current state of the model.
     */
    public void update() {
        //add the tiles' and tokens' data into boardView and tileControllers
       view.clear();    //clear old board
        for(int i = 0; i < model.HEIGHT; i++) { //redraw board
            for (int j = 0; j < model.WIDTH; j++) {
                //adding tile data
                tiles[i][j] = new TileController(model.getTileAt(j, i));
                view.addTile(i, j, tiles[i][j].getTileView());
            }
        }
    }
}
