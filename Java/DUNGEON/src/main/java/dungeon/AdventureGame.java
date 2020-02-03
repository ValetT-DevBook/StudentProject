package dungeon;

import java.util.List;
import java.util.Scanner;

import dungeon.actions.Action;
import dungeon.characters.*;
import dungeon.exceptions.IsNotDirectionException;
import dungeon.items.AllItems;
import dungeon.items.Items;
import dungeon.rooms.*;
import dungeon.scanner.ScannerChoice;
import dungeon.util.RandomRange;

public class AdventureGame {
    // ATTRIBUTS
    private Room room;
    private Players player;
    private int nb_room_total;
    private int nb_room;

    // BUILDER
    /**
     * Build the game
     * 
     * @param startingRoom the first room
     */
    public AdventureGame(Room startingRoom) {
        this.room = startingRoom;
        this.nb_room_total = 10;
        this.nb_room = 0;
        generateDungeon(startingRoom);
    }

    // METHODS

    public void setPlayer(Players p){
        this.player = p;
    }

    /** GETTERS */

    /**
     * Give the player
     * 
     * @return player
     */
    public Players getPlayer() {
        return this.player;
    }

    /**
     * Give the current room
     * 
     * @return room
     */
    public Room currentRoom() {
        return this.room;
    }

    /** SETTERS */

    /**
     * Add a monster in a room
     * 
     * @param m monster to add
     * @param r room
     */
    public void addMonster(Monsters m, Room r) {
        r.addMonsters(m);
    }

    /**
     * Add item in a room
     * 
     * @param i item to add
     * @param r room
     */
    public void addItem(Items i, Room r) {
        r.addItems(i);
    }

    /** OTHERS */

    /**
     * Allows to play
     * @param p the player
     */
    public void play(Players p) {

        List<Action> act = this.player.act();
        ScannerChoice<Action> action = new ScannerChoice<Action>(act);
        Action choice = action.readChoice();
        choice.executeAction(this.player);

    }

    /**
     * Allows to stop the game
     * 
     * @return boolean *true it's finished *false it's not finished
     */
    public boolean isFinished() {
        if (this.currentRoom().isExit() || !this.player.isAlive()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Indicated the direction take by the player
     * 
     * @param d a direction
     * @throws IsNotDirectionException .
     */
    public void playerMoveTo(Direction d) throws IsNotDirectionException {
        System.out.println("You go in " + d.toString() + ".");
        Room r = this.room.getNextRoom(d);
        this.room = r;
    }

    /**
     * Allow to randomize a room
     * @param b boolean
     * @return a random room
     */
    private Room randomizeARoom(boolean b){
        if (b)
            return new Room(true);

        Room r = new Room(false);
        int rand_mons = RandomRange.getRandomNumber(3);
        int rand_items = RandomRange.getRandomNumber(3);

        for(int i = 0; i < rand_mons; i++)
            r.addMonsters(AllMonsters.randomMonsters().createNewMonsters(this));

        for(int j = 0; j < rand_items; j++){
            try{
            r.addItems(AllItems.randAllItem().newInstance(RandomRange.getRandomNumber(1,5)));
            } catch (Exception e) {}
        }

        return r;
    }

    /**
     * Allow to generata a dungeon
     * @param r room
     */
    private void generateDungeon(Room r){
        if ( this.nb_room != this.nb_room_total) {
            boolean b = false;
            int nb_neighbor = RandomRange.getRandomNumber(1, 3);

            while ( nb_neighbor > 0 && this.nb_room != this.nb_room_total ){
                if (this.nb_room == this.nb_room_total-1)
                    b = true;
                Direction d = Direction.random();
                try {
                    r.addNextRoom(d, this.randomizeARoom(b));
                    this.nb_room++;
                    if (RandomRange.getRandomNumber(this.nb_room_total) > this.nb_room)
                        generateDungeon(r.getNextRoom(d));
                    nb_neighbor--;                    
                } catch (Exception e) {}
            }

        }
    }

    public static void main(String[] args){

        AdventureGame game = new AdventureGame(new Room(false));

        Scanner name_request = new Scanner(System.in);
        System.out.println("What's your name ?");
        String name = name_request.nextLine(); 

        Players player = new Players(10, 4, 0, name, game);
        game.setPlayer(player);

        System.out.println("You enter in a dark dungeon. Survive and find the exit.");

        while(!game.isFinished()){
            game.play(player);
        }

        if(player.getLife() <= 0)
            System.out.println("You died.");
        else
            System.out.println("You finished the dungeon with " + player.getGold() + "Gold.");

        name_request.close();

    }

}