import java.awt.Point;

public class HallTile extends Tile
{
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HallTile(Point aLocation, Token token)
  {
    super(aLocation, '*', token);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

  // line 28 "model.ump"
  public String toString(){
	return "*";   
  }
}