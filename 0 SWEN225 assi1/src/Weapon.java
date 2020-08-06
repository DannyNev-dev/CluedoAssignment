/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5099.60569f335 modeling language!*/



// line 41 "model.ump"
// line 136 "model.ump"
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

  // line 44 "model.ump"
   public String toString(){
    
  }

}