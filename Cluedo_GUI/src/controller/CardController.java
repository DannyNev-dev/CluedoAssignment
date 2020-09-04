package controller;

import model.Card;
import view.CardView;

/**
 * @author Daniel
 * 
 * Links the card viewer and card model classes together.
 */
public class CardController {
    
    /** The model. */
    Card model;
    
    /** The view. */
    CardView view;

    /**
     * Gets the model.
     *
     * @return the model
     */
    public Card getModel() {
		return model;
	}

	/**
	 * Gets the view.
	 *
	 * @return the view
	 */
	public CardView getView() {
		return view;
	}

	/**
	 * Instantiates a new card controller.
	 *
	 * @param aModel the a model
	 */
	public CardController(Card aModel) {
        this.model = aModel;
        view = new CardView(model.getName(true));
    }

}
