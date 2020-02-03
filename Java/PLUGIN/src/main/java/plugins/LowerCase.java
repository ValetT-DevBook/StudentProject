package plugins;

import plugin.Plugin;

/**
 * Allows to create a LowerCase
 */
public class LowerCase implements Plugin {

    /**
     * Constructor of the class
     */
    public LowerCase() {
    }

    @Override
    /**
     * Allows to down all case
     * @param s String to transform
     * @return string transformed
     */
    public String transform(String s) {
        return s.toLowerCase();
    }

    @Override
    /**
     * Give the label
     * @return label
     */
    public String getLabel() {
        return "Lower Case";
    }

    @Override
    /**
     * Give the message
     * @return message
     */
    public String getMessage() {
        return "Transform all Text to lower case.";
    }
    
}