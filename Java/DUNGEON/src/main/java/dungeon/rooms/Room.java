package dungeon.rooms;

import java.util.*;

import dungeon.characters.*;
import dungeon.exceptions.*;
import dungeon.items.*;

/**
 * Class Room allows to create different rooms
 */
public class Room {

    // ATTRIBUTES
    private boolean isExit;
    private List<Monsters> monsters;
    private List<Items> items;
    private Map<Direction,Room> next;

    //BUILDER

    /**
     * Create a room
     * @param b boolean
     */
    public Room(Boolean b){
        this.isExit = b;
        this.monsters = new ArrayList<Monsters>();
        this.items = new ArrayList<Items>();
        this.next = new HashMap<Direction, Room>();
    }

    // GETTERS

    /**
     * Give the list of all monsters in actual room
     * @return list of monters
     */
    public List<Monsters> getMonsters(){
        return this.monsters;
    }

    /**
     * Give the list of all items in actual room
     * @return list of items
     */
    public List<Items> getItems(){// TO DO
        return this.items;
    }

    /**
     * Give the list of all directions in actual room
     * @return list of directions
     */
    public List<Direction> getDirection(){
        List<Direction> direction = new ArrayList<Direction>();
        for ( Direction key : next.keySet() ) {
            direction.add(key);
        }
        return direction;
    }

    /**
     * Give the next room and need a direction
     * @param d direction of the next room
     * @return the next room
     * @throws IsNotDirectionException if the direction choose is not a direction
     */
    public Room getNextRoom(Direction d) throws IsNotDirectionException{
        if(this.next.containsKey(d)){
            return this.next.get(d);
        }else{
            throw new IsNotDirectionException("This direction is not valid");
        }
    }

    // SETTERS

    /**
     * Add a new monster in the room
     * @param m new monster
     */
    public void addMonsters(Monsters m){
        this.monsters.add(m);
    }
    
    /**
     * Add new item in the room
     * @param i new item
     */
    public void addItems(Items i){
        this.items.add(i);
    }

    /**
     * Add new next room in terms of the direction
     * 
     * @param d direction of the room
     * @param r next room
     * @throws IsNotDirectionException .
     * @throws AlreadyHaveANeighborRoomException If they is already a room here
     */
    public void addNextRoom(Direction d, Room r) throws AlreadyHaveANeighborRoomException, IsNotDirectionException {
        if (!this.next.containsKey(d))
            this.next.put(d,r);
        else 
            throw new AlreadyHaveANeighborRoomException("The room have already a neighbor.");
        try {
            r.addNextRoom(d.Reverse(), this);
        } catch (Exception e) {}
    }

    /**
     * Remove the monster of the room
     * @param m the monster
     * @throws ElementNotFoundException if the monster isn't in the list
     */
    public void removeMonsters(Monsters m) throws ElementNotFoundException{
        if(this.monsters.contains(m)){
            this.monsters.remove(m);
        }else{
            throw new ElementNotFoundException("This monster isn't in the room");
        } 
    }  

    /**
     * Remove the monster of the room
     * @param i item remove
     * @throws ElementNotFoundException if the item ins't in the list
     */
    public void removeItems(Items i) throws ElementNotFoundException{
        if(this.items.contains(i)){
            this.items.remove(i);
        }else{
            throw new ElementNotFoundException("This item isn't in the room");
        } 
    }

    //OTHERS

     /**
     * Give if has monsters in the room
     * @return boolean *true if there are monsters
     *                 *false if there aren't monsters
     */
     public boolean hasMonsters(){
        return !this.monsters.isEmpty();
    }

    /**
     * Give if the room is an exit room
     * @return boolean *true if it's an exit room
     *                 *false if it's not an exit room
     */
    public boolean isExit(){
        return this.isExit;
    }

    /**
     * Give the representation of the current room
     * @return String representation of room
     */
    public String toString(){

        String mons = "";
        if (this.monsters.isEmpty())
            mons = "They are no monsters.";
        else {
            for (Monsters m : this.monsters) {
                mons += m.toString() + ", ";
            }
            mons = mons.substring(0, mons.length()-2);
        }


        String it = "";
        if (this.items.isEmpty())
            it = "They are no items.";
        else {
            for (Items i : this.items) {
                it += i.toString() + ", ";
            }
            it = it.substring(0, it.length()-2);
        }


        String dir = "";
        if (this.getDirection().isEmpty())
            dir = "They are no direction.";
        else {
            for (Direction d : this.getDirection()) {
                dir += d.toString() + ", ";
            }
            dir = dir.substring(0, dir.length()-2);
        }

        return "Monsters : " + mons + "\nItems : " + it + "\nDirections : " + dir + "\n";
    }







    
}