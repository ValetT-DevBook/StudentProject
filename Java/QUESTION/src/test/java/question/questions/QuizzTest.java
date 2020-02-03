package question.questions;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.StringReader;

import org.junit.Test;

import question.answers.AbstractAnswer;

public class QuizzTest {

    private class MockAnswer extends AbstractAnswer<String> {

        public int nbGoodType;

        public MockAnswer(String s){
            super(s);
            this.nbGoodType = 0;
        }

        public String getType() {
            return "test";
        }

        @Override
        public boolean hasGoodType(String s) {
            if (s == null)
			    return false;
            this.nbGoodType++;
            return !s.equals("b");
        }
        
    }

    @Test (timeout = 1000)
    public void quizzLaunchCorrectlyTest() {
        MockAnswer answer = new MockAnswer("a");
        BufferedReader in = new BufferedReader(new StringReader("b\nb\nb\na\n"));
        Quizz quizz = new Quizz(System.out, in);
        quizz.addQuestion(new Question("hello", answer, 10));
        quizz.askAll();
        assertEquals(answer.nbGoodType, 4);
        assertEquals(quizz.getPoints(), 10);
    }
}