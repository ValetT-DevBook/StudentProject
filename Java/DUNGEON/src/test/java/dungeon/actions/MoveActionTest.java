package dungeon.actions;

import dungeon.AdventureGame;
import dungeon.exceptions.AlreadyHaveANeighborRoomException;
import dungeon.exceptions.IsNotDirectionException;

public class MoveActionTest extends ActionTest {

    protected Action createAction() {
        return new MoveAction();
    }

    protected AdventureGame createAdventureGameWithValidRoom() throws AlreadyHaveANeighborRoomException, IsNotDirectionException {
        return this.game;
    }

    protected AdventureGame createAdventureGameWithNoValidRoom() throws AlreadyHaveANeighborRoomException, IsNotDirectionException {
        return this.game;
    }
    
}

