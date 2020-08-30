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
	 * returns card's name in other forms
	 * @param form for stating which card name you want, true of file name, false for board token name
	 * @return
	 */
	public String getName(boolean form)
	{
		switch(name) {
			//characters
			case "Miss Scarlet":
				return (form) ? "scarlett" : "s";
			case "Colonel Mustard":
				return (form) ? "mustard" : "m";
			case "Mrs White":
				return (form) ? "white" : "w";
			case "Mr Green":
				return (form) ? "green" : "g";
			case "Mrs Peacock":
				return (form) ? "peacock" : "k";
			case "Professor Plum":
				return (form) ? "plum" : "p";
				//weapons
			case "candlestick":
				return (form) ? "candle" : "c";
			case "dagger":
				return (form) ? "knife" : "d";
			case "lead pipe":
				return (form) ? "pipe" : "l";
			case "revolver":
				return (form) ? name : "r";
			case "rope":
				return (form) ? name : "o";
			case "spanner":
				return (form) ? name : "a";
				//rooms
			case "billiard room":
				return "billiard";
			case "dining room":
				return "dining";
		}
		return name;	//if don't need to change name to get file name
	}

	public void delete()
	{}

	/*public boolean equals(Card c){
		return false;

	}*/

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