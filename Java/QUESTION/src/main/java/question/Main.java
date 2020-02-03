package question;

import java.io.IOException;

import question.exceptions.NoBuildQuestionException;
import question.factories.GraphicalQuizzFactory;
import question.factories.QuizzFactory;
import question.ihm.GraphicalQuizz;
import question.questions.Quizz;

/**
 * The main script
 */
public class Main {
	
	/**
	 * Display the usage of the jar
	 */
    private static void usage() {
        System.out.println("Usage of Quizz's Jar :");
        System.out.println("\t* the name of quizz's file (respect the format quizz)");
        System.out.println("Optionnal arguments before file's name :");
        System.out.println("\t* -s   for the quizz on shell (by default without argument)");
        System.out.println("\t* -g   for the graphical quizz in another window");
    }

    public static void main(String argv[]) throws IOException, NoBuildQuestionException {
        if (argv.length == 1 || ( argv.length == 2 && argv[0].equals("-s")) ) {
            Quizz quizz = QuizzFactory.FACTORY.createQuizz(argv[argv.length - 1]);
            quizz.askAll();
        } else if (argv.length == 2 && argv[0].equals("-g")) { 
            GraphicalQuizz quizz = GraphicalQuizzFactory.FACTORY.createGraphicalQuizz(argv[1]);
            quizz.launchQuizz();
        } else
        	usage();
    }

}