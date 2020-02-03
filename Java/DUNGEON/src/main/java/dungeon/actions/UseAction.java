package dungeon.actions;

import java.util.List;

import dungeon.characters.Players;
import dungeon.items.Items;
import dungeon.scanner.ScannerChoice;

/**
 * Class UseAction which allows to use an item
 */
public class UseAction implements Action{
    int index;
     /**
     * Allow to execute an action
     * @param p player make an antion
     */
    public void executeAction(Players p) {
        System.out.println("You decide to use an item.");
        List<Items> items = p.getAdventureGame().currentRoom().getItems();

        ScannerChoice<Items> choice = new ScannerChoice<Items>(items);
        Items i = choice.readChoice();

        if (i != null){
            try {
                i.use(p);
            } catch (Exception e) {
                System.out.println("You failed your using.");
            }
        }
    }

    /**
     * Say if an anction is possible
     * @param p player who make an action
     * @return boolean *true is possible
     *                 *false is not possible
     */
    public boolean isPossible(Players p) {
        return !p.getAdventureGame().currentRoom().getItems().isEmpty();
    }

    /**
     * Create a word about the action
     * @return String The action
     */
    public String toString(){
        return "Use";
    }
}