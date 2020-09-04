package view;

import controller.TokenController;
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
	JPanel cards;
	//List<CardView> cards;
	Player model;
	TokenController token;

    public PlayerView(Player player){
        super();
        this.model = player;
        this.setLayout(new FlowLayout());
        cards = new JPanel();
        cards.setLayout(new GridLayout(4, 2));
        cards.add(new JLabel(player.getName()+"'s hand:"));
        cards.add(new JLabel());
        for(int i = 0; i < player.getHand().size(); i++) {
            cards.add(new CardView(player.getHand().get(i).getName(true)));
        }
        this.add(cards);
    }

    public TokenController getToken() { return token; }

    public Player getModel() {
        return model;
    }
}
