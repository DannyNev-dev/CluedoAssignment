/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5099.60569f335 modeling language!*/


import java.awt.Point;
import java.util.*;

// line 86 "model.ump"
// line 170 "model.ump"
public class Player extends Token
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes

  private Point location;
  private char character;
  private boolean isActive;
  private boolean canWin;
  private List<Card> hand;

  //Player Associations
  private List<Card> cards;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  public Player(Point aLocation, char aCharacter)
  {
    super();
    location = aLocation;
   // isActive = isActive;
    character = aCharacter;
    hand = new ArrayList<Card>();
    cards = new ArrayList<Card>();
  }

  public Player(Point aLocation, boolean isActive, char aCharacter, boolean canWin)
  {
    super();
    location = aLocation;
    this.isActive = isActive;
    character = aCharacter;
    this.canWin = canWin;
    hand = new ArrayList<Card>();
    cards = new ArrayList<Card>();
  }

  public char getSymbol() {
    return character;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLocation(Point aLocation)
  {
    boolean wasSet = false;
    location = aLocation;
    wasSet = true;
    return wasSet;
  }

  public void setIsActive(boolean isActive)
  {
   // boolean wasSet = false;
    this.isActive = isActive;
    //wasSet = true;
   // return wasSet;
  }
  
  public void setCanWin(boolean canWin)
  {
    this.canWin = canWin;
  }

  public boolean setCharacter(char aCharacter)
  {
    boolean wasSet = false;
    character = aCharacter;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetMany */
  public boolean addHand(Card aHand)
  {
    boolean wasAdded = false;
    wasAdded = hand.add(aHand);
    return wasAdded;
  }

  public boolean removeHand(Card aHand)
  {
    boolean wasRemoved = false;
    wasRemoved = hand.remove(aHand);
    return wasRemoved;
  }

  public Point getLocation()
  {
    return location;
  }

  public boolean getIsActive()
  {
    return isActive;
  }

  public char getCharacter()
  {
    return character;
  }
  /* Code from template attribute_GetMany */
  public Card getHand(int index)
  {
    Card aHand = hand.get(index);
    return aHand;
  }

  public Card[] getHand()
  {
    Card[] newHand = hand.toArray(new Card[hand.size()]);
    return newHand;
  }

  public int numberOfHand()
  {
    int number = hand.size();
    return number;
  }

  public boolean hasHand()
  {
    boolean has = hand.size() > 0;
    return has;
  }

  public int indexOfHand(Card aHand)
  {
    int index = hand.indexOf(aHand);
    return index;
  }
  /* Code from template association_GetMany */
  public Card getCard(int index)
  {
    Card aCard = cards.get(index);
    return aCard;
  }

  public List<Card> getCards()
  {
    List<Card> newCards = Collections.unmodifiableList(cards);
    return newCards;
  }

  public int numberOfCards()
  {
    int number = cards.size();
    return number;
  }

  public boolean hasCards()
  {
    boolean has = cards.size() > 0;
    return has;
  }

  public int indexOfCard(Card aCard)
  {
    int index = cards.indexOf(aCard);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCards()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addCard(Card aCard)
  {
    boolean wasAdded = false;
    if (cards.contains(aCard)) { return false; }
    cards.add(aCard);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCard(Card aCard)
  {
    boolean wasRemoved = false;
    if (cards.contains(aCard))
    {
      cards.remove(aCard);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCardAt(Card aCard, int index)
  {  
    boolean wasAdded = false;
    if(addCard(aCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCards()) { index = numberOfCards() - 1; }
      cards.remove(aCard);
      cards.add(index, aCard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCardAt(Card aCard, int index)
  {
    boolean wasAdded = false;
    if(cards.contains(aCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCards()) { index = numberOfCards() - 1; }
      cards.remove(aCard);
      cards.add(index, aCard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCardAt(aCard, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    cards.clear();
    super.delete();
  }

  // line 94 "model.ump"
   public String toString(){
	return String.valueOf(character);
    
  }

  // line 95 "model.ump"
   public void makeSuggestion(){
    
  }

  // line 96 "model.ump"
   public void makeAccusation(){
    
  }

}