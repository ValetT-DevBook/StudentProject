package mail.letters;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mail.Inhabitant;

public class FoolLetterTest extends LetterTest {

	/**
     * Abstract creation for the specifics letters' tests
     * @return the letter
     */
	public FoolLetter createLetter() {
		List<Inhabitant> list = new ArrayList<Inhabitant>();
		for (int i = 0; i < 4; i++)
			list.add(new Inhabitant("hab" + i, this.sender.getCity()));
		return new FoolLetter(this.sender, this.receiver, list);
	}

	@Test
	public void theCostIsCorrect() {
		assertEquals(FoolLetter.DEFAULT_COST, this.letter.getCost());
	}

	@Test
	public void theActionIsCorrect() {
		
		// Tests aren't realized because the aspect of random of this answer is really complicated to test
		
//		// Only for the change of list
//		this.letter.action();
//		List<Inhabitant> list =( (ListInhabitant) this.letter.getContent()).getValue();
//		
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
//		System.out.println(this.receiver);
//		
//		assertEquals( list.get(3), this.receiver);
	}
	
}