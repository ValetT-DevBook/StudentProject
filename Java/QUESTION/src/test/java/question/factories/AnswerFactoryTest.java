package question.factories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import question.answers.AbstractAnswer;
import question.exceptions.NoBuildAnswerException;

public class AnswerFactoryTest {
    
    @Test
    public void theCreationOfAnswerIsCorrectTest() {
        try {
            AbstractAnswer<?> answer = (AbstractAnswer<?>) AnswerFactory.FACTORY.buildAnswer("3", "NumericAnswer");
            assertTrue(answer.hasGoodType("3"));
            assertFalse(answer.hasGoodType("n"));
            assertEquals("Numeric", answer.getType());
            assertEquals("3", answer.getCorrectAnswer());
        } catch (Exception e) {
            fail();
        }
    }

    @Test (expected = NoBuildAnswerException.class)
    public void theCreationIncorrectThrowsAExceptionTest() throws NoBuildAnswerException{
        AnswerFactory.FACTORY.buildAnswer("3", "NumericAnswers");
        fail();
    }

}