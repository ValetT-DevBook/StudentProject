package plugin.observer;

/**
 * Allows to create a FileEvent
 */
public class FileEvent extends java.util.EventObject{

    private static final long serialVersionUID = 1L;
    private String name;

    /**
     * Constructor of the class
     * @param name name of the file
     */
    public FileEvent(String name) {
        super(name);
        this.name = name;
    }

    /**
     * Give the name
     * @return name
     */
    public String getFileString() {
        return this.name;
    }
}