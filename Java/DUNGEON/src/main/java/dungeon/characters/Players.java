package dungeon.characters;

import java.util.List;
import java.util.ArrayList;

import dungeon.AdventureGame;
import dungeon.actions.*;
import dungeon.exceptions.ElementNotFoundException;

/**
 * Class Players to set different player
 */
public class Players extends GameCharacters {

    // ATTRIBUTES

    // BUILDERS
    /**
     * Build a player
     * 
     * @param life     life point
     * @param strength strength point
     * @param gold     gold in a bag
     * @param name     the name of the character
     * @param game     the party
     */
    public Players(int life, int strength, int gold, String name, AdventureGame game) {
        super(life, strength, gold, name, game);
    }

    // METHODS

    /**
     * Stop the game
     */
    public void die() {
        this.game.isFinished(); // --> In AdventureGame --> method 'isFinished' *true if player's life < 0 or
                                // room isExit.
    }

    /**
     * Actions possible for the player
     * @return List the list of actions possible
     */
    public List<Action> act() {
        List<Action> result = new ArrayList<Action>();

        for (Action act : AllActions.LIST_ACTIONS) {
            if(act.isPossible(this))
                result.add(act);
        }

        return result;
    }

    /**
     * Attack the monster
     * 
     * @param c A character we want to attack
     * @throws ElementNotFoundException if c isn't in a room
     */
    public void attack(GameCharacters c) throws ElementNotFoundException {
        super.attack(c);
        if (c.isAlive()){
            c.attack(this);
        }else{
            c.die();
        }
    } 
}
