package dungeon.rooms;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dungeon.AdventureGame;
import dungeon.characters.Monsters;
import dungeon.exceptions.*; 
import dungeon.items.LifePotion;
import dungeon.items.StrengthPotion;


public class RoomTest
{

    private Room r1;
    private Room r2;

    @Before
    public void init(){
        this.r1 = new Room(false);
        this.r2 = new Room(true);
    }


    //Tests IsExit()

    @Test
    public void roomIsReallyAExitTest(){
        assertTrue(this.r2.isExit());
    }

    @Test
    public void roomIsNotAExitTest(){
        assertFalse(this.r1.isExit());
    }

    //Tests About Room's Monsters

    @Test
    public void roomAddTwoMonstersTest(){
        AdventureGame game = new AdventureGame(this.r1);
        Monsters a = new Monsters(10, 0, 0, "Orc", game);
        Monsters b = new Monsters(10, 0, 0, "Goblin", game);
        this.r1.addMonsters(a);
        this.r1.addMonsters(b);
        assertTrue(this.r1.getMonsters().contains(a));
        assertTrue(this.r1.getMonsters().contains(b));
    }

    @Test
    public void roomHasMonsterTest(){
        Monsters a = new Monsters(10, 0, 0, "Orc", new AdventureGame(this.r1));
        this.r1.addMonsters(a);
        assertTrue(this.r1.hasMonsters());
    }

    @Test
    public void roomHasNoMonsterTest(){
        assertFalse(this.r1.hasMonsters());
    }

    @Test
    public void roomRemoveAMonsterPresentInTheRoomTest() throws ElementNotFoundException {
        Monsters a = new Monsters(10, 0, 0, "Orc", new AdventureGame(this.r1));
        this.r1.addMonsters(a);
        assertTrue(this.r1.hasMonsters());
        this.r1.removeMonsters(a);
        assertFalse(this.r1.hasMonsters());
    }

    @Test(expected = ElementNotFoundException.class)
    public void roomRemoveAMonsterNotPresentInTheRoomTest() throws ElementNotFoundException{
        Monsters a = new Monsters(10, 0, 0, "Orc", new AdventureGame(this.r1));
        this.r1.removeMonsters(a);
        assertFalse(this.r1.hasMonsters());
    }

    // Tests about Item's Room

    @Test
    public void roomAddTwoItemsTest(){
        LifePotion a = new LifePotion(2);
        StrengthPotion b = new StrengthPotion(4);
        this.r1.addItems(a);
        this.r1.addItems(b);
        assertTrue(this.r1.getItems().contains(a));
        assertTrue(this.r1.getItems().contains(b));
    }

    @Test
    public void roomRemoveAItemPresentInTheRoomTest() throws ElementNotFoundException {
        LifePotion a = new LifePotion(2);
        this.r1.addItems(a);
        assertTrue(this.r1.getItems().contains(a));
        this.r1.removeItems(a);
        assertFalse(this.r1.getItems().contains(a));
    }

    @Test(expected = ElementNotFoundException.class)
    public void roomRemoveAItemNotPresentInTheRoomTest() throws ElementNotFoundException{
        LifePotion a = new LifePotion(2);
        this.r1.removeItems(a);
        assertFalse(this.r1.getItems().contains(a));
    }

    // Tests about Neighbor's Room

    @Test
    public void roomAddACorrectRoomTest() throws IsNotDirectionException, AlreadyHaveANeighborRoomException {
        Direction d = Direction.random();
        this.r1.addNextRoom(d, this.r2);
        assertTrue(this.r1.getNextRoom(d).equals(this.r2));
    }

    @Test
    public void neighborRoomHaveCorrectlyAddTheCurrentInItsNeighborhoodTest() throws IsNotDirectionException, AlreadyHaveANeighborRoomException {
        Direction d = Direction.random();
        this.r1.addNextRoom(d, this.r2);
        assertTrue(this.r2.getNextRoom(d.Reverse()).equals(this.r1));
    }

    @Test(expected = AlreadyHaveANeighborRoomException.class)
    public void roomThrowErrorWhenHaveAlreadyANeigborTest() throws AlreadyHaveANeighborRoomException, IsNotDirectionException {
        Direction d = Direction.random();
        this.r1.addNextRoom(d, this.r2);
        Room r3 = new Room(false);
        this.r1.addNextRoom(d, r3);
    }

}

