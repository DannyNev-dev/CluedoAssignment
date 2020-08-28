package view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Daniel,Ketaki,Victoria
 * this class handles drawing each tile inside the board class
 *
 */
public class BoardView extends JPanel {

    TileView[][] tiles;

    public BoardView() {
        tiles = new TileView[25][24];
        setLayout(new GridLayout(25, 24));
    }

    public void addTile(int y, int x, TileView v) {
        tiles[y][x] = v;
        add(v);
    }
}
