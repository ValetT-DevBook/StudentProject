package dungeon.items;

import dungeon.characters.Players;
import dungeon.exceptions.ElementNotFoundException;

/**
 * Class LifePotion allows to won different life point
 */
public class LifePotion extends Potion {

    // BUILDER
    /**
     * Build a life potion item
     * 
     * @param health number of life point won
     */
    public LifePotion(int health) {
        super(health);
        this.name = "Life Potion";
    }

    // METHODS
    /**
     * Add the life point at the character
     * 
     * @param p      character who use th potion
     * @throws ElementNotFoundException .
     */
    public void use(Players p) throws ElementNotFoundException {
        p.changeLife(this.count);
        System.out.println("You earn " + this.count + " health.");
        this.removeFromRoom(p);
    }

     /**
     * The item
     * @return The item
     */
    public String toString() {
        return this.name;
    }

}