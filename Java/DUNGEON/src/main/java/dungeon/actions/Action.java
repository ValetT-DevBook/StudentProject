package dungeon.actions;

import dungeon.characters.Players;

/**
 * Interface to define differents methods for all actions
 */
public interface Action {
    public void executeAction(Players p);
    public boolean isPossible(Players p);
    public String toString();
}