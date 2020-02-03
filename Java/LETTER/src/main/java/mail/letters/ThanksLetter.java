package mail.letters;

import mail.contents.Text;
import mail.exceptions.NotEnoughMoneyException;

/**
 * The thanks letter class
 */
public class ThanksLetter extends SimpleLetter {

   // BUILDERS

   public ThanksLetter(BillOfExchange pn) {
      super(pn.getReceiver(), pn.getSender(), "thanks for " + pn.toString());
   }

   public String toString(){
      return super.toString() + " thanks letter ";
   }
}
