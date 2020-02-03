package question.questions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

import question.answers.*;
import question.util.ScannerChoice;

/**
 * The class for a quizz.
 */
public class Quizz{

    ////////////////
    // ATTRIBUTES //

    private int points;
    private Collection<Question> questions;
    private BufferedReader in;
    private PrintStream out;

    //////////////
    // BUILDERS //

    public Quizz(PrintStream out, BufferedReader in) {
        this.in = in;
        this.out = out;
        this.points = 0;
        this.questions = new ArrayList<Question>();
        ScannerChoice.SCANNER.setIn(in);
    }
    
    public Quizz(){
        this(System.out, new BufferedReader(new InputStreamReader(System.in)));
    }

    /////////////
    // METHODS //

    /**
     * Add question in a question list
     * @param q question to add
     */
    public void addQuestion(Question q){
        this.questions.add(q);
    }

    /**
     * Add points a the total point
     * @param p points to add
     */
    public void addPoints(int p){
        this.points += p;
    }

    /**
     * Give the total points
     * @return total points
     */
    public int getPoints(){
        return this.points;
    }

    public List<Question> getQuestions() {
        return (List<Question>) this.questions;
    }

    /**
     * Launch the quiz
     */
    public void askAll(){
        ScannerChoice.SCANNER.setIn(in);
		for (Question question : this.questions) {
            this.points += this.ask(question) ;
            out.println("-------------------------------------------------------------");
		}
		out.println("You have " + this.points + " point(s).") ;
    }

    /**
     * Ask one question
     */
    private int ask(Question question){
        AbstractAnswer<?> answer = question.getAnswer() ;
        out.println(question.getStatement()) ;
        
		String userAnswer = ScannerChoice.SCANNER.readChoice(answer, out);
        
		if (answer.isCorrect(userAnswer)) {
			int points = question.getPoints() ;
			out.println("Good Answer, you won " + points + " point(s).") ;
			return points ;
		} else {
			out.println("Wrong Answer, the answer was " + answer.getCorrectAnswer() + ".") ;
			return 0 ;
		}
    }


}