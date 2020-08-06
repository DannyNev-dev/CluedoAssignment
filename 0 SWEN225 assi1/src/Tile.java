import java.awt.Point;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5099.60569f335 modeling language!*/



// line 9 "model.ump"
// line 111 "model.ump"
public abstract class Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Tile Attributes
  private Point location;
  private char symbol;
  private boolean isOccupied;

  //Tile Associations
  private Token token;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Tile(Point aLocation, char aSymbol, boolean aIsOccupied)
  {
    location = aLocation;
    symbol = aSymbol;
    isOccupied = aIsOccupied;
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

  public boolean setSymbol(char aSymbol)
  {
    boolean wasSet = false;
    symbol = aSymbol;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsOccupied(boolean aIsOccupied)
  {
    boolean wasSet = false;
    isOccupied = aIsOccupied;
    wasSet = true;
    return wasSet;
  }

  public Point getLocation()
  {
    return location;
  }

  public char getSymbol()
  {
    return symbol;
  }

  public boolean getIsOccupied()
  {
    return isOccupied;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsOccupied()
  {
    return isOccupied;
  }
  /* Code from template association_GetOne */
  public Token getToken()
  {
    return token;
  }

  public boolean hasToken()
  {
    boolean has = token != null;
    return has;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setToken(Token aNewToken)
  {
    boolean wasSet = false;
    token = aNewToken;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    token = null;
  }

  public abstract String toString();

}