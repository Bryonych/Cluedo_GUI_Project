import java.util.*;

/**
 * Represents the envelope that stores the murder details
 */
public class Tuple
{

  private CharacterCard murderer;
  private WeaponCard weapon;
  private RoomCard crimeScene;


  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Tuple(CharacterCard aMurderer, WeaponCard aWeapon, RoomCard aCrimeScene)
  {
    murderer = aMurderer;
    weapon = aWeapon;
    crimeScene = aCrimeScene;

  }

  public CharacterCard getMurderer()
  {
    return murderer;
  }

  public WeaponCard getWeapon()
  {
    return weapon;
  }

  public RoomCard getCrimeScene()
  {
    return crimeScene;
  }


}
