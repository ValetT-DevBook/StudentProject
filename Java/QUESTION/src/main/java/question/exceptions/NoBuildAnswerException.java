package question.exceptions;

/**
 * The exception if an answer can't be constructed.
 */
public class NoBuildAnswerException extends Exception {

    public NoBuildAnswerException(String e) {
        super(e);
    }

}