package dungeon.items;

import java.util.*;

import dungeon.util.RandomRange;

import java.lang.reflect.*;

/**
 * the class who allows to access all items
 */

public class AllItems{

    /**
     * Create a list of constructor of all items except InteractivePeople
     * If you want add items add the line " list.add(YourClass.class.getConstructor(theTypeArgumentsOfConstructor.class));"
     * @return List of constructor of items
     */
    private static List<Constructor<? extends Items>> createListItems()
            throws NoSuchMethodException, SecurityException {

        List<Constructor<? extends Items>> list = new ArrayList<>();

        list.add(LifePotion.class.getConstructor(int.class));
        list.add(StrengthPotion.class.getConstructor(int.class));
        list.add(GoldBag.class.getConstructor(int.class));
        return list;
    }

    /**
     * Create a list of constructor of InteractivePeople
     * If you want add InteractivePeople add the line " list.add(YourClass.class.getConstructor(theTypeArgumentsOfConstructor.class));"
     * @return List of constructor of items
     */
    private static List<Constructor<? extends Items>> createListPeople() throws NoSuchMethodException, SecurityException {

        List<Constructor<? extends Items>> list = new ArrayList<>();
        list.add(OneArmedBandit.class.getConstructor(int.class));
        return list;
    }

    /**
     * Create a random constructor of all items
     * @return Random constructor
     */
    public final static Constructor<? extends Items> randAllItem() {
        List<Constructor<? extends Items>> list = new ArrayList<>();
        try {
            list.addAll(createListItems());
            list.addAll(createListPeople());
        } catch (Exception e) {

            System.out.println(e.toString());
            return null;
        }

        int rand = RandomRange.getRandomNumber(list.size());
        return list.get(rand);
    }

    /**
     * Rand a constructor of one item
     * @return Constructor of item
     */
    public final static Constructor<? extends Items> randJustItem() {
        List<Constructor<? extends Items>> list;
        try {
            list = createListItems();
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }

        int rand = RandomRange.getRandomNumber(list.size());
        return list.get(rand);
    }
}