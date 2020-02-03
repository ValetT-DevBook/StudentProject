package dungeon.characters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dungeon.AdventureGame;
import dungeon.exceptions.ElementNotFoundException;
import dungeon.rooms.Room;


public class MonstersTest extends GameCharacterTest
{
    
    protected GameCharacters createGameCharacters(int l, int s, int g, String name, AdventureGame game){
        return new Monsters(l, s, g, name, game);
    }

    protected void die() throws ElementNotFoundException{
        this.game.currentRoom().removeMonsters((Monsters) this.g2);
    }

    public void MonsterDieAndRemoveTest() throws ElementNotFoundException {
        this.r1.addMonsters((Monsters) this.g2);
        this.g1.attack(this.g2);
        assertFalse(this.r1.getMonsters().contains(this.g2));
    }

    
}
