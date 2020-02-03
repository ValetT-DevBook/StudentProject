package plugin.graphical;

import javax.swing.JMenuItem;

import plugin.Plugin;
import plugin.observer.FileEvent;
import plugin.observer.FileListener;

/**
 * Allows to create a PluginListener
 */
public class PluginListener implements FileListener {

    private EditableTextWindow window;

    /**
     * Constructor of the class
     * @param window a window
     */
    public PluginListener(EditableTextWindow window) {
        this.window = window;
    }

    /**
     * Allows to create a plugin
     * @param e file event
     * @return a plugin
     */
    private Plugin createPlugin(FileEvent e) {
        String s = e.getFileString().substring(0, e.getFileString().length() - ".class".length());
        try {
            return (Plugin) Class.forName("plugins." + s).newInstance();
        } catch (Exception exc) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    /**
     * Allows to add file
     * @param e file event 
     */
    public void fileAdded(FileEvent e) {
        Plugin plug = this.createPlugin(e);
        this.window.getPluginMenu().add(new PluginMenuItem(plug, this.window));
        JMenuItem help = new JMenuItem(plug.getLabel());
        help.addActionListener(new HelpListener(plug.getMessage(), this.window));
        this.window.getHelpMenu().add(help);
    }

    @Override
    /**
     * Allows to remove file
     * @param e file vent
     */
    public void fileRemoved(FileEvent e) {
        Plugin plug = this.createPlugin(e);
        for(int i = 0; i < this.window.getPluginMenu().getItemCount(); i++) {
            if ( this.window.getPluginMenu().getItem(i).getText().equals(plug.getLabel())){
                this.window.getPluginMenu().remove(i);
                this.window.getHelpMenu().remove(i);
            }
        }
    }

}