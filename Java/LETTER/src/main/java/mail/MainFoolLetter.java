package mail;

import mail.contents.*;
import mail.exceptions.NotEnoughMoneyException;
import mail.letters.*;

public class MainFoolLetter {
    private static final int MAX_INHABITANT = 100;
    private static int i;

    /**
     * Generate number inhabitant in the city
     * 
     * @param c             city
     * @param maxInhabitant max inhabitant
     */
    public static void generateInhabitant(City c, int maxInhabitant) {
        for (i = 0; i < maxInhabitant; i++) {
            Inhabitant hab = new Inhabitant("hab" + i, c); // Allows to create 100 inhabitant for the simulation
            c.addInhabitant(hab);
        }
    }


    public static void main(String[] args) throws NotEnoughMoneyException {
        System.out.println("Simulation of Fool Letter.");
        City c = new City("RadiatorSprings");
        generateInhabitant(c, MAX_INHABITANT); 
        int j = 1;  

        Inhabitant beginner = c.getRandomInhabitant();

        int max = beginner.getAccount();
        Inhabitant picsou = beginner;

        FoolLetter.initiateFoolLetter(beginner);
        c.distributeLetters();

        while(!c.getMailbox().isEmpty()){
            System.out.println("-----");
            System.out.println("Jour " + j++);
            c.distributeLetters();
        }
        for(Inhabitant i : c.getInhabitant()){
            if(i.getAccount() != Inhabitant.DEFAULT_AMOUNT){
                if(i.getAccount() > max){
                    max = i.getAccount();
                    picsou = i;
                }else{
                    System.out.println("Nobody give an answer");
                }
            }
        }
        System.out.println(picsou.getNameOfInhabitant() + " acount : " + picsou.getAccount());


    }

}