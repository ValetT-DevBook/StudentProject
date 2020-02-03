package mail.letters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mail.contents.Content;

/**
 * Unit test for urgent Letter
 */
public class UrgentLetterTest extends AbstractLetterDecoratorTest{
	
	//Abstract function of superClasse
		
    public Letter<? extends Content> createLetter() {
        this.let = new MockLetter(this.sender, this.receiver, null);
    	return new UrgentLetter(this.let);
    }
    
    // Tests
    
    @Test
    public void theCostIsCorrect() {
    	assertEquals(this.letter.getCost(), 0);
    }
 
}
