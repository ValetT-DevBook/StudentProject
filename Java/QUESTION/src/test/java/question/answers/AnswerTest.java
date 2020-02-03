package question.answers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public abstract class AnswerTest {
	
	protected abstract AbstractAnswer<?> createAnswer();
	protected abstract void initializeDifferentsStrings();
	
	protected AbstractAnswer<?> answer;
	protected String theCorrectAnswerString;
	protected String theInputTrueString;
	protected String theInputWrongString;
	
	@Before
	public void init() {
		initializeDifferentsStrings();
		this.answer = createAnswer();
	}
	
	public abstract void giveTheCorrectTypeOfAnswerTest();
	
	@Test
	public void comparOfAStringAndTheGoodTypeIsTrueTest() {
		assertTrue(this.answer.hasGoodType(this.theCorrectAnswerString));
		assertTrue(this.answer.hasGoodType(this.theInputTrueString));
	}

	@Test
	public void comparOfAStringAndTheGoodTypeIsWrongTest() {
		assertFalse(this.answer.hasGoodType(this.theInputWrongString));
	}

	@Test
	public void theAnswerIsCorrectTest() {
		assertTrue(this.answer.isCorrect(this.theCorrectAnswerString));
	}

	@Test
	public void theAnswerIsNotCorrectTest() {
		assertFalse(this.answer.isCorrect(this.theInputTrueString));
	}

	@Test
	public void giveTheCorrectAnswerTest() {
		assertEquals(this.answer.getCorrectAnswer(), this.theCorrectAnswerString);
	}

}
