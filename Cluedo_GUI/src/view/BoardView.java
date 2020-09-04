package view;

import javax.swing.*;
import java.awt.*;

/**
 * The Class BoardView.
 *
 * @author Daniel,Ketaki,Victoria
 * 
 * this class handles drawing each tile inside the board class and is a panel to be drawn
 */
public class BoardView extends JPanel {

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7422285909447233127L;
	
	/** The tiles. */
	TileView[][] tiles;

    /**
     * Instantiates a new board view.
     */
    public BoardView() {
        tiles = new TileView[25][24];
        setLayout(new GridLayout(25, 24));
    }

    /**
     * clears out the Jpanel from the old tiles.
     */
    public void clear() {
        for(int i = 0; i < 25; i++) {
            for(int j = 0; j < 24; j++) {
                remove(tiles[i][j]);
            }
        }
    }

    /**
     * Adds the tile.
     *
     * @param y the y
     * @param x the x
     * @param v the v
     */
    public void addTile(int y, int x, TileView v) {
        tiles[y][x] = v;
        add(v);
    }
}
