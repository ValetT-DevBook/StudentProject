package question.exceptions;

/**
 * The exception if a question can't be constructed.
 */
public class NoBuildQuestionException extends Exception{

    public NoBuildQuestionException(String e) {
        super(e);
    }

}
