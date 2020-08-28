package view;

import model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * draw player's deck
 */
public class PlayerView extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1280946548453444722L;
	List<CardView> cards;

    public PlayerView(Player player){
        super();
        setLayout(new GridLayout(4, 2));
        add(new JLabel(player.getName()+"'s hand:"));
        add(new JLabel());
        for(int i = 0; i < player.getHand().size(); i++) {
            add(new CardView(player.getHand().get(i).getName()));
        }
    }
}
