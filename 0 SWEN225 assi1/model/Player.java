package model;
import java.awt.Point;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * Represents a character/player in the game
 */
public class Player extends Token
{

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Player Attributes

	private Point location;     //current location of player on the board
	private char character;     //character that represents this player
	private boolean isActive;   //checks if the character will be played by a player in this game
	private boolean canWin;     //checks that the player is allowed to make suggestion and accusations

	//Player Associations
	private List<Card> hand;    //hand of cards that each player has

	//------------------------
	// CONSTRUCTOR
	//------------------------
	public Player(Point aLocation, char aCharacter)
	{
		super();
		location = aLocation;
		//isActive = isActive;
		character = aCharacter;
		//hand = new ArrayList<Card>();
		hand = new ArrayList<Card>();
	}

	/**
	 * returns the char symbol of the character that this player represents
	 * @return
	 */
	public char getSymbol() {
		return character;
	}

	/**
	 * if player wants to make a suggestion and can do it, this function is called
	 * @param board for getting the weapons and char
	 * @return
	 */
	public Card[] makeSuggestion(Board board, Game game, String charName, String weapName){
		//let player choose one character card and one weapon card via strings
		//Player characterSelected = null;
		String characterName = charName;
		String weaponName = weapName;
		String roomName = "";

		//get char to search for char in board

		Card charCard = new Card(characterName);  //create character card
		System.out.println("charCard = "+charCard.getName(false).charAt(0));
		Point charLoc = board.searchFor(charCard.getName(false).charAt(0)); //find character on board, for moving the char

		Card weaponCard = new Card(weaponName);  //create weapon card
		Point weaponLoc = board.searchFor(weaponCard.getName(false).charAt(0)); //find weapon on board, for moving the weapon

		//get room suggestion took place in
		//get symbol of tile from player
		char roomChar = board.getGrid()[(int)this.location.getX()][(int)this.location.getY()].getUnderlyingSymbol();
		switch (roomChar) {
		case 'K':
			roomName = "kitchen";
			break;
		case 'B':
			roomName = "ballroom";
			break;
		case 'C':
			roomName = "conservatory";
			break;
		case 'G':
			roomName = "billiard room";
			break;
		case 'Y':
			roomName = "library";
			break;
		case 'S':
			roomName = "study";
			break;
		case 'H':
			roomName = "hall";
			break;
		case 'L':
			roomName = "lounge";
			break;
		case 'D':
			roomName = "dining room";
			break;
		}

		Card roomCard = new Card(roomName); //create room card

		//move character to room that player is in
		System.out.println("charLoc "+charLoc);
		//check if character is not in room already
		if(board.getGrid()[(int)charLoc.getY()][(int)charLoc.getX()].getUnderlyingSymbol() != roomChar) {
			Point moveTo = board.searchFor(roomChar);  //place character in first available spot
			game.teleport(board.getGrid()[(int)charLoc.getY()][(int)charLoc.getX()].getToken(), charLoc, moveTo);
		}

		//move weapon to room that player is in33
		//check if weapon is not in room already
		if(board.getGrid()[(int)weaponLoc.getY()][(int)weaponLoc.getX()].getUnderlyingSymbol() != roomChar) {
			Point moveTo = board.searchFor(roomChar);  //place character in first available spot
			game.teleport(board.getGrid()[(int)weaponLoc.getY()][(int)weaponLoc.getX()].getToken(), charLoc, moveTo);
		}

		Card[] suggestion = {charCard, weaponCard, roomCard};
		return suggestion;
	}

