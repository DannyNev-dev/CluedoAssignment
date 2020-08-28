package controller;

import model.Card;
import view.CardView;

public class CardController {
    Card model;
    CardView view;

    public CardController(Card aModel) {
        this.model = aModel;
        view = new CardView(model.getName());
    }

}
