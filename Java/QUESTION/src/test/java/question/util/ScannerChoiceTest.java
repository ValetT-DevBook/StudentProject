package question.util;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.StringReader;

import org.junit.Test;

import question.answers.AbstractAnswer;

public class ScannerChoiceTest {

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
    public void theScannerRepeatWhileTheInputIsNotABTest() {
        MockAnswer ans = new MockAnswer("a");
        BufferedReader in = new BufferedReader(new StringReader("b\nb\nb\nb\nb\na\n"));
        ScannerChoice.SCANNER.setIn(in);
        ScannerChoice.SCANNER.readChoice(ans, System.out);
        assertEquals(ans.nbGoodType, 6);
    }

}