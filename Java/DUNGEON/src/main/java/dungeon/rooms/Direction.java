package dungeon.rooms;

import dungeon.util.RandomRange;

/**
 * Enum of the differents directions
 */
public enum Direction{
    NORTH ("North"), 
    EAST ("East"), 
    SOUTH ("South"), 
    WEST ("West");

    private String str;

    /**
     * Build a direction
     */
    private Direction(String s){
        this.str = s;
    }

    /**
     * Give the direction
     * @return String the direction
     */
    public String toString(){
        return this.str;
    }

    /**
     * Give a random direction
     * @return a rand direction
     */
    public static Direction random(){
        int n = Direction.values().length -1;
        return Direction.values()[RandomRange.getRandomNumber(n)];
    }

    /**
     * Reverse the direction
     * @return a reverse direction
     */
    public Direction Reverse(){
        int i = this.ordinal();
        int n = Direction.values().length;

        return Direction.values()[(i+2) % n];
    }
}