package dungeon.actions;

import dungeon.characters.Players;

/**
 * CLass StatusAction which allows to see the stats of player
 */
public class StatusAction implements Action {

    /**
     * Allow to execute an action
     * 
     * @param p player make an antion
     */
    public void executeAction(Players p) {
        System.out.println(p.toString());
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
        return "Status";
    }
}