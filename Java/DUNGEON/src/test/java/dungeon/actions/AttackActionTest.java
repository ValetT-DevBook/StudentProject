package dungeon.actions;


import dungeon.AdventureGame;
import dungeon.characters.Monsters;


public class AttackActionTest extends ActionTest
{

    protected Action createAction() {
        return new AttackAction();
    }

    protected AdventureGame createAdventureGameWithValidRoom() {
        this.game.currentRoom().addMonsters(new Monsters(2, 1, 10, "Orc", this.game));
        return this.game;
    }
    
    protected AdventureGame createAdventureGameWithNoValidRoom() {
        return this.game;
    }

}
