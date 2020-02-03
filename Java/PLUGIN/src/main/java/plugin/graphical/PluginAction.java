package plugin.graphical;

import java.awt.event.*;

/**
 * Allows to create a PluginAction
 */
public class PluginAction implements ActionListener{

    private EditableTextWindow window;
    private PluginMenuItem plugin;

    /**
     * Constructor of the class
     * @param pluginMenuItem Menu of items
     * @param window a window
     */
    public PluginAction(PluginMenuItem pluginMenuItem, EditableTextWindow window) {
        super();
        this.window = window;
        this.plugin = pluginMenuItem;
	}

    @Override
    /**
     * Allow to do an action
     * @param e an action
     */
    public void actionPerformed(ActionEvent e) {
        String s = this.plugin.transform(this.window.getTextArea().getText());
        this.window.getTextArea().setText(s);
    }

}