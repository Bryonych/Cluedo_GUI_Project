/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5074.a43557235 modeling language!*/


import java.util.*;

/**
 * Stores a player's hand
 */
public class Hand
{


  private List<Card> cards = new ArrayList<Card>();
  Character player;


  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Hand(List<Card> aCards, Character player)
  {
    cards = aCards;
    this.player = player;
  }

  public List<Card> getCards()
  {
    return cards;
  }

  public void addToHand(Card newCard){
    cards.add(newCard);
  }

  public String toString()
  {
      String display = player.getName() + "'s Hand:\n";
      for (Card c : cards){
        display += c.toString();
      }
      return display;
  }
}