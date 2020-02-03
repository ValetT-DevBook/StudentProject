package plugin.graphical;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Allows to create a NewListener
 */
public class NewListener implements ActionListener {

    private EditableTextWindow window;

    /**
     * Constructor of the class
     * @param  window a window
     */
    public NewListener(EditableTextWindow window) {
        super();
        this.window = window;
    }

    @Override
    /**
     * Allow to do an action
     * @param e an action
     */
    public void actionPerformed(ActionEvent e) {
        this.window.getTextArea().setText("");
    }

}