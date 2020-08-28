package model;

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

	/**
	 * returns card's file name
	 * @return
	 */
	public String getName()
	{
		switch(name) {
			case "Miss Scarlet":
				return "scarlett";
			case "Colonel Mustard":
				return "mustard";
			case "Mrs White":
				return "white";
			case "Mr Green":
				return "green";
			case "Mrs Peacock":
				return "peacock";
			case "Professor Plum":
				return "plum";
			case "candlestick":
				return "candle";
			case "dagger":
				return "knife";
			case "lead pipe":
				return "pipe";
			case "spanner":
				return "rope";
			case "billiard room":
				return "billiard";
			case "dining room":
				return "dining";
		}
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