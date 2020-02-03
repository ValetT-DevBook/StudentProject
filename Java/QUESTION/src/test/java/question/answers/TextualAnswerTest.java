package question.answers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TextualAnswerTest extends AnswerTest {

	protected void initializeDifferentsStrings() {
		this.theCorrectAnswerString = "Harry Potter";
		this.theInputTrueString = "Ron Weasley";
		this.theInputWrongString = "3";
	}
	
	public AbstractAnswer<?> createAnswer() {
		return new TextualAnswer("Harry Potter");
	}

	@Test
	public void giveTheCorrectTypeOfAnswerTest() {
		assertEquals(this.answer.getType(), "Textual");
	}
	
	@Override
	public void comparOfAStringAndTheGoodTypeIsWrongTest() {
		// Test is always true because is always a text
		assertTrue(true);
	}
}
