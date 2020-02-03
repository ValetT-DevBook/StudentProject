package dungeon.characters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dungeon.AdventureGame;
import dungeon.exceptions.ElementNotFoundException;
import dungeon.rooms.Room;


public class PlayersTest extends GameCharacterTest{
    
    protected GameCharacters createGameCharacters(int l, int s, int g, String name, AdventureGame game){
        return new Players(l, s, g, name, game);
    }
    
    protected void die() throws ElementNotFoundException{
        this.game.isFinished();
    }

    public void PlayerDieTest() throws ElementNotFoundException {
        this.g1.die();
        assertTrue(game.isFinished());
    }

    public void AttackAnMonsterTest() throws ElementNotFoundException {
        this.g1.attack(this.g2);
        assertEquals(this.g2.getLife(), 4);
        assertEquals(this.g1.getLife(), 3);
    }

}
