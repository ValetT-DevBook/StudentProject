package question.ihm.panels;

import java.awt.Container;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import question.answers.UniqueMultipleAnswer;
import question.questions.Question;

/**
 * The class for the unique multiple answer panel.
 */
public class UniqueMultipleAnswerPanel extends AbstractRadioButtonPanel {

	//////////////
	// BUILDERS //

	public UniqueMultipleAnswerPanel(Question q) {
		super(q);
	}

	/////////////
	// METHODS //

    /**
     * Create a label with the statement of question
     * @return the jlabel with statement
     */
	@Override
	protected JComponent createAnswerField() {
		String array[] = this.question.getAnswer().getType().split(UniqueMultipleAnswer.SEPARATOR);
		
		Container content = new JPanel();
		content.setLayout(new GridLayout(2, (array.length/2)+1));
		ButtonGroup bg = new ButtonGroup();
		
		for (String s : array) {
			JRadioButton but = new JRadioButton(s);
			but.addActionListener(new CheckRadioButtonAnswer());
			bg.add(but);
			content.add(but);
		}
		
		return (JComponent) content;
	}

    /**
     * A main for the graphical test
     * @param argv the arguments
     */
    public static void main(String argv[]) {
        JFrame f = new JFrame("test UniqueMultipleAnswerPanel");
        f.setLocation(200, 300);
        
		JPanel listeQuestion = new JPanel();
		listeQuestion.setLayout(new BoxLayout(listeQuestion, BoxLayout.PAGE_AXIS));
        
        UniqueMultipleAnswer answer = new UniqueMultipleAnswer("Hello | Coucou | Bonjour");
        Question question = new Question("salut!", answer, 3);
        UniqueMultipleAnswerPanel pan = new UniqueMultipleAnswerPanel(question);
        
		UniqueMultipleAnswer answer2 = new UniqueMultipleAnswer("Hello | Coucou | Bonjour | Testons ça");
        Question question2 = new Question("salut!", answer2, 3);
        UniqueMultipleAnswerPanel pan2 = new UniqueMultipleAnswerPanel(question2);

		listeQuestion.add(pan.createQuestionPanel());
		listeQuestion.add(pan2.createQuestionPanel());
		
		f.setContentPane(listeQuestion);

		f.pack();
		f.setVisible(true);
    }
}
