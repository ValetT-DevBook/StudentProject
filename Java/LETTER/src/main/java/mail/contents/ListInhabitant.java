package mail.contents;

import java.util.*;

import mail.Inhabitant;

/**
 * The content of the inhabitant's list
 */
public class ListInhabitant extends AbstractContent<List<Inhabitant>> {

    // BUILDERS

    public ListInhabitant() {
        super(new ArrayList<Inhabitant>());
    }

    public ListInhabitant(List<Inhabitant> inhabitants) {
        super(inhabitants);
    }

    //METHODS

    /**
     * Add an Inhabitant in the end of list 
     * @param inhabitant the inhabitant
     */
    public void addInhabitant(Inhabitant inhabitant){
        this.value.add(inhabitant);
    }

    /**
     * Remove an inhabitant in the list
     * @param inhabitant the inhabtitant 
     */
    public void removeInhabitant(Inhabitant inhabitant){
        this.value.remove(inhabitant);
    }


    /**
     * Give the string of a content
     * @return a string
     */
    public String toString() {
        return "the list of Inhabitant " + super.toString();
    }
}