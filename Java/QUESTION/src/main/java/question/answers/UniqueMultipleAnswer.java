package question.answers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The class of answers with multiple answer bu one true. 
 */
public class UniqueMultipleAnswer extends AbstractMultipleAnswer{

    ////////////////
    // ATTRIBUTES //

	public final static String SEPARATOR = " \\| ";

    //////////////
    // BUILDERS //

    public UniqueMultipleAnswer(String s) {
        super(s.split(SEPARATOR));
    }

    /////////////
    // METHODS //

    /**
     * Give the type of answer to enter
     * @return a type of answer to enter
     */
    public String getType() {
    	List<String> l = Arrays.asList(this.answer.clone());
    	Collections.shuffle(l);
        String array[] = new String[l.size()];
        for (int i = 0; i < l.size(); i++) {
            array[i] = l.get(i);
        }
    	return String.join(" | ", array);
    }

    /**
     * Allows to know if the answer enter by user is good type
     * @param s answer enter by the user
     * @return *true if it's *false otherwise
     */
	@Override
	public boolean hasGoodType(String s) {
        if (s == null)
			return false;
		boolean res = false;
		for (String s2 : this.answer)
			if (s.toLowerCase().equals(s2.toLowerCase())) 
				res = true;
		return res;
	}

    /**
     * Compare the answer enter by user to the correct answer
     * @param s a string enter by user
     * @return *true if correct answer *false otherwise
     */
    public boolean isCorrect(String s) {
    	return this.answer[0].toLowerCase().equals(s.toLowerCase());
    }

    /**
     * Give the correct answer
     * @return the correct answer
     */
    public String getCorrectAnswer() {
        return this.answer[0];
    }
    
}