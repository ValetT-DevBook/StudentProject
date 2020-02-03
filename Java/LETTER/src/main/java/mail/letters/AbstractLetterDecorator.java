package mail.letters;

import mail.exceptions.NotEnoughMoneyException;

/**
 * The abstract class for differents letters' Decorator
 */
public abstract class AbstractLetterDecorator extends Letter<Letter<?>> {

	//BUILDERS

	public AbstractLetterDecorator(Letter<?> letter) {
	      super(letter.getSender(), letter.getReceiver(), letter) ;
	}

	//METHODS
	
	/**
	 * The letter is open
	 * @throws NotEnoughMoneyException Exception if the sender not enough money
	 */
	public void action() throws NotEnoughMoneyException {
		this.getContent().action() ;
	}
	
	/**
	 * Give the cost of the letter
	 * @return the price
	 */
    public int getCost() {
		return this.getContent().getCost(); 
	}
	
}
