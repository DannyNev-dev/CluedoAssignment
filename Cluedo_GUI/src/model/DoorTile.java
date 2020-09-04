package model;
import java.awt.Point;

/**
 * Stores the door tile for the board
 */
public class DoorTile extends Tile
{
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

	public String toString(){
		return String.valueOf(super.getSymbol());
	}
}