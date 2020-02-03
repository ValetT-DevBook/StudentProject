package plugin.graphical;

import java.awt.Component;
import java.awt.event.*;

import javax.swing.JOptionPane;

/**
 * Allows to create a HelpLister
 */
public class HelpListener implements ActionListener {

    private String message;
    private Component window;

    /**
     * Constructor of the class
     * @param message help message
     * @param window a window
     */
    public HelpListener(String message, EditableTextWindow window) {
        super();
        this.message = message;
        this.window = window.getHelpMenu();
    }

    @Override
    /**
     * Allow to do an action
     * @param e an action
     */
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this.window, this.message, "Help", 1);
    }

}