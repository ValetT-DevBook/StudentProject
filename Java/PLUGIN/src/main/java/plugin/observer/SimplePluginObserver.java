package plugin.observer;

public class SimplePluginObserver implements FileListener {

    @Override
    /**
     * Allow to add file 
     * @param e file to add
     */
    public void fileAdded(FileEvent e) {
        System.out.println("New " + e.getSource() + " detected.");
    }
    @Override
    /**
     * Allows to remove file
     * @param e file to remove
     */
    public void fileRemoved(FileEvent e) {
        System.out.println(e.getSource() + " deleted.");
    }
}