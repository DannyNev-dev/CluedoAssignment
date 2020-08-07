/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5099.60569f335 modeling language!*/


import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collector;

// line 2 "model.ump"
// line 104 "model.ump"
public class Board
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Board Associations
  private Tile[][] tiles;
  private List<Weapon> weapons;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Board(String[] boardData)
  {
    tiles = new Tile[25][24];
    //add data to each tile
    for(int i = 0; i < tiles.length; i++) {
      Tile[] row = tiles[i];
     // System.out.println("row: "+boardData[i]);
      for(int j = 0; j < row.length; j++) { //is row.length-1 because want to ignore \n
        //check which tile is it
        char c = boardData[i].charAt(j);
        switch(c) {
          case '.': //invalid tile
            tiles[i][j] = new InvalidTile(new Point(i, j), c, null);
            break;
          case '*': //for the hallway tiles and the hall tiles that are where the characters first start
            tiles[i][j] = new HallTile(new Point(i, j), null);
            break;
          case 'w':
            tiles[i][j] = new HallTile(new Point(i, j),  Game.PLAYERS[0]);
            break;
          case 'g':
            tiles[i][j] = new HallTile(new Point(i, j),  Game.PLAYERS[1]);
            break;
          case 'k':
            tiles[i][j] = new HallTile(new Point(i, j),  Game.PLAYERS[2]);
            break;
          case 'p':
            tiles[i][j] = new HallTile(new Point(i, j),  Game.PLAYERS[3]);
            break;
          case 's':
            tiles[i][j] = new HallTile(new Point(i, j),  Game.PLAYERS[4]);
            break;
          case 'm':
            tiles[i][j] = new HallTile(new Point(i, j),  Game.PLAYERS[5]);
            break;
          default:  //for the room tiles
            tiles[i][j] = new RoomTile(new Point(i, j), c, null);
            break;
        }
      }
    }
    weapons = new ArrayList<>();
    //initialise weapons
    for(int i = 0; i < 6; i++) {
      weapons.add(new Weapon(Game.WEAPONSYMBOL[i]));
    }
    //shuffle weapon's list and add to rooms
    Collections.shuffle(weapons);
    //search for kitchen and add weapon to kitchen
    //TO DO HERE

   /* tiles = new ArrayList<Tile>();
    weapons = new ArrayList<Weapon>();
    boolean didAddWeapons = setWeapons(allWeapons);
    if (!didAddWeapons)
    {
      throw new RuntimeException("Unable to create Board, must have 6 weapons. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }*/
  }

  /**
   * searches for first occurance of the character in the board
   * @param c character to search for
   * @return
   */
  private Point searchFor(char c) {
    return null;
  }


  // line 6 "model.ump"

  /**
   * prints current state of board
   */
  public void printBoard(){
    for(Tile[] row : tiles) {
      for(Tile t : row) {
        System.out.print(t.getSymbol());
      }
      System.out.println();
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  /*public Tile getTile(int index)
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
  }*/
  /* Code from template association_GetMany */
  public Weapon getWeapon(int index)
  {
    Weapon aWeapon = weapons.get(index);
    return aWeapon;
  }

  public List<Weapon> getWeapons()
  {
    List<Weapon> newWeapons = Collections.unmodifiableList(weapons);
    return newWeapons;
  }

  public int numberOfWeapons()
  {
    int number = weapons.size();
    return number;
  }

  public boolean hasWeapons()
  {
    boolean has = weapons.size() > 0;
    return has;
  }

  public int indexOfWeapon(Weapon aWeapon)
  {
    int index = weapons.indexOf(aWeapon);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTiles()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
 /* public boolean addTile(Tile aTile)
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
  /*public boolean addTileAt(Tile aTile, int index)
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
  }*/
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfWeapons()
  {
    return 6;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWeapons()
  {
    return 6;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfWeapons()
  {
    return 6;
  }
  /* Code from template association_SetUnidirectionalN */
  public boolean setWeapons(Weapon... newWeapons)
  {
    boolean wasSet = false;
    ArrayList<Weapon> verifiedWeapons = new ArrayList<Weapon>();
    for (Weapon aWeapon : newWeapons)
    {
      if (verifiedWeapons.contains(aWeapon))
      {
        continue;
      }
      verifiedWeapons.add(aWeapon);
    }

    if (verifiedWeapons.size() != newWeapons.length || verifiedWeapons.size() != requiredNumberOfWeapons())
    {
      return wasSet;
    }

    weapons.clear();
    weapons.addAll(verifiedWeapons);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    //tiles.clear();
    weapons.clear();
  }



}