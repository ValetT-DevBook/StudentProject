package dungeon.items;

import dungeon.characters.Players;
import dungeon.exceptions.ElementNotFoundException;

/**
 * Class StrengthPotion allows to won different strength point
 */
public class StrengthPotion extends Potion {

    // BUILDER
    /**
     * Build a potion of strength
     * 
     * @param strength number of strength point won
     */
    public StrengthPotion(int strength) {
        super(strength);
        this.name = "Strength Potion";
    }

    // METHODS
    /**
     * Add strength point at the character
     * 
     * @param p        character who use the potion
     * @throws ElementNotFoundException .
     */
    public void use(Players p) throws ElementNotFoundException {
        p.changeStrength(this.count);
        System.out.println("You earn " + this.count + " Strength.");
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