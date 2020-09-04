package view;

import controller.TokenController;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Victoria
 * Panel that contains players name, cards and the dice
 * draw player's deck.
 */
public class PlayerView extends JPanel {

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1280946548453444722L;
	
	/** The cards. */
	JPanel cards;
	
	/** The model. */
	Player model;
	
	/** The token. */
	TokenController token;

    /**
     * Instantiates a new player view.
     *
     * @param player the player
     */
    public PlayerView(Player player){
        super();
        this.model = player;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        cards = new JPanel();
        cards.setLayout(new GridLayout(4, 2));
        cards.add(new JLabel(player.getName()+"'s hand:"));
        cards.add(new JLabel());    //add extra space for the first row
        for(int i = 0; i < player.getHand().size(); i++) {
            cards.add(new CardView(player.getHand().get(i).getName(true)));
        }
        add(cards);
    }

    /**
     * Gets the token.
     *
     * @return the token
     */
    public TokenController getToken() { return token; }

    /**
     * Gets the model.
     *
     * @return the model
     */
    public Player getModel() {
        return model;
    }
}
