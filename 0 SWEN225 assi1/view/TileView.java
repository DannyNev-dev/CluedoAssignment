package view;

import javax.swing.*;
import java.awt.*;

public class TileView extends JPanel {
    //attributes
    private JLabel label;
    private ImageIcon tilePicture;
    private String tileToolTipText;
    private String tokenToolTipText = "";

    public TileView(Image imageFile, String text) {
        super();
        //set image
        tilePicture = new ImageIcon(imageFile);
        label = new JLabel();
        label.setIcon(tilePicture);
        //set tool tip
        tileToolTipText = text;
        label.setToolTipText(tileToolTipText+tokenToolTipText);

        add(label);  //add JLabel for image
    }

    public void setTokenToolTipText(String tokenToolTipText) {
        this.tokenToolTipText = tokenToolTipText;
        label.setToolTipText(tileToolTipText+tokenToolTipText);
    }
}
