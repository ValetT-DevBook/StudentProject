package question.ihm.panels;

import javax.swing.JFrame;
import javax.swing.JLabel;

import question.answers.*;
import question.questions.Question;

/**
 * The class for a multiple answer question.
 */
public class MultipleAnswerPanel extends TextualAnswerPanel {

	//////////////
	// BUILDERS //

	public MultipleAnswerPanel(Question q) {
		super(q);
	}

	/////////////
	// METHODS //

	/**
     * Create a label with the statement of question
     * @return the jlabel with statement
     */
	@Override
	public JLabel createStatementField() {
		JLabel j = new JLabel(this.question.getStatement() + " (" + this.question.getAnswer().getType()+ ")");
        j.setPreferredSize(DIMENSION_STATEMENT);
        return j;
	}
	
	/**
	* A main for the graphical test
	* @param argv the arguments
	*/
    public static void main(String argv[]) {
        JFrame f = new JFrame("test multipleAnswerPanel");
        f.setLocation(200, 300);
        
        MultipleAnswer answer = new MultipleAnswer("Hello ; Coucou ; Bonjour");
        Question question = new Question("salut!", answer, 3);
        MultipleAnswerPanel pan = new MultipleAnswerPanel(question);
		
		f.setContentPane(pan.createQuestionPanel());

		f.pack();
		f.setVisible(true);
    }
}
