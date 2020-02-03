package plugin.graphical;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import plugin.PluginFilter;
import plugin.observer.*;

/**
 * Allows to create an EditableTextWindow
 */
public class EditableTextWindow {

    private JFrame window;
    private FileChecker check;
    private JTextArea text;
    private JMenu pluginMenu;
    private JMenu helpMenu;

    /**
     * Constructor of the class
     */
    public EditableTextWindow() {
        this.check = new FileChecker(new PluginFilter());
        this.check.addFileListener(new PluginListener(this));
        this.initializeWindow();
    }

    /**
     * Give the text of the area
     * @return JTextArea text
     */
    public JTextArea getTextArea() {
        return this.text;
    }

    /**
     * Give the plugin menu
     * @return JMenu plugin menu
     */
    public JMenu getPluginMenu() {
        return this.pluginMenu;
    }

    /**
     * Give the help menu
     * @return JMenu help menu
     */
    public JMenu getHelpMenu() {
        return this.helpMenu;
    }

    /**
     * Allows to initialize window
     */
    private void initializeWindow() {
        this.window = new JFrame("Editable Text");
        this.window.addWindowListener(new CloseWindowEvent());
        this.text = new JTextArea(40, 40);
        JScrollPane scroll = new JScrollPane(this.text);
        this.window.setJMenuBar(this.createMenu());
        this.window.getContentPane().add(scroll, "Center");
        this.window.pack();
    }

    /**
     * Allows to create menu
     * @return JMenuBar menu
     */
    private JMenuBar createMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");

        JMenuItem neew = new JMenuItem("New");
        neew.addActionListener(new NewListener(this));
        file.add(neew);

        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(new OpenFileListener(this));
        file.add(open);

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new CloseWindowEvent());
        file.add(exit);

        menu.add(file);
        menu.add(this.pluginMenu = new JMenu("Plugin"));
        menu.add(this.helpMenu = new JMenu("Help"));
        return menu;
    }

    /**
     * Alows to start
     * @param pathname a pathname
     */
    public void start(String pathname) {
        this.check.start(pathname);
        this.window.setVisible(true);
        while (true) {
        }
    }
    
    private class CloseWindowEvent extends WindowAdapter implements ActionListener {
        public void windowClosing(java.awt.event.WindowEvent e) {
            System.exit(0);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
	}
}