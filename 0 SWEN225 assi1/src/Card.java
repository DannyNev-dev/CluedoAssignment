/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5099.60569f335 modeling language!*/



// line 47 "model.ump"
// line 141 "model.ump"
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

 /* public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }*/

  public String getName()
  {
    return name;
  }

  public void delete()
  {}

  // line 51 "model.ump"
  public boolean equals(Card c){
	return false;
    
  }

  // line 52 "model.ump"

  /**
   * compares two cards and sees if the card is in fact the same
   * @param o
   * @return
   */
  public boolean equals(Object o) {
    System.out.println("at here");
    if(o instanceof Card) { //check if same class
      System.out.println("AA");
      Card c = (Card) o;
      if(c.name.equals(this.name)) {
        System.out.println("BB");
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