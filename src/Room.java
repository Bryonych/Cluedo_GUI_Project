package viewController;
import java.util.*;

import Model.Cell;

/**
 * Represents a room in the game
 */
public class Room
{

    private String name;
    private Cell weaponSpot;
    private Cell characterSpot;
    private Weapon currentWeapon;
    private Cell.Type roomType;


    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Room(String aName, Cell weapon, Cell.Type roomType, Cell characterSpot)
    {
        name = aName;
        weaponSpot = weapon;
        this.roomType = roomType;
        this.characterSpot = characterSpot;

    }

   public Cell getCharacterSpot(){
        return characterSpot;
   }

   public Cell.Type getType(){
        return roomType;
   }

   public Cell getWeaponSpot(){
        return weaponSpot;
   }

   public void changeWeapon(Weapon newWeapon){
        this.currentWeapon = newWeapon;
   }

   public String getName()
    {
        return name;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)  return true;
        if (obj == null)  return false;
        if (obj.getClass() != this.getClass())  return false;
        Room other = (Room)obj;
        return this.name.equals(other.name);
    }

    @Override
    public String toString()
    {
        return super.toString() + "["+
                "name" + ":" + getName()+ "]";
    }
}


