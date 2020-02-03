package mail.letters;

import mail.exceptions.NotEnoughMoneyException;

/**
 * The urgent letter class
 */
public class UrgentLetter extends AbstractLetterDecorator {

   // BUILDERS

   public UrgentLetter(Letter<?> letter) {
      super(letter);
   }

   // METHODS

   /**
    * Open the letter
    * 
    * @throws NotEnoughMoneyException Exception if the sender not enough money
    */
   public void action() throws NotEnoughMoneyException {
   	super.action() ;
   }

   /**
    * Give the cost of the letter
    */
   public int getCost() {
      return super.getCost() * 2;
   }

   public String toString(){
      return this.getContent().toString() + " URGENT ";
   }

}
