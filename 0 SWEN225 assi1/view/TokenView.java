package view;

import javax.swing.*;
import java.awt.*;

public class TokenView extends JLabel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6790663442931777292L;
	//attributes
    private ImageIcon tokenPicture;

    public TokenView(Image imageFile) {
        super();
        //set image
        tokenPicture = new ImageIcon(imageFile);
        setIcon(tokenPicture);
    }

}
