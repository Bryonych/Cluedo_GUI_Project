package viewController;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5074.a43557235 modeling language!*/


import java.util.*;

import Model.Cell;

/**
 * Represents the board of the game
 */
public class Board
{

  private Cell[][] squares;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Board(Cell[][] squares)
  {
    this.squares = squares;
  }

  public Cell[][] getCells(){
    return squares;
  }

  public void changeCell(Cell newCell, int xPos, int yPos){
    squares[yPos][xPos] = newCell;
  }


}