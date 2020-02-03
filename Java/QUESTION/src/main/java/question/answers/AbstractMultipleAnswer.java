package question.answers;

/**
 * The abstract class for answer with multiple answers predifined.
 */
public abstract class AbstractMultipleAnswer extends AbstractAnswer<String[]>{

    //////////////
    // BUILDERS //
	
    public AbstractMultipleAnswer(String[] s) {
        super(s);
    }

}