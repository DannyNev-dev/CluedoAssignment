/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5099.60569f335 modeling language!*/


import java.util.*;

// line 67 "model.ump"
// line 162 "model.ump"
public class Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private List<Card> envelope;
  private List<String> accusations;

  //Game Associations
  private Board board;
  private List<Player> players;
  private List<Tile> tiles;
  private List<Card> cards;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(Board aBoard, Player... allPlayers)
  {
    envelope = new ArrayList<Card>();
    accusations = new ArrayList<String>();
    if (!setBoard(aBoard))
    {
      throw new RuntimeException("Unable to create Game due to aBoard. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    players = new ArrayList<Player>();
    boolean didAddPlayers = setPlayers(allPlayers);
    if (!didAddPlayers)
    {
      throw new RuntimeException("Unable to create Game, must have 3 to 6 players. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    tiles = new ArrayList<Tile>();
    cards = new ArrayList<Card>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template attribute_SetMany */
  public boolean addEnvelope(Card aEnvelope)
  {
    boolean wasAdded = false;
    wasAdded = envelope.add(aEnvelope);
    return wasAdded;
  }

  public boolean removeEnvelope(Card aEnvelope)
  {
    boolean wasRemoved = false;
    wasRemoved = envelope.remove(aEnvelope);
    return wasRemoved;
  }
  /* Code from template attribute_SetMany */
  public boolean addAccusation(String aAccusation)
  {
    boolean wasAdded = false;
    wasAdded = accusations.add(aAccusation);
    return wasAdded;
  }

  public boolean removeAccusation(String aAccusation)
  {
    boolean wasRemoved = false;
    wasRemoved = accusations.remove(aAccusation);
    return wasRemoved;
  }
  /* Code from template attribute_GetMany */
  public Card getEnvelope(int index)
  {
    Card aEnvelope = envelope.get(index);
    return aEnvelope;
  }

  public Card[] getEnvelope()
  {
    Card[] newEnvelope = envelope.toArray(new Card[envelope.size()]);
    return newEnvelope;
  }

  public int numberOfEnvelope()
  {
    int number = envelope.size();
    return number;
  }

  public boolean hasEnvelope()
  {
    boolean has = envelope.size() > 0;
    return has;
  }

  public int indexOfEnvelope(Card aEnvelope)
  {
    int index = envelope.indexOf(aEnvelope);
    return index;
  }
  /* Code from template attribute_GetMany */
  public String getAccusation(int index)
  {
    String aAccusation = accusations.get(index);
    return aAccusation;
  }

  public String[] getAccusations()
  {
    String[] newAccusations = accusations.toArray(new String[accusations.size()]);
    return newAccusations;
  }

  public int numberOfAccusations()
  {
    int number = accusations.size();
    return number;
  }

  public boolean hasAccusations()
  {
    boolean has = accusations.size() > 0;
    return has;
  }

  public int indexOfAccusation(String aAccusation)
  {
    int index = accusations.indexOf(aAccusation);
    return index;
  }
  /* Code from template association_GetOne */
  public Board getBoard()
  {
    return board;
  }
  /* Code from template association_GetMany */
  public Player getPlayer(int index)
  {
    Player aPlayer = players.get(index);
    return aPlayer;
  }

  public List<Player> getPlayers()
  {
    List<Player> newPlayers = Collections.unmodifiableList(players);
    return newPlayers;
  }

  public int numberOfPlayers()
  {
    int number = players.size();
    return number;
  }

  public boolean hasPlayers()
  {
    boolean has = players.size() > 0;
    return has;
  }

  public int indexOfPlayer(Player aPlayer)
  {
    int index = players.indexOf(aPlayer);
    return index;
  }
  /* Code from template association_GetMany */
  public Tile getTile(int index)
  {
    Tile aTile = tiles.get(index);
    return aTile;
  }

  public List<Tile> getTiles()
  {
    List<Tile> newTiles = Collections.unmodifiableList(tiles);
    return newTiles;
  }

  public int numberOfTiles()
  {
    int number = tiles.size();
    return number;
  }

  public boolean hasTiles()
  {
    boolean has = tiles.size() > 0;
    return has;
  }

  public int indexOfTile(Tile aTile)
  {
    int index = tiles.indexOf(aTile);
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
  /* Code from template association_SetUnidirectionalOne */
  public boolean setBoard(Board aNewBoard)
  {
    boolean wasSet = false;
    if (aNewBoard != null)
    {
      board = aNewBoard;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlayers()
  {
    return 3;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfPlayers()
  {
    return 6;
  }
  /* Code from template association_AddUnidirectionalMN */
  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (players.contains(aPlayer)) { return false; }
    if (numberOfPlayers() < maximumNumberOfPlayers())
    {
      players.add(aPlayer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean removePlayer(Player aPlayer)
  {
    boolean wasRemoved = false;
    if (!players.contains(aPlayer))
    {
      return wasRemoved;
    }

    if (numberOfPlayers() <= minimumNumberOfPlayers())
    {
      return wasRemoved;
    }

    players.remove(aPlayer);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_SetUnidirectionalMN */
  public boolean setPlayers(Player... newPlayers)
  {
    boolean wasSet = false;
    ArrayList<Player> verifiedPlayers = new ArrayList<Player>();
    for (Player aPlayer : newPlayers)
    {
      if (verifiedPlayers.contains(aPlayer))
      {
        continue;
      }
      verifiedPlayers.add(aPlayer);
    }

    if (verifiedPlayers.size() != newPlayers.length || verifiedPlayers.size() < minimumNumberOfPlayers() || verifiedPlayers.size() > maximumNumberOfPlayers())
    {
      return wasSet;
    }

    players.clear();
    players.addAll(verifiedPlayers);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPlayerAt(Player aPlayer, int index)
  {  
    boolean wasAdded = false;
    if(addPlayer(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayerAt(Player aPlayer, int index)
  {
    boolean wasAdded = false;
    if(players.contains(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayerAt(aPlayer, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTiles()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addTile(Tile aTile)
  {
    boolean wasAdded = false;
    if (tiles.contains(aTile)) { return false; }
    tiles.add(aTile);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTile(Tile aTile)
  {
    boolean wasRemoved = false;
    if (tiles.contains(aTile))
    {
      tiles.remove(aTile);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTileAt(Tile aTile, int index)
  {  
    boolean wasAdded = false;
    if(addTile(aTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTiles()) { index = numberOfTiles() - 1; }
      tiles.remove(aTile);
      tiles.add(index, aTile);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTileAt(Tile aTile, int index)
  {
    boolean wasAdded = false;
    if(tiles.contains(aTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTiles()) { index = numberOfTiles() - 1; }
      tiles.remove(aTile);
      tiles.add(index, aTile);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTileAt(aTile, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCards()
  {
    return 0;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfCards()
  {
    return 21;
  }
  /* Code from template association_AddUnidirectionalOptionalN */
  public boolean addCard(Card aCard)
  {
    boolean wasAdded = false;
    if (cards.contains(aCard)) { return false; }
    if (numberOfCards() < maximumNumberOfCards())
    {
      cards.add(aCard);
      wasAdded = true;
    }
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
  /* Code from template association_SetUnidirectionalOptionalN */
  public boolean setCards(Card... newCards)
  {
    boolean wasSet = false;
    ArrayList<Card> verifiedCards = new ArrayList<Card>();
    for (Card aCard : newCards)
    {
      if (verifiedCards.contains(aCard))
      {
        continue;
      }
      verifiedCards.add(aCard);
    }

    if (verifiedCards.size() != newCards.length || verifiedCards.size() > maximumNumberOfCards())
    {
      return wasSet;
    }

    cards.clear();
    cards.addAll(verifiedCards);
    wasSet = true;
    return wasSet;
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
    board = null;
    players.clear();
    tiles.clear();
    cards.clear();
  }

  // line 74 "model.ump"
  public void main(){
    
  }

  // line 76 "model.ump"
  public Card[] initDeck(){
	return null;
    
  }

  // line 77 "model.ump"
  public void dealCards(){
    
  }

  // line 78 "model.ump"
  public Card[] selectSolution(){
	return null;
    
  }

  // line 79 "model.ump"
  public void createBoard(){
    
  }

  // line 81 "model.ump"
  public void turn(Player p){
    
  }

  // line 82 "model.ump"
  public void move(Player p){
    
  }

  // line 83 "model.ump"
  public int rollDice(){
	return 0;
    
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "board = "+(getBoard()!=null?Integer.toHexString(System.identityHashCode(getBoard())):"null");
  }
}