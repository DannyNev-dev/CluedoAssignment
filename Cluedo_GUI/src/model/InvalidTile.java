package model;
import java.awt.Point;

/**
 * Stores the invalid tile for the board
 */
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

	public String toString(){
		return ".";    
	}
}