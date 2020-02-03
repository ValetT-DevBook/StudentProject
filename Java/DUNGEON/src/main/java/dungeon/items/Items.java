package dungeon.items;

import dungeon.characters.Players;
import dungeon.exceptions.ElementNotFoundException;

/**
 * Interface implements different methods for all items
 */
public interface Items{
    /**
     * Allow to use an item
     * @param p player who use this item
     * @throws ElementNotFoundException .
     */
    public void use(Players p) throws ElementNotFoundException;
    
    /**
    * Remove the items from the room of player
    * 
    * @param p the player
    * @throws ElementNotFoundException .
    */
    public void removeFromRoom(Players p) throws ElementNotFoundException;

    /**
     * Allow to know if two object are equals
     * @param o an object
     * @return boolean *true if is equals
     *                 *false if isn't equals
     */
    public boolean equals(Object o);

     /**
     * The item
     * @return The item
     */
    public String toString();
}   