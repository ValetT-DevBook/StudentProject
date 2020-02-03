package question.questions;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import question.answers.AbstractAnswer;

public class QuestionTest {
	
	private class MockAnswer extends AbstractAnswer<String>{

		public MockAnswer(String t) {
			super(t);
		}

		@Override
		public String getType() {
			// TODO Auto-generated method stub
			return "test";
		}

		@Override
		public boolean hasGoodType(String s) {
			return s instanceof String;
		}
		
	}

	private Question q;
	private final String statement = "What Color ?";
	private final int points = 10;
	private final MockAnswer answer = new MockAnswer("red");
	
	@Before
	public void init() {
		this.q = new Question(this.statement, this.answer, this.points);
	}
	
	@Test
	public void giveTheCorrectStatementTest() {
		assertEquals(this.statement, this.q.getStatement());
	}
	
	@Test
	public void giveTheCorrectAmountOfPointsTest() {
		assertEquals(this.points, this.q.getPoints());
	}
	
	@Test
	public void giveTheCorrectAnswerTest() {
		assertEquals(this.answer.getCorrectAnswer(), this.q.getAnswer().getCorrectAnswer());
	}
}
