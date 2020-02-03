package dungeon.items;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dungeon.characters.Players;


public class StrengthPotionTest extends PotionTest
{
    protected Potion createPotion(int n){
        return new StrengthPotion(n);
    }

    protected boolean checkPlayer(Players p, int n, int life, int str){
        return p.getStrength() == str + n;
    }

}

