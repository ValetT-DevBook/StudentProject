package question.answers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UniqueMultipleAnswerTest extends AnswerTest {

	protected AbstractAnswer<?> createAnswer() {
		return new UniqueMultipleAnswer(this.theCorrectAnswerString + " | " + this.theInputTrueString + " | Hermione Granger");
	}

	protected void initializeDifferentsStrings() {
		this.theCorrectAnswerString = "Harry Potter";
		this.theInputTrueString = "Ron Weasley";
		this.theInputWrongString = "Albus Dumbledore";
	}

	@Test
	public void giveTheCorrectTypeOfAnswerTest() {
		String s = this.answer.getType();
		if (s.indexOf(this.theCorrectAnswerString) != -1 && 
				s.indexOf(this.theInputTrueString) != -1 && 
				s.indexOf("Hermione Granger") != -1 &&
				s.indexOf(this.theInputWrongString) == -1)
			assertTrue(true);
		else 
			assertFalse(true);
	}

}
