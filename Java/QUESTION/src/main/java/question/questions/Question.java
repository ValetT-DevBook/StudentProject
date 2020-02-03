package question.questions;

import question.answers.*;

/**
 * The class for a question.
 */
public class Question{

    ////////////////
    // ATTRIBUTES //

    private String question;
    private int points;
    private Answer answer;

    //////////////
    // BUILDERS //

    public Question(String q, Answer a, int p ){
        this.question = q;
        this.points = p;
        this.answer = a;
    }

    /////////////
    // METHODS //

    /**
     * Give the statement
     * @return the statement
     */
    public String getStatement(){
        return this.question;
    }

    /**
     * Give the points of the question
     * @return the points
     */
    public int getPoints(){
        return this.points;
    }

    /**
     * Give the answer
     * @return the answer
     */
    public AbstractAnswer<?> getAnswer(){
        return (AbstractAnswer<?>) this.answer;
    }
}