package dungeon.actions;

import java.util.List;

import dungeon.characters.Monsters;
import dungeon.characters.Players;
import dungeon.scanner.ScannerChoice;

/**
 * CLass AttackAction which allows attack an other character
 */
public class AttackAction implements Action {

    /**
     * Allow to execute an action
     * 
     * @param p player make an antion
     */
    public void executeAction(Players p) {
        System.out.println("You decide to attack a monster.");
        List<Monsters> monsters = p.getAdventureGame().currentRoom().getMonsters();

        ScannerChoice<Monsters> choice = new ScannerChoice<Monsters>(monsters);
        Monsters m = choice.readChoice();

        if (m != null){
            try {
                p.attack(m);
            } catch (Exception e) {
                System.out.println("You failed your attack.");
            }
        }
    }

    /**
     * Say if an action is possible
     * @param p player who make an action
     * @return boolean *true is possible
     *                 *false is not possible
     */
    public boolean isPossible(Players p) {
        return p.getAdventureGame().currentRoom().hasMonsters();
    }

    /**
     * Create a word about the action
     * @return String The action
     */
    public String toString(){
        return "Attack";
    }
}