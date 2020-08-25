package model;
/**
 * represents a "physical" weapon in the game
 */
public class Weapon extends Token
{
	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Weapon Attributes
	private char name;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Weapon(char aName)
	{
		super();
		name = aName;
	}

	//------------------------
	// INTERFACE
	//------------------------

	public boolean setName(char aName)
	{
		boolean wasSet = false;
		name = aName;
		wasSet = true;
		return wasSet;
	}

	public char getName()
	{
		return name;
	}

	public void delete()
	{
		super.delete();
	}

	public char getSymbol() {
		return name;
	}

	public String toString(){
		return null;
	}
}