package mail.letters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mail.contents.Content;

/**
 * Unit test for registered Letter
 */
public class RegisteredLetterTest extends AbstractLetterDecoratorTest{
	
	// Astracts functions of superClasse
	
    public Letter<? extends Content> createLetter() {
        this.let = new MockLetter(this.sender, this.receiver, null);
    	return new RegisteredLetter(this.let);
    }
    
    //Test
    
    @Test
    public void theCostIsCorrect() {
    	assertEquals(this.letter.getCost(), RegisteredLetter.DEFAULT_TAX);
    }
    
}
