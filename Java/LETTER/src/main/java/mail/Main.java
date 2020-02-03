package mail;

import java.util.Random;

import mail.contents.*;
import mail.exceptions.NotEnoughMoneyException;
import mail.letters.*;

public class Main {
    private static final int MAX_INHABITANT = 100;
    private static final int NB_DAYS = 5;
    private static int i;

    /**
     * Get a random number between range
     * @param min minimum of the range
     * @param max maximum of the range
     * @return int number
     */
    public static int getRandomIntegerBetweenRange(int min, int max) {
        int x = (int) ((Math.random() * ((max - min) + 1)) + min);
        return x;
    }

    /**
     * Get a random number
     * @param nb int max number for the random
     * @return number a random int
     */
    public static int getRandomNumber(int nb){
        Random rand = new Random();
        int n = rand.nextInt(nb);
        return n;
    }


    /**
     * Generate number inhabitant in the city
     * @param c city
     * @param maxInhabitant max inhabitant
     */
    public static void generateInhabitant(City c, int maxInhabitant){
        for(i = 0; i < MAX_INHABITANT; i++){
            Inhabitant hab = new Inhabitant("hab"+i, c); //Allows to create 100 inhabitant for the simulation
            c.addInhabitant(hab);
        }
    }

    /**
     * Get a random letter
     * @param s sender of the letter
     * @param r receiver of the letter
     * @return new random Letter
     */
    public static Letter<? extends Content> getRandomLetterContent(Inhabitant s, Inhabitant r) {
        int nbRandom = getRandomNumber(2);
        if(nbRandom == 0){
            int ct = getRandomIntegerBetweenRange(2, 10);
            return new  BillOfExchange(s, r, ct);
        }else{
            String ct = "bla bla";
            return new SimpleLetter(s, r, ct);
        }
    }

    /**
     * Get a random letter
     * @param letter letter which get random
     * @return new random Letter
     */
    public static Letter<? extends Content> getRandomLetterAction(Letter<? extends Content> letter) {
        int nbRandom = getRandomNumber(3);
        if(nbRandom == 0){
            return new  RegisteredLetter(letter);
        }else if(nbRandom == 1){
            return new UrgentLetter(letter);
        }else{
            return letter;
        }
    }

    /**
     * Generate one day
     * @param c city
     * @throws NotEnoughMoneyException Exception if the sender not enough money
     */
    public static void generateOneDay(City c) throws NotEnoughMoneyException {
        int nb = getRandomIntegerBetweenRange(5, 10);
            for(int j = 0; j < nb; j++){
                Inhabitant sender = c.getRandomInhabitant();
                Inhabitant receiver = c.getRandomInhabitant();
                Letter<? extends Content> letter = getRandomLetterContent(sender, receiver);
                Letter<? extends Content> rletter = getRandomLetterAction(letter);
                c.addLetter(rletter);
                System.out.println(">>> " + rletter.getSender().getNameOfInhabitant() + " sends a " + rletter.toString() + " to " +rletter.getReceiver().getNameOfInhabitant() + " (cost : " + rletter.getCost() + ")");
            }
    }

    public static void main(String[] args) throws NotEnoughMoneyException {
        System.out.println("Simulation of classical letter.");

        int nbDays = NB_DAYS + 2;
        int nbDaysPast = 1;
        City c = new City("Angrenost");

        generateInhabitant(c, MAX_INHABITANT);

        System.out.println("-----");
        System.out.println("Jour " + (nbDaysPast));
        generateOneDay(c);
        while(nbDays > nbDaysPast){
            System.out.println("-----");
            System.out.println("Jour " + (nbDaysPast  + 1 ));

            if(nbDaysPast < NB_DAYS){
                c.distributeLetters(); //Generate a normal day
                generateOneDay(c);
            }else{
                c.distributeLetters(); //Distrubute the letters and don't take a new
            }
            nbDaysPast++;
        }
    }

}