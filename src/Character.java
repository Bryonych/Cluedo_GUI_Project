
import java.util.*;

/**
 * Represents a character in the game
 */
public class Character
{

  private String name;
  private Hand hand;
  private Cell location;
  private Cell.Type currentRoom;
  private Cell.Type characterType;
  private boolean makeSuggestions = true;


  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Character(String aName, Cell aLocation, Cell.Type character)
  {
    name = aName;
    location = aLocation;
    this.currentRoom = Cell.Type.START;
    characterType = character;

  }

  public Cell.Type getCharacterType(){
    return characterType;
  }

  public void setHand(Hand newHand){
    this.hand = newHand;
  }

  public Cell.Type getCurrentRoom(){
    return currentRoom;
  }

  public void setCurrentRoom(Cell.Type newRoom){
    currentRoom = newRoom;
  }

  public Cell getLocation(){
    return location;
  }

  public void setLocation(Cell newLoc){
    this.location = newLoc;
  }


  public String getName()
  {
    return name;
  }

  public Hand getHand()
  {
    return hand;
  }

  public boolean canMakeSuggestions() {
    if(makeSuggestions) {
      return true;
    }
    else {
      return false;
    }
  }

  public void setSuggestionStatus(boolean value) {
    makeSuggestions = value;
  }

  @Override
  public boolean equals(Object obj){
    if (this == obj)  return true;
    if (obj == null)  return false;
    if (obj.getClass() != this.getClass())  return false;
    Character other = (Character)obj;
    return this.name.equals(other.name);
  }



}