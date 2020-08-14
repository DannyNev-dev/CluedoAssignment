
public class Card
{

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Card Attributes
	private String name;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Card(String aName)
	{
		name = aName;
	}

	//------------------------
	// INTERFACE
	//------------------------

	public String getName()
	{
		return name;
	}

	public void delete()
	{}

	public boolean equals(Card c){
		return false;

	}

	/**
	 * compares two cards and sees if the card is in fact the same
	 * @param o
	 * @return
	 */
	public boolean equals(Object o) {
		//System.out.println("at here");
		if(o instanceof Card) { //check if same class
			//System.out.println("AA");
			Card c = (Card) o;
			if(c.name.equals(this.name)) {
				//System.out.println("BB");
				return true;
			}
		}
		return false;
	}

	/**
	 * useful for debugging
	 * @return
	 */
	public String toString() {
		return this.name;
	}

}