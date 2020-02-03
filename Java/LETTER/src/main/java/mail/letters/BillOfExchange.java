package mail.letters;

import mail.Inhabitant;
import mail.contents.Number;
import mail.exceptions.NotEnoughMoneyException;

/**
 * The bill of Exchange letter Class
 */
public class BillOfExchange extends Letter<Number> {

	// ATTRIBUTES

	protected static final int DEFAULT_COST = 1;

	// BUILDERS

	public BillOfExchange(Inhabitant sender, Inhabitant receiver, int content) {
		super(sender, receiver, new Number(content));
	}

	// METHODS

	/**
	 * Open the letter Take the amount on the sender and give to the receiver The
	 * receiver send a thanks letter
	 * 
	 * @throws NotEnoughMoneyException Exception if the sender not enough money
	 */
	public void action() throws NotEnoughMoneyException {
	    int amount =  this.getContent().getValue() ;
	    this.getSender().debit(amount) ;
	    this.getReceiver().credit(amount) ;
		this.getReceiver().sendsLetter((new ThanksLetter(this)));
		System.out.println(">>> " +this.getReceiver().getNameOfInhabitant() + " sends a thanks letter to " + this.getSender().getNameOfInhabitant() + " for " + this.getContent().getValue() + "$" + " (cost : " + this.getCost() + ")");
	}

	/**
	 * Give the cost of the letter
	 * @return the price
	 */
	public int getCost(){
		return DEFAULT_COST + this.getContent().getValue() / 100 ;
	}

	public String toString(){
		return super.toString() + " bill of exchange " ;
	 }


	
}
