package mail.letters;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import mail.Inhabitant;
import mail.City;
import mail.contents.Content;
import mail.exceptions.NotEnoughMoneyException;

/**
 * Unit test for Letters
 */
public abstract class LetterTest{
	
	// Abstracts Functions

    /**
     * Abstract creation for the specifics letters' tests
     * @return the letter
     */
    public abstract Letter<? extends Content> createLetter();

    public abstract void theCostIsCorrect(); 
    public abstract void theActionIsCorrect();

    // Attributes
    
    protected Inhabitant receiver;
    protected Inhabitant sender;
    protected Letter<? extends Content> letter;

    // Tests' Initialization
    
    @Before
    public void init(){
        City c = new City("TimoLand");
        this.receiver = new Inhabitant("Timo", c);
        this.sender = new Inhabitant("Leon", c);
        this.letter = createLetter();
    }

    //Tests
    
    @Test
    public void getReceiverIsTheGoodGuy(){
        assertEquals(this.receiver, this.letter.getReceiver());
    }
    
    @Test
    public void getSenderIsTheGoodGuy(){
        assertEquals(this.sender, this.letter.getSender());
    }

    @Test (expected = NotEnoughMoneyException.class)
    public void sendLettersWhenSenderHaveNoMoneyExceptionTest() throws NotEnoughMoneyException {
        this.sender.debit(this.sender.getAccount());
        this.sender.sendsLetter(this.letter);
    }


}
