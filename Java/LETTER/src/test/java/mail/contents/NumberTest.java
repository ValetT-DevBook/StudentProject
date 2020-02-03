package mail.contents;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NumberTest extends AbstractContentTest{

    protected AbstractContent creatAbstractContent() {
        return new Number(5);
    }


    @Test
    public void getValueTest(){
        assertEquals(c.getValue(), 5);
    }

    
}