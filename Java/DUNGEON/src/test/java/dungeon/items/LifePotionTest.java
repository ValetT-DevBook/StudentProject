package dungeon.items;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dungeon.characters.Players;


public class LifePotionTest extends PotionTest
{
    protected Potion createPotion(int n){
        return new LifePotion(n);
    }

    protected boolean checkPlayer(Players p, int n, int life, int str){
        return p.getLife() == life + n;
    }


}
