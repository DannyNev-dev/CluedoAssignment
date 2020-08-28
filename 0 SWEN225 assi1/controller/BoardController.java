package controller;

import model.Board;
import view.BoardView;

public class BoardController {
    //attributes
    BoardView view;
    Board model;

    TileController tiles[][];

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
}
