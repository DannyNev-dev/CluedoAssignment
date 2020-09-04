package model;
import java.awt.Point;

/**
 * Stores the room tile for the board
 */
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

	public String toString(){
		return String.valueOf(super.getSymbol());
	}
}