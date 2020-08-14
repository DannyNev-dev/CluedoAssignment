/**
 * Represents a token in the game, this token can be a weapon or a character/player
 */
public abstract class Token
{
	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//------------------------
	// CONSTRUCTOR
	//------------------------
	public Token()
	{}

	//------------------------
	// INTERFACE
	//------------------------

	public void delete()
	{}

	abstract public char getSymbol();
}