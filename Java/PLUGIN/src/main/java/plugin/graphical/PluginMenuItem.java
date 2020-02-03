package plugin.graphical;

import javax.swing.JMenuItem;

import plugin.Plugin;

/**
 * Allows to create a plugin menu of items
 */
public class PluginMenuItem extends JMenuItem {

    private static final long serialVersionUID = 1L;
    private Plugin plugin;

    /**
     * Constructor of the class
     * @param plugin a plugin
     * @param window a window
     */
    public PluginMenuItem(Plugin plugin, EditableTextWindow window){
        super(plugin.getLabel());
        this.plugin = plugin;
        this.addActionListener(new PluginAction(this, window));
    }

    /**
     * Transform string message
     * @param s message
     * @return a string
     */
    public String transform(String s) {
        return plugin.transform(s);
    }

}