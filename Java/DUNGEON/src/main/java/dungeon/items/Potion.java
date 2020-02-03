package dungeon.items;

import dungeon.characters.Players;
import dungeon.exceptions.ElementNotFoundException;

/**
 * Class LifePotion allows to won different life point
 */
public abstract class Potion implements Items {

    // ATTRIBUTS
    protected int count;
    protected String name;

    // BUILDER
    /**
     * Build a potion
     * 
     * @param n a number
     */
    public Potion(int n) {
        this.count = n;
    }

    // METHODS

    /**
     * Allow to use a potion
     * 
     * @param p player who use this potion
     */
    public abstract void use(Players p) throws ElementNotFoundException;

    /**
     * Remove the items from the room of player
     * 
     * @param p the player
     * @throws ElementNotFoundException .
     */
    public void removeFromRoom(Players p) throws ElementNotFoundException {
        p.getAdventureGame().currentRoom().removeItems(this);
    };

    /**
     * Give the count of potion
     * @return the int of potion
     */
    public int getCount(){
        return this.count;
    }

    /**
     * Compare two objects 
     * @param o An object to compare
     * @return boolean *true if same object
     *                 *flase if different object
     */
    public boolean equals(Object o){
        if (o instanceof Potion){
            Potion other = (Potion) o;
            return this.name.equals(other.toString()) &&
                this.count == other.getCount();
        }else{
            return false;
        }
    }

}