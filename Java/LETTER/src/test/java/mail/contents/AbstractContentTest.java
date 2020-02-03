package mail.contents;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public abstract class AbstractContentTest{

    protected abstract AbstractContent creatAbstractContent();

    protected AbstractContent c;

    @Before
    public void init(){
        this.c = this.creatAbstractContent();
    }
}