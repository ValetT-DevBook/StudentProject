package mail.contents;

import static org.junit.Assert.*;
import java.util.*;
import mail.Inhabitant;
import mail.City;
import org.junit.Before;
import org.junit.Test;

public class ListInhabitantTest extends AbstractContentTest{

    private Inhabitant i;
    private City ct;
    private ListInhabitant c;

    protected AbstractContent creatAbstractContent() {
        return new ListInhabitant();
    }

    @Before
    public void init(){
        this.c = (ListInhabitant) this.creatAbstractContent();
        this.ct = new City("RadiatorSprings");
        this.i = new Inhabitant("Martin", ct);
    }

    @Test
    public void AddInhabitantTest(){
       this.c.addInhabitant(this.i);
       assertTrue((this.c.getValue().contains(this.i)));
    }

    @Test
    public void RemoveInhabitantTest(){
        this.c.addInhabitant(this.i);
        this.c.removeInhabitant(this.i);
        assertTrue(this.c.getValue().isEmpty());
    }

    
}