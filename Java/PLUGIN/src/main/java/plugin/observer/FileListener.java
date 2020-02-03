package plugin.observer;

public interface FileListener extends java.util.EventListener {

    /**
     * Allow to add file 
     * @param e file to add
     */
    public void fileAdded(FileEvent e);

    /**
     * Allows to remove file
     * @param e file to remove
     */
    public void fileRemoved(FileEvent e);

}