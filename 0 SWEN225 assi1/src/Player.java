/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5099.60569f335 modeling language!*/


import java.awt.Point;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * Represents a character/player in the game
 */
// line 86 "model.ump"
// line 170 "model.ump"
public class Player extends Token
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes

  private Point location;     //current location of player on the board
  private char character;     //character that represents this player
  private boolean isActive;   //checks if the character will be played by a player in this game
  private boolean canWin;     //checks that the player is allowed to make suggestion and accusations
  //private List<Card> hand;

  //Player Associations
  private List<Card> hand;    //hand of cards that each player has

  //------------------------
  // CONSTRUCTOR
  //------------------------
  public Player(Point aLocation, char aCharacter)
  {
    super();
    location = aLocation;
   // isActive = isActive;
    character = aCharacter;
    //hand = new ArrayList<Card>();
    hand = new ArrayList<Card>();
  }

  /**
   * returns the char symbol of the character that this player represents
   * @return
   */
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
      inputChar = s.next().charAt(0);
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
    inputChar = board.getGrid()[(int)this.location.getX()][(int)this.location.getY()].getUnderlyingSymbol();
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
    if(board.getGrid()[(int)charLoc.getX()][(int)charLoc.getY()].getUnderlyingSymbol() != inputChar) {
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

  /**
   * let's this player chose an accusation
   * @param accusations
   * @param solution
   * @return  true if accusation was correct. false if not
   */
  public int makeAccusation(List<Card[]> accusations, List<Card> solution){
    //print accusations
    System.out.println("Accusations are: ");
    for(int i = 0; i < accusations.size(); i++) {
      Card[] accusation = accusations.get(i);
      System.out.print("\t");
      for(Card card : accusation) {  //print out all the parts of the accusation
        System.out.print(card.getName()+" ");
      }
      System.out.println("["+i+"]");
    }
    System.out.println();
    //ask for which accusation
    Scanner s;
    int indexChosen = -1;
    while(indexChosen > accusations.size() || indexChosen < 0) {
      System.out.println("Which accusation do you choose? Please choose index of accusation");
      s = new Scanner(System.in);
      String str = s.next();

      try {
        indexChosen = parseInt(str);
      } catch(NumberFormatException e) {
        System.out.println("ERROR: invalid character Please type down a character");
      }
    }
    //get accusation from list of accusations in game
    //check that accusations exist
    if(!accusations.isEmpty()) {
      Card[] chosenAccusation = accusations.get(indexChosen);
      //check is accusation is correct
      for (int i = 0; i < chosenAccusation.length; i++) {
        if (!chosenAccusation[i].getName().equals(solution.get(i).getName())) {      //if not
          System.out.println("Accusation was incorrect!");
          this.canWin = false;  //now this player cannot win in this game
          return 0;
        }
      }
      System.out.println("Accusation was correct!");  //if so
      return 1;
    }
    System.out.println("No accusations!");
    return 2;
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
  public boolean getCanWin() {return this.canWin;}

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

  public Card getCard(int index)
  {
    Card aCard = hand.get(index);
    return aCard;
  }

  public List<Card> getHand()
  {
    List<Card> newCards = Collections.unmodifiableList(hand);
    return newCards;
  }

  public int numberOfCards()
  {
    int number = hand.size();
    return number;
  }

  public boolean hasCards()
  {
    boolean has = hand.size() > 0;
    return has;
  }

  public int indexOfCard(Card aCard)
  {
    int index = hand.indexOf(aCard);
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
    if (hand.contains(aCard)) { return false; }
    hand.add(aCard);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCard(Card aCard)
  {
    boolean wasRemoved = false;
    if (hand.contains(aCard))
    {
      hand.remove(aCard);
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
      hand.remove(aCard);
      hand.add(index, aCard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCardAt(Card aCard, int index)
  {
    boolean wasAdded = false;
    if(hand.contains(aCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCards()) { index = numberOfCards() - 1; }
      hand.remove(aCard);
      hand.add(index, aCard);
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
    hand.clear();
    super.delete();
  }

  // line 94 "model.ump"
   public String toString(){
	return String.valueOf(character);
  }
}