package question.answers;

/**
 * The class of multiple possibles answers.
 */
public class MultipleAnswer extends AbstractMultipleAnswer{

	////////////////
	// ATTRIBUTES //
	
	protected final static String SEPARATOR = " ; ";

	//////////////
	// BUILDERS //

    public MultipleAnswer(String s) {
        super(s.split(SEPARATOR));
	}
	
	/////////////
	// METHODS //
    
    /**
     * Give the type of answer to enter
     * @return a type of answer to enter
     */
	public String getType() {
		return this.answer.length + " Possible(s) Answer(s)" ;
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

	/**
     * Compare the answer enter by user to the correct answer
     * @param s a string enter by user
     * @return *true if correct answer *false otherwise
     */
	@Override
	public boolean isCorrect(String s){
		boolean res = false;
		for (String s2 : this.answer)
			if (s.toLowerCase().equals(s2.toLowerCase())) 
				res = true;
		return res;
    }

	/**
     * Give the correct answer
     * @return the correct answer
     */
	@Override
	public String getCorrectAnswer(){
        return String.join(SEPARATOR, this.answer);
    }
    
}