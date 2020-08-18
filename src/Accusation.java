
import java.util.ArrayList;
import java.util.List;

/**
 * An accusation by a player to be checked against the murder details
 */
public class Accusation extends Tuple {
    int playerNo;
    Character accuser;
    CharacterCard murderAccused;
    WeaponCard weaponAccused;
    RoomCard roomAccused;

    public Accusation(int playerNo, Character accuser, CharacterCard sCharacter, WeaponCard sWeapon, RoomCard sRoom) {
        super(sCharacter, sWeapon, sRoom);
        this.playerNo = playerNo;
        this.accuser = accuser;
        murderAccused = sCharacter;
        weaponAccused = sWeapon;
        roomAccused = sRoom;
    }

    public String toString() {
        return "Player " + playerNo + " made the accusation:\n" + murderAccused.getName() + ", in the " + roomAccused.getName() +", with the " + weaponAccused.getName();
    }

    public CharacterCard getMurderAccused(){
        return murderAccused;
    }

    public WeaponCard getWeaponAccused(){
        return weaponAccused;
    }

    public RoomCard getRoomAccused(){
        return roomAccused;
    }

    public Character getAccuser(){
        return accuser;
    }


}