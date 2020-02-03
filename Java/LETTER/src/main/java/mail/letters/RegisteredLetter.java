package mail.letters;

import mail.exceptions.NotEnoughMoneyException;

/**
 * The registered letter class
 */
public class RegisteredLetter extends AbstractLetterDecorator {

   // ATTRIBUTES

   protected final static int DEFAULT_TAX = 15;

   // BUILDERS

   public RegisteredLetter(Letter<?> letter) {
      super(letter);
   }

   // METHODS

   /**
    * Open the letter And the receiver send a AcknowlegmentOfReceipt
    * 
    * @throws NotEnoughMoneyException Exception if the sender not enough money
    */
   public void action() throws NotEnoughMoneyException {
   	super.action() ;
      this.getReceiver().getCity().addLetter(new AcknowledgmentOfReceipt(this)) ;
   }

   /**
    * Give the cost of the letter
    */
   public int getCost() {
      return DEFAULT_TAX + super.getCost();
   }

   public String toString(){
      return super.toString() + " registered letter ";
   }
}
