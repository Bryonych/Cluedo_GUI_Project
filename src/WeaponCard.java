package viewController;

import Model.Card;

/**
 * Represents a weapon card in the game
 */
public class WeaponCard extends Card
{

    private String name;
    private Weapon weapon;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public WeaponCard(String name, Weapon weapon)
    {
        super(name);
        this.name = name;
        this.weapon = weapon;
    }

    public String getName()
    {
        return name;
    }

    public Weapon getWeapon(){
        return weapon;
    }

    public String toString()
    {
        return " ------------------- \n" +
                " Card: " + name + "\n" +
                " ------------------- \n";
    }
}