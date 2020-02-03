package question.ihm.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import question.questions.Question;

/**
 * The Abstract class for question with radio buttons
 */
public abstract class AbstractRadioButtonPanel extends AbstractPanel {

	//////////////
	// BUILDERS //

	public AbstractRadioButtonPanel(Question q) {
		super(q);
	}

	////////////
	// EVENTS //

	/**
	 * The intern class for the input in the radio buttons
	 */
	protected class CheckRadioButtonAnswer implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String s = ((JRadioButton) e.getSource()).getText();
			if (AbstractRadioButtonPanel.this.question.getAnswer().isCorrect(s))
				AbstractRadioButtonPanel.this.answer = true;
			else
				AbstractRadioButtonPanel.this.answer = false;
		}
	}
	
}
