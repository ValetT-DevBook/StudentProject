package dungeon.items;

import dungeon.characters.Players;
import dungeon.exceptions.ElementNotFoundException;
/**
 * Class Gold set the gold item
 */
public class GoldBag implements Items{

    //ATTRIBUTS
    private int count;

    //BUILDER

    /**
     * Build the gold bag
     * @param n number of gold in a bag
     */
    public GoldBag(int n){
        this.count = n;
    }

    //METHODS

    /**
     * Allow to use a item
     * @param p Player
     * @throws ElementNotFoundException .
     */
    public void use(Players p) throws ElementNotFoundException{
        p.changeGold(this.count);
        System.out.println("You earn " + this.count + " Gold.");
        this.removeFromRoom(p);
    }

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
     * Give the number of gold
     * @return number of gold
     */
    public int getCount(){
        return this.count;
    }

    /**
     * The item
     * @return The item
     */
    public String toString(){
        return "Gold Bag";
    }

    /**
     * Compare two objects 
     * @param o An object to compare
     * @return boolean *true if same object
     *                 *flase if different object
     */
    public boolean equals(Object o){
        if (o instanceof GoldBag){
            GoldBag other = (GoldBag) o;
            return this.count == other.getCount();
        }else{
            return false;
        }
    }
}