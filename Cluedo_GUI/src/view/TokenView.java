package view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Victoria
 * The Class TokenView.
 */
public class TokenView extends JLabel {
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6790663442931777292L;
	
	/** The token picture. */
	//attributes
    private ImageIcon tokenPicture;

    /**
     * Instantiates a new token view.
     *
     * @param imageFile the image file
     */
    public TokenView(Image imageFile) {
        super();
        //set image
        tokenPicture = new ImageIcon(imageFile);
        setIcon(tokenPicture);
    }

}
