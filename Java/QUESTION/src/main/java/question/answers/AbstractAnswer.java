package question.answers;

/**
 * The abstract class for differents answers.
 */
public abstract class AbstractAnswer<T> implements Answer{

    ///////////////
    // ATRIBUTES //

    protected T answer;

    //////////////
    // BUILDERS //

    public AbstractAnswer(T t){
        this.answer = t;
    }

    /////////////
    // METHODS //

    /**
     * Give the type of answer to enter
     * @return a type of answer to enter
     */
    public abstract String getType();

    /**
     * Allows to know if the answer enter by user is good type
     * @param s answer enter by the user
     * @return *true if it's *false otherwise
     */
    public abstract boolean hasGoodType(String s);

    /**
     * Compare the answer enter by user to the correct answer
     * @param s a string enter by user
     * @return *true if correct answer *false otherwise
     */
    public boolean isCorrect(String s){
        return this.answer.toString().toLowerCase().equals(s.toLowerCase());
    }

    /**
     * Give the correct answer
     * @return the correct answer
     */
    public String getCorrectAnswer(){
        return this.answer.toString();
    }

}