package question.factories;

import java.io.IOException;
import java.lang.reflect.Constructor;

import question.exceptions.NoBuildQuestionException;
import question.ihm.GraphicalQuizz;
import question.ihm.panels.AbstractPanel;
import question.questions.Question;
import question.questions.Quizz;

/**
 * The class for the construction of a graphical quizz.
 */
public class GraphicalQuizzFactory {

	////////////////
	// ATTRIBUTES //

	public static final GraphicalQuizzFactory FACTORY = new GraphicalQuizzFactory();

	//////////////
	// BUILDERS //

	private GraphicalQuizzFactory() {}

	/////////////
	// METHODS //
	
	/**
	 * Create the graphical quizz from a name's file.
	 * @param fileName the path of the file
	 * @return the graphical quizz object 
	 * @throws IOException .
	 * @throws NoBuildQuestionException if a question can't be constructed
	 */
	public GraphicalQuizz createGraphicalQuizz(String fileName) throws IOException, NoBuildQuestionException {
		Quizz quizz = QuizzFactory.FACTORY.createQuizz(fileName);
		GraphicalQuizz graphQuizz = new GraphicalQuizz();
		
		for (Question q : quizz.getQuestions()) {
			String s = q.getAnswer().getClass().getSimpleName();
			try {
				Class<?> cls = Class.forName("question.ihm.panels." + s + "Panel");
				Constructor<?> cs = cls.getConstructor(Question.class);
				graphQuizz.addQuestionPanel((AbstractPanel) cs.newInstance(q));
			} catch (Exception e) {
				throw new NoBuildQuestionException(e.getMessage());
			}
		
		}
		return graphQuizz;
	}
		
}

