package question.ihm.panels;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

import question.questions.Question;

/**
 * The abstract class for the panel with a textual field.
 */
public abstract class AbstractTextFieldPanel extends AbstractPanel {

	//////////////
	// BUILDERS //

    public AbstractTextFieldPanel(Question q) {
        super(q);
    }

	/////////////
	// METHODS //

	/**
     * create the container with the field of answer
     * @return the component of the answer's field
     */
    protected JComponent createAnswerField(){
        JTextField j = new JTextField();
        j.setPreferredSize(DIMENSION_ANSWER);
        j.addKeyListener(new CheckAnswerListener());
        return j;
    }
	
	////////////
	// EVENTS //
	
	/**
	 * The intern class for the check of correct answer/
	 */
    protected class CheckAnswerListener implements KeyListener {
		public void keyTyped(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
		}
		
		public void keyReleased(KeyEvent e) {
			String s = ((JTextField) e.getSource()).getText();
			if (AbstractTextFieldPanel.this.question.getAnswer().isCorrect(s))
				AbstractTextFieldPanel.this.answer = true;
			else
				AbstractTextFieldPanel.this.answer = false;
		}
	}
}