	/**
	 * let's this player chose an accusation
	 * @param accusations
	 * @param solution
	 * @return  true if accusation was correct. false if not
	 */
	public int makeAccusation(List<Card[]> accusations, List<Card> solution){
		if(accusations.isEmpty()) {
			return 2;
		}
		//print accusations
		System.out.println("Accusations are: ");
		for(int i = 0; i < accusations.size(); i++) {
			Card[] accusation = accusations.get(i);
			System.out.print("\t");
			for(Card card : accusation) {  //print out all the parts of the accusation
				System.out.print(card.getName(true)+" ");
			}
			System.out.println("["+i+"]");
		}
		System.out.println();
		//ask for which accusation
		Scanner s;
		int indexChosen = -1;
		while(indexChosen > accusations.size() || indexChosen < 0) {
			System.out.println("Which accusation do you choose? Please choose index of accusation");
			s = new Scanner(System.in);
			String str = s.next();

			try {
				indexChosen = parseInt(str);
			} catch(NumberFormatException e) {
				System.out.println("ERROR: invalid character Please type down a character");
			}
		}
		//get accusation from list of accusations in game
		//check that accusations exist
		if(!accusations.isEmpty()) {
			Card[] chosenAccusation = accusations.get(indexChosen);
			//check is accusation is correct
			for (int i = 0; i < chosenAccusation.length; i++) {
				if (!chosenAccusation[i].toString().equals(solution.get(i).toString())) {      //if not
					System.out.println("Accusation was incorrect!");
					System.out.println("You can now only refute");
					this.canWin = false;  //now this player cannot win in this game
					return 0;
				}
			}
			System.out.println("Accusation was correct!");  //if so
			return 1;
		}
		System.out.println("No accusations!");
		return 2;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if(o == null) return false;
		if(getClass() != o.getClass()) return false;
		return character == ((Player)o).character;
	}

	//------------------------
	// INTERFACE
	//------------------------

	public boolean setLocation(Point aLocation)
	{
		boolean wasSet = false;
		location = aLocation;
		wasSet = true;
		return wasSet;
	}

	public void setIsActive(boolean isActive)
	{
		this.isActive = isActive;
	}

	public void setCanWin(boolean canWin)
	{
		this.canWin = canWin;
	}
	public boolean getCanWin() {return this.canWin;}

	public Point getLocation()
	{
		return location;
	}

	public boolean getIsActive()
	{
		return isActive;
	}

	public char getCharacter()
	{
		return character;
	}

	public Card getCard(int index)
	{
		Card aCard = hand.get(index);
		return aCard;
	}

	public List<Card> getHand()
	{
		List<Card> newCards = Collections.unmodifiableList(hand);
		return newCards;
	}

	public int numberOfCards()
	{
		int number = hand.size();
		return number;
	}

	public boolean hasCards()
	{
		boolean has = hand.size() > 0;
		return has;
	}

	public int indexOfCard(Card aCard)
	{
		int index = hand.indexOf(aCard);
		return index;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfCards()
	{
		return 0;
	}

	/* Code from template association_AddUnidirectionalMany */
	public boolean addCard(Card aCard)
	{
		boolean wasAdded = false;
		if (hand.contains(aCard)) { return false; }
		hand.add(aCard);
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeCard(Card aCard)
	{
		boolean wasRemoved = false;
		if (hand.contains(aCard))
		{
			hand.remove(aCard);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addCardAt(Card aCard, int index)
	{  
		boolean wasAdded = false;
		if(addCard(aCard))
		{
			if(index < 0 ) { index = 0; }
			if(index > numberOfCards()) { index = numberOfCards() - 1; }
			hand.remove(aCard);
			hand.add(index, aCard);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveCardAt(Card aCard, int index)
	{
		boolean wasAdded = false;
		if(hand.contains(aCard))
		{
			if(index < 0 ) { index = 0; }
			if(index > numberOfCards()) { index = numberOfCards() - 1; }
			hand.remove(aCard);
			hand.add(index, aCard);
			wasAdded = true;
		} 
		else 
		{
			wasAdded = addCardAt(aCard, index);
		}
		return wasAdded;
	}

	public void delete()
	{
		hand.clear();
		super.delete();
	}

	public String getName() {
		switch(character){
			case 's':
				return "Miss Scarlett";
			case 'g':
				return "Mr Green";
			case 'k':
				return "Mrs Peacock";
			case 'p':
				return "Prof Plum";
			case 'm':
				return "Col. Mustard";
			default:
				return "Mrs White";
		}
	}

	public String toString(){
		return String.valueOf(character);
	}
}