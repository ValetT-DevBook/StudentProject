package question.ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import question.ihm.panels.AbstractPanel;

/**
 * The class for the graphical quizz.
 */
public class GraphicalQuizz {

	////////////////
	// ATTRIBUTES //

	private List<AbstractPanel> questions;
	
	//////////////
	// BUILDERS //

	public GraphicalQuizz() {
		this.questions = new ArrayList<AbstractPanel>();
	}
	
	/////////////
	// METHODS //

	/**
	 * Add a question's panel in the quizz
	 * @param pane the question
	 */
	public void addQuestionPanel(AbstractPanel pane) {
		this.questions.add(pane);
	}
	
	/**
	 * create a button for the submit
	 * @return the button object
	 */
	private JButton createSubmitButton() {
		JButton but = new JButton("Submit");
		but.addActionListener(new SubmitAction());
		return but;
	}
	
	/**
	 * create the panel with all questions
	 * @return the panel with questions
	 */
	private JPanel createListQuestionsPanel() {
		JPanel listQuestion = new JPanel();
		listQuestion.setLayout(new BoxLayout(listQuestion, BoxLayout.PAGE_AXIS));

		for (AbstractPanel j : this.questions) {
			listQuestion.add(j.createQuestionPanel());
		}
		return listQuestion;
	}

	
	/**
	 * Check all answers if they are correct
	 * @return the number of total points
	 */
	private int checkAllAnswer() {
		int res = 0;
		
		for(AbstractPanel p : this.questions)
			if( p.isCorrect() )
				res += p.getPoints();
		
		return res;
	} 

	/**
	 * Create the frame with total points
	 * @param points the number of points to display
	 */
	private void createResultPanel(int points) {
		JOptionPane.showMessageDialog(null, "You won " + points + " point(s).");
	}
	
	/**
	 * Launche the graphical quizz
	 */
	public void launchQuizz() {
		JFrame f = new JFrame("Quizz");
		f.addWindowListener(new CloseWindowEvent());
		f.setLocation(100, 300);
		f.setSize(500,500);
        f.setLayout(new BorderLayout());
        
        f.add(this.createListQuestionsPanel(), BorderLayout.NORTH);
        f.add(this.createSubmitButton(), BorderLayout.SOUTH);
		
		f.pack();
		f.setVisible(true);
	}
	
	////////////
	// EVENTS //

	/**
	 * The intern class for the event to submit quizz
	 */
	private class SubmitAction implements ActionListener {	

		public void actionPerformed(ActionEvent e) {
			int points = GraphicalQuizz.this.checkAllAnswer();
			GraphicalQuizz.this.createResultPanel(points);
		}
	}
	
	/**
	 * The intern class for the event to close the quizz
	 */
	private class CloseWindowEvent extends WindowAdapter {
		public void windowClosing(java.awt.event.WindowEvent e) {
			System.exit(0);
		}
	}
	

}
