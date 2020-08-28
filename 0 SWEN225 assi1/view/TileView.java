package view;

import javax.swing.*;
import java.awt.*;

public class TileView extends JLayeredPane {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8666965276708185159L;
	//attributes
    private ImageIcon tilePicture;
    //text that appears when mouse hovers above it
    private String tileToolTipText;
    private String tokenToolTipText = "";

    JLabel tile = new JLabel();
    JLabel token;

    public TileView(Image imageFile, String text) {
        //set image
        tilePicture = new ImageIcon(imageFile);
        tile.setIcon(tilePicture);
        //set tool tip
        tileToolTipText = text;
        setToolTipText(tileToolTipText+tokenToolTipText);

        //set tile
        tile.setBounds(0, 0, 32, 32);

        //add components
        add(tile, 1);

        //set how to draw it
        setBorder(null);
        setSize(32, 32);
    }

    public void setTokenToolTipText(String tokenToolTipText) {
        this.tokenToolTipText = tokenToolTipText;
        setToolTipText(tileToolTipText+"\n"+tokenToolTipText);
    }

    public void setToken(TokenView token) {
        this.token = token;
        if(token != null) {
            token.setBounds(0, 0, 32, 32);
            add(token, 0);
        } else {
            remove(token);
        }
    }
}
