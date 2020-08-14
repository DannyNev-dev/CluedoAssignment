import java.awt.Point;

/**
 * Stores the hall tile for the board
 */
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

	public String toString(){
		return "*";   
	}
}