import java.awt.Point;

public class DoorTile extends Tile{

	  //------------------------
	  // MEMBER VARIABLES
	  //------------------------

	  //------------------------
	  // CONSTRUCTOR
	  //------------------------

	  public DoorTile(Point aLocation, char aSymbol, Token token)
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
