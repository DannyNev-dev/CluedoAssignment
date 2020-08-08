import java.awt.Point;

public class InvalidTile extends Tile
{
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public InvalidTile(Point aLocation, char aSymbol, Token token)
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

  // line 34 "model.ump"
  public String toString(){
	return ".";    
  }
}