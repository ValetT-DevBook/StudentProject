package dungeon.characters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dungeon.AdventureGame;
import dungeon.exceptions.*;
import dungeon.rooms.*;


public abstract class GameCharacterTest{   
    
    protected Room r1;
    protected AdventureGame game;
    protected AdventureGame game2;
    protected GameCharacters g1;
    protected GameCharacters g2;
    protected GameCharacters g3;

    protected abstract GameCharacters createGameCharacters(int l, int s, int g, String name, AdventureGame game);
    protected abstract void die() throws ElementNotFoundException;

    @Before
    public void init(){
        this.r1 = new Room(false);
        this.game = new AdventureGame(r1);
        this.g1 = this.createGameCharacters(5, 6, 7, "Timoleon", this.game);
        this.g2 = this.createGameCharacters(10, 2, 7, "Socrate", this.game);
        AdventureGame game2 = new AdventureGame(new Room(false));
        GameCharacters g3 = this.createGameCharacters(1, 2, 3, "Victim", this.game2);
    }

    @Test
    public void GetValidValuesTest(){
        assertTrue(this.g1.getLife() == 5);
        assertTrue(this.g1.getStrength() == 6);
        assertTrue(this.g1.getGold() == 7);
        assertTrue(this.g1.getName() == "Timoleon");
        assertTrue(this.g1.getAdventureGame() == game);
    }

    @Test
    public void GetInvalidValuesTest(){
        assertFalse(this.g1.getLife() == 1);
        assertFalse(this.g1.getStrength() == 1);
        assertFalse(this.g1.getGold() == 1);
        assertFalse(this.g1.getName() == "Leon");
        assertFalse(this.g1.getAdventureGame() == game2);
    }

    @Test
    public void ChangeValueTest(){
        this.g1.changeGold(3);
        assertEquals(this.g1.getGold(), 10);
        this.g1.changeLife(4);
        assertEquals(this.g1.getLife(), 9);
        this.g1.changeStrength(2);
        assertEquals(this.g1.getStrength(), 8);
        this.g1.changeName("Luke");
        assertEquals(this.g1.getName(), "Luke");
    }

    @Test
    public void IsAliveTest(){
        assertTrue(this.g1.isAlive());
    }

    @Test 
    public void IsNotAliveTest(){
        this.g1.changeLife(-10);
        assertFalse(this.g1.isAlive());
    }

    @Test
    public void AttackAnOtherCharacterTest() throws ElementNotFoundException {
        Monsters m = new Monsters(7, 1, 1, "Mtest", this.game);
        assertEquals(m.getLife(), 7);
        this.g1.attack(m);
        assertEquals(m.getLife(), 1);
    }


    @Test
    public void TwoCharactersAreEquals(){
        GameCharacters g1bis = this.createGameCharacters(5, 6, 7, "Timoleon", game);
        assertTrue(this.g1.equals(g1bis));
    }

    @Test
    public void TwoCharactersAreNotEquals(){
        assertFalse(this.g1.equals(this.g2)); 
    }
}
