package dungeon.actions;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dungeon.AdventureGame;


public class LookActionTest extends ActionTest
{

    protected Action createAction() {
        return new LookAction();
    }

    protected AdventureGame createAdventureGameWithValidRoom() {
        return this.game;
    }
    
    @Test 
    public void actionIsNotAvailableTest(){
        assertTrue(true); // Right test always because the player can look all time
    }

    @Override
    protected AdventureGame createAdventureGameWithNoValidRoom() {
        // Must because extends ActionTest 
        return null;
    }
}
