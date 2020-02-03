package plugins;

import plugin.Plugin;

/**
 * Allows to create a UpperCase
 */
public class UpperCase implements Plugin {

    /**
     * Constructor of the class
     */
    public UpperCase(){}

    @Override
    /**
     * Allows to up all case
     * @param s String to transform
     * @return string transformed
     */
    public String transform(String s) {
        return s.toUpperCase();
    }

    @Override
    /**
     * Give the label
     * @return label
     */
    public String getLabel() {
        return "Upper Case";
    }

    @Override
    /**
     * Give the message
     * @return message
     */
    public String getMessage() {
        return "Transform all Text to upper case.";
    }
    
}