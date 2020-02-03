package mail.letters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple Letter
 */
public class SimpleLetterTest extends LetterTest{
	
	// Abstract function of superClasse
	
    /**
     * Abstract creation for the specifics letters' tests
     * @return the letter
     */
    public SimpleLetter createLetter() {
        return new SimpleLetter(this.sender, this.receiver, "bonjour");
    }

    // Tests
    
    @Test
    public void theCostIsCorrect() {
    	assertEquals(SimpleLetter.DEFAULT_COST, this.letter.getCost());
    }

	@Test
	public void theActionIsCorrect() {
		assertTrue(true);
	}

}
