package mail.letters;

import mail.Inhabitant;
import mail.contents.Content;
import mail.exceptions.NotEnoughMoneyException;


/**
 * The abstract class for differents letters with T content inside
 * @param <T> the type of content
 */
public abstract class Letter<T extends Content> implements Content {

   //ATTRIBUTES

   protected final T content ;
   protected final Inhabitant receiver ;
   protected final Inhabitant sender ;

   //BUILDERS

   public Letter(Inhabitant sender, Inhabitant receiver, T content) {
      this.sender = sender;
      this.receiver = receiver;
      this.content = content;
   }

   //GETTERS

   /**
    * Give the receiver of the letter
    * @return the receiver object
    */
   public Inhabitant getReceiver() {
      return this.receiver; 
   }

   /**
    * Give the sender of the letter
    * @return the sender object
    */
   public Inhabitant getSender() {
      return this.sender;
   }

   /**
    * Give the content of the letter
    * @return the content
    */
   public T getContent() {
      return this.content;
   }

   /**
    * Give the cost of the letter
    * @return the price
    */
   public abstract int getCost() ;
   
   //METHODS

   /**
    * The Letter is open
    * @throws NotEnoughMoneyException Exception if the sender not enough money
    */
   public abstract void action() throws NotEnoughMoneyException;

   /**
    * Give the string of the letter
    * @return a string
    */
   public String toString() {
      return "";
   }
}
