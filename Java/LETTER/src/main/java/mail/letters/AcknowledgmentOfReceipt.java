package mail.letters;


/**
 * The acknowledgement of receipt letter Class
 */
public class AcknowledgmentOfReceipt extends SimpleLetter {

   //BUILDERS

   public AcknowledgmentOfReceipt(RegisteredLetter recommande) {
      super(recommande.getReceiver(), recommande.getSender(), "aknowledgment of receipt for "+ recommande);
   }

   /**
    * The letter is open
    */
    public  void action() {
      this.getReceiver().getCity().getMailbox().add(this);
   }
   
   public String toString(){
      return super.toString() + " aknowledgment of receipt " ;
   }

}
