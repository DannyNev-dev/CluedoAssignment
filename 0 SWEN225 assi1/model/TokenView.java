package view;

import javax.swing.*;
import java.awt.*;

public class TokenView extends JPanel {
    //attributes
    private JLabel label;
    private ImageIcon tokenPicture;

    public TokenView(Image imageFile) {
        super();
        //set image
        tokenPicture = new ImageIcon(imageFile);
        label = new JLabel();
        label.setIcon(tokenPicture);

        add(label);  //add JLabel for image
    }

}
