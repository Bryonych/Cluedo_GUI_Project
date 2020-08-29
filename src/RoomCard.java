package viewController;

import Model.Card;

/**
 * Represents a room card in the game
 */
public class RoomCard extends Card
{

  private String name;
  private Room room;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RoomCard(String name, Room room)
  {
    super(name);
    this.name = name;
    this.room = room;
  }

  public String getName()
  {
    return name;
  }

  public Room getRoom(){
    return room;
  }

  public String toString()
  {
    return " ------------------- \n" +
            " Card: " + name + "\n" +
            " ------------------- \n";
  }
}