package question.ihm.panels;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import question.answers.NumericAnswer;
import question.questions.Question;

/**
 * The class for a numeric answer panel.
 */
public class NumericAnswerPanel extends AbstractPanel {

	//////////////
	// BUILDERS //

	public NumericAnswerPanel(Question q) {
		super(q);
	}

	/////////////
	// METHODS //

	/**
     * Create a label with the statement of question
     * @return the jlabel with statement
     */
	protected JComponent createAnswerField() {
		SpinnerModel model = new SpinnerNumberModel(0, -1000000000, 1000000000, 1);     
		JSpinner spinner = new JSpinner(model);
		spinner.setPreferredSize(DIMENSION_ANSWER);
		model.addChangeListener(new CheckSpinnerAnswer());
		return spinner;
	}

    /**
     * A main for the graphical test
     * @param argv the arguments
     */
    public static void main(String argv[]) {
        JFrame f = new JFrame("test NumericAnswerPanel");
        f.setLocation(200, 300);
        
        NumericAnswer answer = new NumericAnswer("3");
        Question question = new Question("salut!", answer, 3);
        NumericAnswerPanel pan = new NumericAnswerPanel(question);

		f.setContentPane(pan.createQuestionPanel());

		f.pack();
		f.setVisible(true);
	}
	
	////////////
	// EVENTS //
	
	/**
	 * The intern class for the events of spinner.
	 */
    private class CheckSpinnerAnswer implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent arg0) {
			String s = ((SpinnerModel) arg0.getSource()).getValue().toString();
			if (NumericAnswerPanel.this.question.getAnswer().isCorrect(s))
				NumericAnswerPanel.this.answer = true;
			else
				NumericAnswerPanel.this.answer = false;
			
		}
	}

    
}
