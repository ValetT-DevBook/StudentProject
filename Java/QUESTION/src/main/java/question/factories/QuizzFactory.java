package question.factories;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import question.exceptions.NoBuildQuestionException;
import question.questions.*;

/**
 * The class for the construction of a quizz.
 */
public class QuizzFactory {

	////////////////
	// ATTRIBUTES //

	public static final QuizzFactory FACTORY = new QuizzFactory();

	//////////////
	// BUILDERS //

	private QuizzFactory() {}

	/////////////
	// METHODS //

	/**
	 * Create a question object.
	 * @param text the string of question's statement
	 * @param answer the string of the correct answer
	 * @param type the type of the question
	 * @param points the number of points from question
	 * @return the question object
	 * @throws NoBuildQuestionException the question can't be constructed
	 */
	public Question createQuestion(String text, String answer, String type, String points)
			throws NoBuildQuestionException {
		try{
			int nbPoints = Integer.parseInt(points);
			return new Question(text, AnswerFactory.FACTORY.buildAnswer(answer, type), nbPoints);
		}catch(Exception e){
			throw new NoBuildQuestionException(e.getMessage());
		}
	}

	/**
	 * create the quizz from a file's name.
	 * @param fileName the path of the file
	 * @return the quizz object
	 * @throws IOException .
	 * @throws NoBuildQuestionException the question can't be constructed
	 */
	public Quizz createQuizz(String fileName) throws IOException, NoBuildQuestionException {
		int nbLinesError = 1;
		Quizz quizz = new Quizz();

		File source = new File(fileName);
		BufferedReader in = null;

		try{
			in = new BufferedReader(new FileReader(source));
			String text;

			while((text = in.readLine()) != null){
				String answer = in.readLine();
				String type = in.readLine();
				String nbPoints = in.readLine();
				
				if (answer == null | nbPoints == null){
					throw new IOException("Bad format.");
				}
				quizz.addQuestion(this.createQuestion(text, answer, type, nbPoints));
			}

			nbLinesError += 4;
		}catch(FileNotFoundException e){
			throw new IOException(e + "\nCheck the question in the lines n°" + nbLinesError + " in your quizz's file.");
		}
		finally{
			in.close();
		}
		return quizz;
	}
}
