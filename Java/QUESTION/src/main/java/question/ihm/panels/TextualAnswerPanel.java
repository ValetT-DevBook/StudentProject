package question.ihm.panels;

import javax.swing.JFrame;

import question.answers.TextualAnswer;
import question.questions.Question;

/**
 * The class for textual answer panel.
 */
public class TextualAnswerPanel extends AbstractTextFieldPanel {

    //////////////
    // BUILDERS //

    public TextualAnswerPanel(Question q) {
        super(q);
    }

    /**
     * A main for the graphical test
     * @param argv the arguments
     */
    public static void main(String argv[]) {
        JFrame f = new JFrame("test TextualAnswerPanel");
        f.setLocation(200, 300);
        
        TextualAnswer answer = new TextualAnswer("Hello");
        Question question = new Question("salut!", answer, 3);
        TextualAnswerPanel pan = new TextualAnswerPanel(question);

		f.setContentPane(pan.createQuestionPanel());

		f.pack();
		f.setVisible(true);
    }
}