package dungeon.actions;

import java.util.List;

import dungeon.characters.Players;
import dungeon.rooms.Direction;
import dungeon.scanner.ScannerChoice;

/**
 * Class MoveAction which allows to move in an other room
 */
public class MoveAction implements Action{
     /**
     * Allow to execute an action
     * @param p player make an antion
     */
    public void executeAction(Players p) {
        System.out.println("You decide to move.");
        List<Direction> dir = p.getAdventureGame().currentRoom().getDirection();

        ScannerChoice<Direction> choice = new ScannerChoice<Direction>(dir);
        Direction d = choice.readChoice();

        if (d != null){
            try {
                p.getAdventureGame().playerMoveTo(d);
            } catch (Exception e) {
                System.out.println("You failed your move.");
            }
        }

    }

    /**
     * Say if an anction is possible
     * @param p player who make an action
     * @return boolean *true is possible
     *                 *false is not possible
     */
    public boolean isPossible(Players p) {
        return !p.getAdventureGame().currentRoom().hasMonsters();
    }

    /**
     * Create a word about the action
     * @return String The action
     */
    public String toString(){
        return "Move";
    }
}