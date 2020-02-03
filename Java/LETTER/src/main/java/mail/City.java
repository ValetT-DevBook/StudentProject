package mail;

import java.util.*;

import mail.exceptions.NotEnoughMoneyException;
import mail.letters.*;
import mail.Inhabitant;

/**
 * The city class
 */
public class City {

   // ATTRIBUTES

   protected List<Inhabitant> inhabitants;
   protected Collection<Letter<?>> mailbox;
   protected String name;

   // BUILDERS

   public City(String name) {
      this.name = name;
      this.mailbox = new LinkedList<Letter<?>>();
      this.inhabitants = new ArrayList<Inhabitant>();
   }

   // GETTERS

   /**
    * Give the name of the city
    * 
    * @return name : name of the city
    */
   public String getNameOfCity() {
      return this.name;
   }

   /**
    * Give the list of the inhabitant in the city
    * 
    * @return List : List of the inhabitant
    */
   public List<Inhabitant> getInhabitant() {
      return this.inhabitants;
   }

   /**
    * Give the list of letters
    * 
    * @return List : List of letters
    */
   public Collection<Letter<?>> getMailbox() {
      return this.mailbox;
   }

   /**
    * Get a random inhabitant
    * 
    * @return inhabitant
    */
   public Inhabitant getRandomInhabitant() {
      Random rand = new Random();
      int randInt = rand.nextInt(this.inhabitants.size());
      return this.getInhabitant().get(randInt);
   }

   // METHODS

   /**
    * Add a inhabitant in the city
    * 
    * @param inhabitant the inhabitant
    */
   public void addInhabitant(Inhabitant inhabitant) {
      this.inhabitants.add(inhabitant);
   }

   /**
    * Remove a inhabitant in the city
    * 
    * @param inhabitant the inhabitant
    */
   public void removeInhabitant(Inhabitant inhabitant) {
      this.inhabitants.remove(inhabitant);
   }

   /**
    * Add a letter in the mailbox for the sending
    * 
    * @param letter the letter
    */
   public void addLetter(Letter<?> letter) {
      this.mailbox.add(letter);
   }

   /**
    * Distribute the letters stocked
    * 
    * @throws NotEnoughMoneyException Exception if the sender not enough money
    */
   public void distributeLetters() throws NotEnoughMoneyException {
      Collection<Letter<?>> letterBag = this.mailbox;
      this.mailbox  = new LinkedList<Letter<?>>();
      for (Letter<?> l : letterBag) {
          System.out.println(l.getReceiver().getNameOfInhabitant() + " receives " + l.getContent() + " from " + l.getSender().getNameOfInhabitant());
          l.getReceiver().receivesLetter(l);
          l.getReceiver().getCity().getMailbox().remove(l);
      }
   }

}
