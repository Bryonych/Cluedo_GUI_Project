package Model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5074.a43557235 modeling language!*/


/**
 * Super class to represent all cards
 */
public abstract class Card
{
 private String name;

public Card(String name){
  this.name = name;
}

public abstract String getName();

}