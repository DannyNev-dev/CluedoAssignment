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
	List<CardView> cards;
	
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
        setLayout(new GridLayout(4, 2));
        add(new JLabel(player.getName()+"'s hand:"));
        //add(new JLabel());
        for(int i = 0; i < player.getHand().size(); i++) {
            add(new CardView(player.getHand().get(i).getName(true)));
        }
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
