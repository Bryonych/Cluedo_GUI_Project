
import java.util.*;

/**
 * A suggestion to be checked against the other players' hands
 */
public class Suggestion extends Tuple {
    int playerNo;
    Character player;
    CharacterCard suspect;
    WeaponCard weapon;
    RoomCard crimeScene;
    //list of suggestion cards so that subsequent players can compare with their hand
    List<Card> suggestionCards = new ArrayList<Card>();

    public Suggestion(int playerNo, Character player, CharacterCard sCharacter, WeaponCard sWeapon, RoomCard sRoom) {
        super(sCharacter, sWeapon, sRoom);
        this.playerNo = playerNo;
        this.player = player;
        suspect = sCharacter;
        weapon = sWeapon;
        crimeScene = sRoom;
        suggestionCards.add(suspect);
        suggestionCards.add(weapon);
        suggestionCards.add(crimeScene);
    }

    public String toString() {
        return "Player " + playerNo + " made the suggestion:\n" + suspect.getName() + ", in the " + crimeScene.getName() +", with the " + weapon.getName();
    }

    public List<Card> getCards(){
        return suggestionCards;
    }

    public Accusation toAccusation() {
        Accusation conv = new Accusation(playerNo, player, suspect, weapon, crimeScene);
        return conv;
    }

    public Character getSuggester() {
        return player;
    }

}