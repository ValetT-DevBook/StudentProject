package question.factories;

import java.lang.reflect.*;

import question.answers.Answer;
import question.exceptions.NoBuildAnswerException;

/**
 * The class for the construction of an answer.
 */
public class AnswerFactory {

	////////////////
	// ATTRIBUTES //

	public static final AnswerFactory FACTORY = new AnswerFactory();

	//////////////
	// BUILDERS //

	private AnswerFactory(){}

	/////////////
	// METHODS //

	/**
	 * Build an answer object with specified type
	 * @param answer the string of answer
	 * @param typeQuestion the type of answer
	 * @return the answer object
	 * @throws NoBuildAnswerException throw if the answer can't be create
	 */
	public Answer buildAnswer(String answer, String typeQuestion) throws NoBuildAnswerException {
		try{
			Class<?> cls = Class.forName("question.answers." + typeQuestion);
			Constructor<?> cs = cls.getConstructor(String.class);
			Answer result = (Answer) cs.newInstance(answer);
			return result;
		} catch (Exception e) {
			throw new NoBuildAnswerException("The answer '" + answer + "' can't create. Check your quizz's file.");
		}
	}
}
