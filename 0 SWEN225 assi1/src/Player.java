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

  // line 95 "model.ump"

  /**
   * if player wants to make a suggestion and can do it, this function is called
   * @param board for getting the weapons and char
   * @return
   */
  public Card[] makeSuggestion(Board board, Game game){
    //let player choose one character card and one weapon card via strings
    Player characterSelected = null;
    String characterName = "";
    String weaponName = "";
    String roomName = "";
    Weapon weaponSelected = null;
    boolean selectedValid = false;  //checking is typed in character is valid
    Scanner s = new Scanner(System.in);
    //select character
    char inputChar = '\0';
    while(!selectedValid) {
      System.out.println("Select a character: ");
      System.out.println("Miss Scarlet: s");
      System.out.println("Colonel Mustard: m");
      System.out.println("Mrs White: w");
      System.out.println("Mr Green: g");
      System.out.println("Mrs Peacock: k");
      System.out.println("Professor Plum: p");
      inputChar = s.next().charAt(0);
      switch(inputChar) {
        case 's':
          characterName = "Miss Scarlet";
          selectedValid = true;
          break;
        case 'm':
          characterName = "Colonel Mustard";
          selectedValid = true;
          break;
        case 'w':
          characterName = "Mrs White";
          selectedValid = true;
          break;
        case 'g':
          characterName = "Mr Green";
          selectedValid = true;
          break;
        case 'k':
          characterName = "Mrs Peacock";
          selectedValid = true;
          break;
        case 'p':
          characterName = "Professor Plum";
          selectedValid = true;
          break;
        default:
          System.out.println("first char in string is not a valid character");
          break;
      }
    }
    Card charCard = new Card(characterName);  //create character card
    Point charLoc = board.searchFor(inputChar); //find character on board, for moving the char

    //select weapon
    selectedValid = false;  //checking is typed in character is valid
    inputChar = '\0';
    while(!selectedValid) {
      System.out.println("Select a weapon: ");
      System.out.println("Candlestick: c");
      System.out.println("Dagger: d");
      System.out.println("Lead Pipe: l");
      System.out.println("Revolver: r");
      System.out.println("Rope: o");
      System.out.println("Spanner: a");
      switch (inputChar) {
        case 'c':
          weaponName = "candlestick";
          selectedValid = true;
          break;
        case 'd':
          weaponName = "dagger";
          selectedValid = true;
          break;
        case 'l':
          weaponName = "lead pipe";
          selectedValid = true;
          break;
        case 'r':
          weaponName = "revolver";
          selectedValid = true;
          break;
        case 'o':
          weaponName = "rope";
          selectedValid = true;
          break;
        case 'a':
          weaponName = "spanner";
          selectedValid = true;
          break;
        default:
          System.out.println("first char in string is not a valid character");
          break;
      }
    }
    Card weaponCard = new Card(weaponName);  //create weapon card
    Point weaponLoc = board.searchFor(inputChar); //find weapon on board, for moving the weapon

    //get room suggestion took place in
    //get symbol of tile from player
    inputChar = board.getGrid()[(int)this.location.getY()][(int)this.location.getX()].getUnderlyingSymbol();
    switch (inputChar) {
      case 'K':
        roomName = "kitchen";
        break;
      case 'B':
        roomName = "ballroom";
        break;
      case 'C':
        roomName = "conservatory";
        break;
      case 'G':
        roomName = "billard room";
        break;
      case 'Y':
        roomName = "library";
        break;
      case 'S':
        roomName = "study";
        break;
      case 'H':
        roomName = "hall";
        break;
      case 'L':
        roomName = "lounge";
        break;
      case 'D':
        roomName = "dining room";
        break;
    }
    Card roomCard = new Card(roomName); //create room card

    //move character to room that player is in
    //check if character is not in room already
    if(board.getGrid()[(int)charLoc.getY()][(int)charLoc.getX()].getUnderlyingSymbol() != inputChar) {
      Point moveTo = board.searchFor(inputChar);  //place character in first available spot
      game.teleport(board.getGrid()[(int)charLoc.getY()][(int)charLoc.getX()].getToken(), charLoc, moveTo);
    }

    //move weapon to room that player is in
    //check if weapon is not in room already
    if(board.getGrid()[(int)weaponLoc.getY()][(int)weaponLoc.getX()].getUnderlyingSymbol() != inputChar) {
      Point moveTo = board.searchFor(inputChar);  //place character in first available spot
      game.teleport(board.getGrid()[(int)weaponLoc.getY()][(int)weaponLoc.getX()].getToken(), charLoc, moveTo);
    }

    Card[] suggestion = {charCard, weaponCard, roomCard};
    return suggestion;
  }

  // line 96 "model.ump"
  public void makeAccusation(int i){
    //ask for accusation
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
}