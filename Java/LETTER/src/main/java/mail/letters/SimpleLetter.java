package mail.letters;

import mail.Inhabitant;
import mail.contents.Text;


/**
 * The simple letter class
 */
public class SimpleLetter extends Letter<Text> {

   //ATTRIBUTES

   protected static final int DEFAULT_COST = 1 ;
   
   //BUILDERS

   public SimpleLetter(Inhabitant sender, Inhabitant receiver, String content) {
      super(sender, receiver, new Text(content)) ;
   }

   /**
    * The letter is open
    */
   public  void action() {}

   /**
    * Give the cost of the letter
    */
   public int getCost() {
      return DEFAULT_COST;
   }

   /**
    * Give the string of the letter
    * @return a string
    */
    public String toString(){
      return super.toString() + "simple letter ";
   }

}
