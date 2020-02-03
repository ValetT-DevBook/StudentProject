package question.answers;

/**
 * The class of answers "Yes or No".
 */
public  class YesNoAnswer extends AbstractAnswer<Boolean>{

    //////////////
    // BUILDERS //

    public YesNoAnswer(String s) {
        super(changeStringToBoolean(s));
    }
    
    /////////////
    // METHODS //

    /**
     * Change the string to boolean 
     * @param s the string
     * @return * true if the string is "yes" or "oui" * false otherwise
     */
    private static boolean changeStringToBoolean(String s) {
		boolean res = false;
    	if (s.toLowerCase().equals("yes") || s.toLowerCase().equals("oui"))
    		res = true;
        return res;
	}

    /**
     * Give the type of answer to enter
     * @return a type of answer to enter
     */
    public String getType() {
        return "Yes or No";
    }

    /**
     * Allows to know if the answer enter by user is good type
     * @param s answer enter by the user
     * @return *true if it's *false otherwise
     */
    public boolean hasGoodType(String s) {
        if (s == null)
			return false;
    	if (s.toLowerCase().equals("yes") || s.toLowerCase().equals("no") || s.toLowerCase().equals("oui") || s.toLowerCase().equals("non"))
    		return true;
    	else
    		return false;
    }

    /**
     * Compare the answer enter by user to the correct answer
     * @param s a string enter by user
     * @return *true if correct answer *false otherwise
     */
    @Override
    public boolean isCorrect(String s){
    	boolean res = false;
    	if (s.toLowerCase().equals("yes") || s.toLowerCase().equals("oui"))
    		res = true;
        return res == this.answer;
    }

    /**
     * Give the correct answer
     * @return the correct answer
     */
    @Override
    public String getCorrectAnswer(){
        if (this.answer)
        	return "Yes";
        else
        	return "No";
    }

}