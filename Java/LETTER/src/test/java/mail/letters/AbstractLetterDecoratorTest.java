package mail.letters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import mail.Inhabitant;
import mail.contents.Content;
import mail.exceptions.NotEnoughMoneyException;

/**
 * Unit test for Decorator Letter
 */
public abstract class AbstractLetterDecoratorTest extends LetterTest {

	// Mock Objects

	protected class MockContent implements Content {
	}

	protected class MockLetter extends Letter<MockContent> {

		protected int nbAction = 0;

		public MockLetter(Inhabitant sender, Inhabitant receiver, MockContent content) {
			super(sender, receiver, content);
		}

		@Override
		public int getCost() {
			return 0;
		}

		@Override
		public void action() {
			this.nbAction++;
		}
	}

	// Attributes

	protected MockLetter let;

	// Abstracts functions

	public abstract Letter<? extends Content> createLetter();

	// Tests

	@Test
	public void theActionIsCorrect() {
		try {
			this.letter.action();
			assertEquals(1, let.nbAction);
		} catch (NotEnoughMoneyException e) {
			fail();
		}
	}
    
    
    
}
