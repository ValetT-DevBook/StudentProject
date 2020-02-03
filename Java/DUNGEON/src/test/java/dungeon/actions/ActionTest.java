package dungeon.actions;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import dungeon.AdventureGame;
import dungeon.characters.Players;
import dungeon.exceptions.AlreadyHaveANeighborRoomException;
import dungeon.exceptions.IsNotDirectionException;
import dungeon.rooms.Room;


public abstract class ActionTest
{
    protected abstract Action createAction();
    protected abstract AdventureGame createAdventureGameWithValidRoom() throws AlreadyHaveANeighborRoomException, IsNotDirectionException;
    protected abstract AdventureGame createAdventureGameWithNoValidRoom() throws AlreadyHaveANeighborRoomException, IsNotDirectionException;

    protected AdventureGame game;

    @Before
    public void init(){
        Room r = new Room(false);
        this.game = new AdventureGame(r);
    }
    
    @Test
    public void actionIsAvailableTest() throws AlreadyHaveANeighborRoomException, IsNotDirectionException {
        Players p = new Players(10, 5, 0, "Timoleon", this.createAdventureGameWithValidRoom());
        Action a = this.createAction();
        assertTrue(a.isPossible(p));
    }

}
