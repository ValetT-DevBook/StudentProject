package question.ihm.panels;

import java.awt.*;

import javax.swing.*;

import question.answers.MultipleAnswer;
import question.answers.NumericAnswer;
import question.answers.TextualAnswer;
import question.answers.UniqueMultipleAnswer;
import question.answers.YesNoAnswer;
import question.questions.Question;

/**
 * The abstract class for the panel of a question
 */
public abstract class AbstractPanel {

    ////////////////
    // ATTRIBUTES //

    protected Question question;
    protected boolean answer;
    
    protected final static Dimension DIMENSION_ANSWER = new Dimension(100,30);
    protected final static Dimension DIMENSION_STATEMENT = new Dimension(600,30);

    //////////////
    // BUILDERS //
    
    public AbstractPanel(Question q) {
        this.question = q;
        this.answer = false;
    }

    /////////////
    // METHODS //
    
    /**
     * Give if the answer in panel is correct
     * @return * true if it's correct * false otherwise
     */
    public boolean isCorrect() {
    	return this.answer;
    }
    
    /**
     * Give the number of points from the question in panel
     * @return the number of points
     */
    public int getPoints() {
    	return this.question.getPoints();
    }

    /**
     * Create a label with the statement of question
     * @return the jlabel with statement
     */
    protected JLabel createStatementField() {
        JLabel j = new JLabel(this.question.getStatement());
        j.setPreferredSize(DIMENSION_STATEMENT);
        return j;
    }

    /**
     * create the container with the field of answer
     * @return the component of the answer's field
     */
    protected abstract JComponent createAnswerField();

    /**
     * create the panel of question
     * @return the container
     */
    public Container createQuestionPanel() {
        Container content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(this.createStatementField(), BorderLayout.WEST);
        content.add(this.createAnswerField(), BorderLayout.EAST);
        return content;
    }


    /**
     * A main for the graphical test
     * @param argv the arguments
     */
    public static void main(String argv[]) {
        JFrame f = new JFrame("test UniqueMultipleAnswerPanel");
        f.setLocation(200, 300);
        f.setLayout(new BorderLayout());
        f.setSize(800,300);
       
		JPanel listeQuestion = new JPanel();
		listeQuestion.setLayout(new BoxLayout(listeQuestion, BoxLayout.PAGE_AXIS));
        
        UniqueMultipleAnswer answer = new UniqueMultipleAnswer("Hello | Coucou | Bonjour");
        Question question = new Question("salut!", answer, 3);
        UniqueMultipleAnswerPanel pan = new UniqueMultipleAnswerPanel(question);
        
		UniqueMultipleAnswer answer2 = new UniqueMultipleAnswer("Hello | Coucou | Bonjour | Testons ça");
        Question question2 = new Question("salut!", answer2, 3);
        UniqueMultipleAnswerPanel pan2 = new UniqueMultipleAnswerPanel(question2);
        
        TextualAnswer answer3 = new TextualAnswer("Hello");
        Question question3 = new Question("salut!", answer3, 3);
        TextualAnswerPanel pan3 = new TextualAnswerPanel(question3);
        
        NumericAnswer answer4 = new NumericAnswer("3");
        Question question4 = new Question("salut!", answer4, 3);
        NumericAnswerPanel pan4 = new NumericAnswerPanel(question4);
        
        MultipleAnswer answer5 = new MultipleAnswer("Hello ; Coucou ; Bonjour");
        Question question5 = new Question("salut!", answer5, 3);
        MultipleAnswerPanel pan5 = new MultipleAnswerPanel(question5);
        
        YesNoAnswer answer6 = new YesNoAnswer("yes");
        Question question6 = new Question("salut!", answer6, 3);
        YesNoAnswerPanel pan6 = new YesNoAnswerPanel(question6);

		listeQuestion.add(pan.createQuestionPanel());
		listeQuestion.add(pan2.createQuestionPanel());
		listeQuestion.add(pan3.createQuestionPanel());
		listeQuestion.add(pan4.createQuestionPanel());
		listeQuestion.add(pan5.createQuestionPanel());
		listeQuestion.add(pan6.createQuestionPanel());
		
		JScrollPane scroll = new JScrollPane(listeQuestion);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
		
		
		f.add(scroll, BorderLayout.NORTH);

//		f.pack();
		f.setVisible(true);
    }

}