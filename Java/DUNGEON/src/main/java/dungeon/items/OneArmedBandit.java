package dungeon.items;

import java.lang.reflect.InvocationTargetException;

import dungeon.characters.Players;
import dungeon.exceptions.ElementNotFoundException;
import dungeon.util.RandomRange;

/**
 * Class OneArmedBandit allows to use the item one armed bandit
 */
public class OneArmedBandit extends InteractivePeople {

    // BUILDER
    /**
     * Build an one armed bandit item
     * 
     * @param n price
     */
    public OneArmedBandit(int n) {
        super(n);
        this.name = "One Armed Bandit";
    }

    /**
     * Allow to use item
     * 
     * @param p the player who uses the item
     * @throws ElementNotFoundException .
     */
    public void use(Players p) throws ElementNotFoundException {
        boolean trade = this.trade(p);
        if (trade){
            Items item = null;
            try {
                item = this.giveObject();
                item.use(p);
            } catch (Exception e) {}
        }

        this.removeFromRoom(p);
    }

    /**
     * Trade the numbep.getAdventureGame().currentRoom().removeItems(this);r of Gold to the Bandit
     * 
     * @param p the player who give money
     * @return True, if the bandit accept False otherwises
     */
    protected boolean trade(Players p) {
        if (p.getGold() < this.price) {
            System.out.println("The One Armed Bandit don't accept and vanish.");
            return false;
        }
        else {
            System.out.println("The One Armed Bandit give you an item.");
            return true; 
        }
    }

    /**
     * Return the new Item from the bandit
     * 
     * @throws InvocationTargetException .
     * @throws IllegalArgumentException . 
     * @throws IllegalAccessException .
     * @throws InstantiationException .
     */
    protected Items giveObject()
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return AllItems.randJustItem().newInstance(RandomRange.getRandomNumber(5));
    }

}