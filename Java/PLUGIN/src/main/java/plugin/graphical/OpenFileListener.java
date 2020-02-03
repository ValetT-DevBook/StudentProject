package plugin.graphical;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Allows to create an OpenFileListener
 */
public class OpenFileListener implements ActionListener{

    private EditableTextWindow window;

    /**
     * Constructor of the class
     * @param window a window
     */
    public OpenFileListener(EditableTextWindow window) {
        super();
        this.window = window;
    }

    @Override
    /**
     * Allow to do an action
     * @param e an action
     */
    public void actionPerformed(ActionEvent e) {
        StringBuffer sb = new StringBuffer("");
        JFileChooser choo = new JFileChooser( new File("."));
        int i = choo.showOpenDialog(this.window.getTextArea());
        if ( i == 0) {
            try {
                BufferedReader buff = new BufferedReader(new FileReader(choo.getSelectedFile()));
                String s;
				while ( (s = buff.readLine()) != null) 
                    sb.append(s + '\n');
                buff.close();
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(this.window.getTextArea(), "Error on selected file.");
            }
            this.window.getTextArea().setText(sb.toString());
        }
    }

    
}