/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5074.a43557235 modeling language!*/


/**
 * Represents a cell on the board
 */
public class Cell
{

  private boolean isEmpty = true;
  private int xPos;
  private int yPos;

  public enum Type {
    WALL,
    START,
    KITCHEN,
    BALLROOM,
    CONSERVATORY,
    DINING,
    BILLIARD,
    LIBRARY,
    LOUNGE,
    HALL,
    STUDY,
    HALLWAY,
    DOOR,
    WHITE,
    GREEN,
    PEACOCK,
    PLUM,
    SCARLETT,
    MUSTARD,
    CANDLESTICK,
    DAGGER,
    LEADPIPE,
    REVOLVER,
    ROPE,
    SPANNER;
  }

  private Type type;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Cell(Type type, int aXPos, int aYPos)
  {
    this.type = type;
    xPos = aXPos;
    yPos = aYPos;
  }

  public void changeType(Type type){
    this.type = type;
  }

  public Type getType() {
    return type;
  }

  public boolean getIsEmpty()
  {
    return isEmpty;
  }

  public int getXPos()
  {
    return xPos;
  }

  public int getYPos()
  {
    return yPos;
  }

  public void setIsEmpty(boolean empty){
    this.isEmpty = empty;
  }

  @Override
  public boolean equals(Object obj){
    if (this == obj)  return true;
    if (obj == null)  return false;
    if (obj.getClass() != this.getClass())  return false;
    Cell other = (Cell)obj;
    return this.xPos == other.xPos && this.yPos == other.yPos;
  }

  @Override
  public String toString()
  {
    if (type.equals(Type.WALL)){
      return "XX";
    }
    else if (type.equals(Type.START)){
      return "()";
    }
    else if (type.equals(Type.DOOR)){
      return "  ";
    }
    else if (type.equals(Type.WHITE)){
      return "W|";
    }
    else if (type.equals(Type.GREEN)){
      return "G|";
    }
    else if (type.equals(Type.PEACOCK)){
      return "K|";
    }
    else if (type.equals(Type.PLUM)){
      return "P|";
    }
    else if (type.equals(Type.SCARLETT)){
      return "S|";
    }
    else if (type.equals(Type.MUSTARD)){
      return "M|";
    }
    else if (type.equals(Type.CANDLESTICK)){
      return "I|";
    }
    else if (type.equals(Type.DAGGER)){
      return "1|";
    }
    else if (type.equals(Type.LEADPIPE)){
      return "7|";
    }
    else if (type.equals(Type.REVOLVER)){
      return "L|";
    }
    else if (type.equals(Type.ROPE)){
      return "8|";
    }
    else if (type.equals(Type.SPANNER)){
      return "F|";
    }
    else if (type.equals(Type.HALLWAY)) {
      return "_|";
    }
    else {
      return "_|";
    }
  }
}