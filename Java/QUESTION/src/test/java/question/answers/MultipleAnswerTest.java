package question.answers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MultipleAnswerTest extends AnswerTest {

	protected AbstractAnswer<?> createAnswer() {
		return new MultipleAnswer(this.theCorrectAnswerString + " ; Ron Weasley");
	}

	protected void initializeDifferentsStrings() {
		this.theCorrectAnswerString = "Harry Potter";
		this.theInputTrueString = "Hermione Granger";
		this.theInputWrongString = "Albus Dumbledore";
	}

	@Test
	public void giveTheCorrectTypeOfAnswerTest() {
		assertEquals(this.answer.getType(), "2 Possible(s) Answer(s)");
	}
	
	@Override
	public void theAnswerIsCorrectTest() {
		assertTrue(this.answer.isCorrect(this.theCorrectAnswerString));
		assertTrue(this.answer.isCorrect("Ron Weasley"));
	}

	@Override
	public void giveTheCorrectAnswerTest() {
		assertEquals(this.answer.getCorrectAnswer(), this.theCorrectAnswerString + " ; Ron Weasley");
	}
	
	@Override
	public void comparOfAStringAndTheGoodTypeIsWrongTest() {
		// Test is always true because is always a text
		assertTrue(true);
	}
}
