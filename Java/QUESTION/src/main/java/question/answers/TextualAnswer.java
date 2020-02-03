package question.answers;

/**
 * the class of textual answers.
 */
public  class TextualAnswer extends AbstractAnswer<String>{

    //////////////
    // BUILDERS //

    public TextualAnswer(String t) {
        super(t);
    }

    /////////////
    // METHODS //

    /**
     * Give the type of answer to enter
     * @return a type of answer to enter
     */
    public String getType() {
        return "Textual";
    }

    /**
     * Allows to know if the answer enter by user is good type
     * @param s answer enter by the user
     * @return *true if it's *false otherwise
     */
    public boolean hasGoodType(String s) {
        if (s == null)
			return false;
        return true;
    }




}