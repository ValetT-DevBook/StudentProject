package question.answers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class YesNoAnswerTest extends AnswerTest {
	
	protected void initializeDifferentsStrings() {
		this.theCorrectAnswerString = "Yes";
		this.theInputTrueString = "No";
		this.theInputWrongString = "Harry";
	}

	protected AbstractAnswer<?> createAnswer() {
		return new YesNoAnswer(this.theCorrectAnswerString);
	}

	@Test
	public void giveTheCorrectTypeOfAnswerTest() {
		assertEquals(this.answer.getType(), "Yes or No");
	}

}
