package dungeon.characters;

import dungeon.AdventureGame;
import dungeon.exceptions.ElementNotFoundException;

/**
 * Class GameCharacter that allows to set different character for the game
 */
public abstract class GameCharacters{

    /** ATTRIBUTES */
    protected int life;
    protected int strength;
    protected int gold;
    protected String name;
    protected AdventureGame game;

    /** BUILDERS */
    
    /**
     * Build a game character
     * @param life life point
     * @param strength strength point
     * @param gold gold in a bag
     * @param name  the name of the character
     * @param game the party
     */
    public GameCharacters(int life, int strength, int gold, String name ,AdventureGame game){
        this.life = life;
        this.strength = strength;
        this.gold = gold;
        this.name = name;
        this.game = game;
    }

    /** METHODS */

    // GETTERS

    /**
     * Give the life point of the character
     * @return life points
     */
    public int getLife(){
        return this.life;
    }

    /**
     * Give the strength of the character
     * @return strength points
     */
    public int getStrength(){
        return this.strength;
    }

    /**
     * Give the gold carried by the character
     * @return nulber of gold
     */
    public int getGold(){
        return this.gold;
    }

    /**
     * Give the name of the character
     * @return String name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Give the game
     * @return the game
     */
    public AdventureGame getAdventureGame(){
        return this.game;
    }

    // CHANGERS

    /**
     * Change the life point of the character
     * @param i the new life point
     */
    public void changeLife(int i){
        this.life += i;
    }

    /**
     * Change the strength of the character
     * @param i the new strength point
     */
    public void changeStrength(int i){
        this.strength += i;
    }

    /**
     * Change the gold of the character
     * @param i the new gold
     */
    public void changeGold(int i){
        this.gold += i;
    }

    /**
     * Set a name of the charactere
     * @param name the name
     */
    public void changeName(String name){
        this.name = name;
    }


    // OTHERS

    /**
     * Lets know if a character is alive
     * @return * true if is alive
     *         * false if is dead
     */     
    public boolean isAlive(){
        return (this.life > 0);
    }

    /**
     * Differents actions according to the character
     * @throws ElementNotFoundException if the monster isn't in a room
     */
    public abstract void die() throws ElementNotFoundException;

    /**
     * attack an other character
     * @param c character who's attacked
     * @throws ElementNotFoundException if the monster isn't in a room
     */
    public void attack(GameCharacters c) throws ElementNotFoundException{
        System.out.println(this.name + " attack " + c.getName() + " and take " + this.getStrength() + " damage.");
        c.changeLife(-this.getStrength());
    }   

    /**
     * Give the name, the health, the strength and the gold of the character
     * @return String A description of the character
     */
    public String toString(){
        return "Name : " + this.getName() + "\nHealth : " + this.getLife() + "\nStrength :  " + this.getStrength() + "\nGold: " +this.getGold() + "\n";
    }

    /**
     * Compare two objects 
     * @param o An object to compare
     * @return boolean *true if same object
     *                 *flase if different object
     */
    public boolean equals(Object o){
        if (o instanceof GameCharacters){
            GameCharacters other = (GameCharacters) o;
            return this.life == other.getLife() &&
                this.gold == other.getGold() &&
                this.name .equals(this.getName()) &&
                this.strength == other.getStrength();
        }else{
            return false;
        }
    }
    
}