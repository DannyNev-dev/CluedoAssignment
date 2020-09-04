package view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Victoria
 * The Class TileView.
 * 
 */
public class TileView extends JLayeredPane {
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8666965276708185159L;
	
	/** The tile picture. */
	//attributes
    private ImageIcon tilePicture;
    
    /** The tile tool tip text. */
    //text that appears when mouse hovers above it
    private String tileToolTipText;
    
    /** The token tool tip text. */
    private String tokenToolTipText = "";

    /** The tile. */
    JLabel tile = new JLabel();
    
    /** The token. */
    JLabel token;

    /**
     * Instantiates a new tile view.
     *
     * @param imageFile the image file
     * @param text the text
     */
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

    /**
     * Sets the token tool tip text.
     *
     * @param tokenToolTipText the new token tool tip text
     */
    public void setTokenToolTipText(String tokenToolTipText) {
        this.tokenToolTipText = tokenToolTipText;
        setToolTipText(tileToolTipText+"\n"+tokenToolTipText);
    }

    /**
     * Sets the token.
     *
     * @param token the new token
     */
    public void setToken(TokenView token) {
        this.token = token;
        if(this.token != null) {
            this.token.setBounds(0, 0, 32, 32);
            add(this.token, 0);
        } else {
            ;
        }
    }
}
