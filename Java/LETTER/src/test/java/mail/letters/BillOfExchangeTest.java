package mail.letters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import mail.exceptions.NotEnoughMoneyException;

/**
 * Unit test for Bill Of Exchange
 */
public class BillOfExchangeTest extends LetterTest {

    // Attributes

    protected int price;

    // Abstract function of superClasses

    /**
     * Abstract creation for the specifics letters' tests
     * 
     * @return the letter
     */
    public BillOfExchange createLetter() {
        this.price = this.sender.getAccount() / 2;
        return new BillOfExchange(this.sender, this.receiver, this.price);
    }

    // Tests

    @Test
    public void theCostIsCorrect() {
        int res = BillOfExchange.DEFAULT_COST + this.price / 100;
        assertEquals(res, this.letter.getCost());
    }

    @Test
    public void theActionIsCorrect() {
        try {
            int defaultCount = this.sender.getAccount();
            this.letter.action();
            assertEquals(this.sender.getAccount(), defaultCount - this.price);
            assertEquals(this.receiver.getAccount(), defaultCount + this.price - ThanksLetter.DEFAULT_COST);
        } catch (NotEnoughMoneyException e) {
            fail();
        }
    }

}
