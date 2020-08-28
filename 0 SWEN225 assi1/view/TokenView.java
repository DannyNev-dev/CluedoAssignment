package view;

import javax.swing.*;
import java.awt.*;

public class TokenView extends JLabel {
    //attributes
    private ImageIcon tokenPicture;

    public TokenView(Image imageFile) {
        super();
        //set image
        tokenPicture = new ImageIcon(imageFile);
        setIcon(tokenPicture);
    }

}
