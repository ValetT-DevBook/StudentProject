package dungeon.actions;

import dungeon.characters.Players;

/**
 * Class LookAction which allows to look the actions possible in the room
 */
public class LookAction implements Action{

     /**
     * Allow to execute an action
     * @param p player make an antion
     */
    public void executeAction(Players p) {
        System.out.println(p.getAdventureGame().currentRoom().toString());

    }

    /**
     * Say if an anction is possible
     * @param p player who make an action
     * @return boolean *true is possible
     *                 *false is not possible
     */
    public boolean isPossible(Players p) {
        return true;
    }

    /**
     * Create a word about the action
     * @return String The action
     */
    public String toString(){
        return "Look";
    }
}