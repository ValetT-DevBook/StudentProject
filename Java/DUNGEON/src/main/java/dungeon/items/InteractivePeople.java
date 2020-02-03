package dungeon.items;
import java.lang.reflect.InvocationTargetException;

import dungeon.characters.Players;
import dungeon.exceptions.ElementNotFoundException;
/**
 * Class Gold set the gold item
 */
public abstract class InteractivePeople implements Items{

    //ATTRIBUT
    protected int price;
    protected String name;

    //BUILDER

    /**
     * Build the gold bag
     * @param n price 
     */
    public InteractivePeople(int n){
        this.price = n;
    }

    //METHODS

    /**
     * Allow to use this item
     * @param p a player use the item
     * @throws ElementNotFoundException .
     */
    public abstract void use(Players p) throws ElementNotFoundException;

    protected abstract boolean trade(Players p);

    protected abstract Items giveObject() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

    /**
     * Remove the items from the room of player
     * 
     * @param p the player
     * @throws ElementNotFoundException .
     */
    public void removeFromRoom(Players p) throws ElementNotFoundException {
        p.getAdventureGame().currentRoom().removeItems(this);
    }

    /**
     * Give the price of the interactive people
     * @return the price
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * The item
     * @return The item
     */
    public String toString() {
        return this.name;
    }

    /**
     * Compare two objects 
     * @param o An object to compare
     * @return boolean *true if same object
     *                 *flase if different object
     */
    public boolean equals(Object o){
        if (o instanceof InteractivePeople){
            InteractivePeople other = (InteractivePeople) o;
            return this.name.equals(other.toString()) &&
                this.price == other.getPrice();
        }else{
            return false;
        }
    }
}