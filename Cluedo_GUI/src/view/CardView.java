package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * @author Daniel
 * The Class CardView.
 * Handles creating a viewable card class from a model card
 * 
 */
public class CardView extends JLabel{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3425093654815422748L;

    /**
     * Instantiates a new card view.
     *
     * @param s the s
     */
    public CardView(String s) {
        super(s); // gives the parent a name for this component
        String path = s + ".png";	//not sure if this will work
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(image!=null) {setIcon(new ImageIcon(image.getScaledInstance(70, 100, Image.SCALE_SMOOTH)));}
    }

}
