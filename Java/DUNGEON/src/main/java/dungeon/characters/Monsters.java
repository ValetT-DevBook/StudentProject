package dungeon.characters;

import dungeon.AdventureGame;
import dungeon.exceptions.ElementNotFoundException;
import dungeon.items.GoldBag;

/**
 * Class Monsters to set different monsters
 */
public class Monsters extends GameCharacters {

    // BUILDERS
    /**
     * Build a monster
     * 
     * @param life     life point
     * @param strength strength point
     * @param gold     gold in a bag
     * @param name     the name of the character
     * @param game     the party
     */
    public Monsters(int life, int strength, int gold, String name, AdventureGame game) {
        super(life, strength, gold, name, game);
    }

    // METHODS

    /**
     * Remove the monster in the list of the monsters in a room
     * @throws ElementNotFoundException if the monster isn't in a room
     */
    public void die() throws ElementNotFoundException {
        this.game.currentRoom().addItems(new GoldBag(this.gold));
        this.game.currentRoom().removeMonsters(this);
        System.out.println(this.name + " die and left a Gold Bag.");
    }
    /**
     * Create a word about the monster
     * @return String The monster's name
     */
    public String toString(){
        return this.getName();
    }
}