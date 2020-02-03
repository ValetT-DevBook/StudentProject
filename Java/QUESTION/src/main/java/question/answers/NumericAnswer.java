package question.answers;

/**
 * The class of numerical answer.
 */
public  class NumericAnswer extends AbstractAnswer<Integer>{

    //////////////
    // BUILDERS //

    public NumericAnswer(String s) {
        super(Integer.parseInt(s));
    }

    /////////////
    // METHODS //

    /**
     * Give the type of answer to enter
     * @return a type of answer to enter
     */
    public String getType() {
        return "Numeric";
    }

    /**
     * Allows to know if the answer enter by user is good type
     * @param s answer enter by the user
     * @return *true if it's *false otherwise
     */
    public boolean hasGoodType(String s) {
        if (s == null)
			return false;
    	try {
    		Integer.parseInt(s);
    		return true;
    	} catch (NumberFormatException e) {
    		return false;
    	}
    }




}