package mail;

import mail.exceptions.NotEnoughMoneyException;
import mail.letters.Letter;


/**
 * The inhabitant class
 */
public class Inhabitant {

	//ATTRIBUTES

	public static final int DEFAULT_AMOUNT = 5000 ;

	private City city ;
	private String name;
	private int account ;

	//BUILDERS

	public Inhabitant(String name, City city) {
      this.name = name ;
      this.city = city ;
      this.account = DEFAULT_AMOUNT ;
	}

	//GETTERS

	/**
	 * Give the name of the inhabitant
	 * @return name : name of the inhabitant
	 */
	public String getNameOfInhabitant(){
		return this.name;
	}
	
	/**
	 * Give the city
	 * @return city : the city
	 */
	public City getCity(){
		return this.city;
	}

	/**
	 * Give the amount of the account
	 * @return account : the amount of the account
	 */
	public int getAccount(){
		return this.account;
	}

	/**
	 * Debit the account
	 * @param amount : amount of the debit
	 */
	public void debit(int amount){
		this.account -= amount;
	}

	/**
	 * Credit the account
	 * @param amount : amount of the credit
	 */
	public void credit(int amount){
		this.account += amount;
	}

	//METHODS

	/**
	 * The inhabitant receive the letter and open it
	 * 
	 * @param letter the letter
	 * @throws NotEnoughMoneyException Exception if the sender not enough money
	 */
	public void receivesLetter(Letter<?> letter) throws NotEnoughMoneyException {
    	letter.action() ;
	}
	
	/**
	 * The inhabitant sends a letter and lost the cost of letter
	 * @param letter letter to send
	 * @throws NotEnoughMoneyException Exception if the sender not enough money
	 */
    public void sendsLetter(Letter<?> letter) throws NotEnoughMoneyException{
		if(this.account - letter.getCost() > 0){
			this.debit(letter.getCost()) ;
			letter.getReceiver().getCity().addLetter(letter);
	    }else{
			throw new NotEnoughMoneyException("You haven't got enghout money on your bank account.");
		}
	}
    
    /**
     * Return if objects are equals
     * @param o an object
     * @return 	* true, if objects are equals
     * 			* false otherwise
     */
	public boolean equals(Object o) {
		if (o instanceof Inhabitant) {
			Inhabitant other = (Inhabitant) o;
			if ( 	this.name.equals(other.getNameOfInhabitant())
					&& this.account == other.getAccount()
					&& this.city.getNameOfCity().equals(other.getCity().getNameOfCity()) )
				return true;
			else
				return false;
		} else 
			return false;
	}

}
