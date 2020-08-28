package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * @author Daniel
 *
 */
public class CardView extends JLabel{

    /**
     *
     */
    private static final long serialVersionUID = -3425093654815422748L;

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
        if(image!=null) {setIcon(new ImageIcon(image));}
    }

}
