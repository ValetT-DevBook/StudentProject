package dungeon.actions;

import dungeon.AdventureGame;
import dungeon.items.LifePotion;


public class UseActionTest extends ActionTest
{

    protected Action createAction(){
        return new UseAction();
    }

    protected AdventureGame createAdventureGameWithValidRoom(){
        this.game.currentRoom().addItems(new LifePotion(2));;
        return this.game;
    }
    protected AdventureGame createAdventureGameWithNoValidRoom(){
        return this.game;
    }

}

