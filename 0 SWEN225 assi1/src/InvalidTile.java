import java.awt.Point;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5099.60569f335 modeling language!*/



// line 31 "model.ump"
// line 126 "model.ump"
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