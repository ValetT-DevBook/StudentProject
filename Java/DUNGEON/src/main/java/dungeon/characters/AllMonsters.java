package dungeon.characters;

import dungeon.AdventureGame;
import dungeon.util.*;

/**
 * The enum of differents monsters
 * If you want add a new monster, add this form "MONSTER ('name', life, strength, goldmax)"
 * You can change the already exist monster if you want equilibrate the stats
 */

public enum AllMonsters
{
    
    ORC ("Orc", 5, 3, 10),
    SPIDER ("Spider", 2, 1, 2),
    SKELETON ("Skeleton", 4, 2, 5),
    BAT ("Bat", 1, 1, 1),
    GOBLIN ("Goblin", 3, 5, 20),
    WITCH ("Witch", 3, 6, 5),
    WEREWOLF ("Werewolf", 6, 3, 8),
    TROLL ("Troll", 20, 1, 3),
    VAMPIRE ("Vampire", 4, 4, 8),
    SATYR ("Satyr", 5, 2, 6);

    // ATTRIBUTS
    private String name;
    private int life;
    private int strength;
    private int gold;

    //BUILDER
    /**
     * Buil a monster
     * @param s Name
     * @param l Life points
     * @param str Strenght points
     * @param g Gold
     */
    private AllMonsters(String s, int l, int str, int g){
        this.name = s;
        this.life = l;
        this.strength = str;
        this.gold = RandomRange.getRandomNumber(g);
    }

    /** GETTERS */

    /**
     * Give the name
     * @return String name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Give the life points
     * @return int life points
     */
    public int getLife(){
        return this.life;
    }

    /**
     * Give the strength points
     * @return int strength points
     */
    public int getStrength(){
        return this.strength;
    }

    /**
     * Give the gold held
     * @return int gold held
     */
    public int getGold(){
        return this.gold;
    }

    /**
     * Allow to create new monster
     * @param game The Adventure game
     * @return Monsters a new monster
     */
    public Monsters createNewMonsters(AdventureGame game){
        return new Monsters(this.life, this.strength, this.gold, this.name, game);
    }

    /**
     * Give a random monster
     * @return AllMonsters a random monster
     */
    public static AllMonsters randomMonsters(){
        int n = RandomRange.getRandomNumber( AllMonsters.values().length - 1);
        return AllMonsters.values()[n];
    }
}
