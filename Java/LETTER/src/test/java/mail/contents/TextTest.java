package mail.contents;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import mail.contents.*;

public class TextTest extends AbstractContentTest{

    protected Text creatAbstractContent() {
        return new Text("Hello");
    }

    @Test
    public void getValueTest(){
        assertEquals(c.getValue(), "Hello");
    }


    
}