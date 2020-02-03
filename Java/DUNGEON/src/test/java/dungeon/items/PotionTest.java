package dungeon.items;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dungeon.AdventureGame;
import dungeon.characters.Players;
import dungeon.exceptions.ElementNotFoundException;
import dungeon.rooms.Room;

public abstract class PotionTest {
    protected abstract Potion createPotion(int n);

    protected abstract boolean checkPlayer(Players p, int n, int life, int str);

    @Test
    public void playerDrinkAPotionTest() throws ElementNotFoundException {
        Room r = new Room(false);
        int n = 3;
        int life = 10;
        int str = 5;
        Potion po = this.createPotion(n);
        r.addItems(po);
        Players pl = new Players(life, str, 0, "Timoleon", new AdventureGame(r));
        po.use(pl);
        assertTrue(this.checkPlayer(pl, n, life, str));
    }

}

