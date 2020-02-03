package plugins;

import plugin.Plugin;

/**
 * Allows to create a AllPoints
 */
public class AllPoints implements Plugin {

    /**
     * Constructor of the class
     */
    public AllPoints() {
    }

    @Override
    /**
     * Allows to transform all characters to points
     * @param s String to transform
     * @return string transformed
     */
    public String transform(String s) {
        String res = "";

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c != '\n' && c != '\t' && c != ' ')
                c = '.';
            res += c;
        }

        return res;
    }

    @Override
    /**
     * Give the label
     * @return label
     */
    public String getLabel() {
        return "All Points";
    }

    @Override
    /**
     * Give the message
     * @return message
     */
    public String getMessage() {
        return "Transform all Text to only points.";
    }
    
}