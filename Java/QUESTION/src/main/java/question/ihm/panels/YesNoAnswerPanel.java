package question.ihm.panels;

import java.awt.Container;
import java.awt.*;
import javax.swing.*;

import question.answers.YesNoAnswer;
import question.questions.Question;

/**
 * The class for the Yes or No answer panel.
 */
public class YesNoAnswerPanel extends AbstractRadioButtonPanel {

	//////////////
	// BUILDERS //
	
	public YesNoAnswerPanel(Question q) {
		super(q);
	}

	/////////////
	// METHODS //

    /**
     * create the container with the field of answer
     * @return the component of the answer's field
     */
	@Override
	protected JComponent createAnswerField() {
		Container content = new JPanel();
		content.setLayout(new GridLayout(2, 1));
		
		ButtonGroup bg = new ButtonGroup();
		
		JRadioButton yes = new JRadioButton("Yes");
		JRadioButton no = new JRadioButton("No");
		
		yes.addActionListener(new CheckRadioButtonAnswer());
		no.addActionListener(new CheckRadioButtonAnswer());
		
		bg.add(yes);
		bg.add(no);
		
		content.add(yes);
		content.add(no);
		
		return (JComponent) content;
	}

    /**
     * A main for the graphical test
     * @param argv the arguments
     */
    public static void main(String argv[]) {
        JFrame f = new JFrame("test YesNoAnswerPanel");
        f.setLocation(200, 300);
        
        YesNoAnswer answer = new YesNoAnswer("yes");
        Question question = new Question("salut!", answer, 3);
        YesNoAnswerPanel pan = new YesNoAnswerPanel(question);

		f.setContentPane(pan.createQuestionPanel());

		f.pack();
		f.setVisible(true);
    }

}
