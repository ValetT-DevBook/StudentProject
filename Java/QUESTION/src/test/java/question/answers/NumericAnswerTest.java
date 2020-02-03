package question.answers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NumericAnswerTest extends AnswerTest {

	protected void initializeDifferentsStrings() {
		this.theCorrectAnswerString = "3";
		this.theInputTrueString = "2";
		this.theInputWrongString = "Albus Dumbledore";
	}

	protected AbstractAnswer<?> createAnswer() {
		return new NumericAnswer(this.theCorrectAnswerString);
	}

	@Test
	public void giveTheCorrectTypeOfAnswerTest() {
		assertEquals(this.answer.getType(), "Numeric");
	}

}
