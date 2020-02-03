package dungeon.items;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dungeon.AdventureGame;
import dungeon.characters.Players;
import dungeon.exceptions.ElementNotFoundException;
import dungeon.rooms.Room;

public class GoldBagTest {
    private Players p;
    private GoldBag g;

    @Before
    public void init() {
        this.p = new Players(10, 5, 0, "Timoleon", new AdventureGame(new Room(false)));
        this.g = new GoldBag(6);
    }

    @Test
    public void goldAddInTheBagOfPlayerTest() throws ElementNotFoundException {
        this.p.getAdventureGame().currentRoom().addItems(this.g);
        this.g.use(this.p);
        assertEquals(this.p.getGold(), 6);
    }

}
