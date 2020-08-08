import java.awt.Point;

public class RoomTile extends Tile
{
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RoomTile(Point aLocation, char aSymbol, Token token)
  {
    super(aLocation, aSymbol, token);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

  // line 22 "model.ump"
  public String toString(){
	return String.valueOf(super.getSymbol());
  }
}