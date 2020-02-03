package dungeon.util;

/**
 * Allows to give a random number
 */
public class RandomRange
{
    /**
     * Allows to give a random number in an interval
     * @param min minimum
     * @param max maximum
     * @return int a random number
     * @throws IllegalArgumentException .
     */
    public static int getRandomNumber(int min, int max) throws IllegalArgumentException{

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    /**
     * Allows to give a random number between zero and a other number
     * @param max maximum
     * @return int a random number
     * @throws IllegalArgumentException .
     */
    public static int getRandomNumber(int max) throws IllegalArgumentException{
        return RandomRange.getRandomNumber(0, max);
    }

}

