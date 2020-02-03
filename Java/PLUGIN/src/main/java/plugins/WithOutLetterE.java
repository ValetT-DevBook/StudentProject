package plugins;

import plugin.Plugin;

/**
 * Allows to create a AllPoints
 */
public class WithOutLetterE implements Plugin {

    /**
     * Constructor of the class
     */
    public WithOutLetterE() {
    }

    @Override
    /**
     * Allows to remove all letter e
     * @param s String to transform
     * @return string transformed
     */
    public String transform(String s) {
        String res = "";

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c != '\n' && c != '\t' && c != ' '){
                if(c == 'e' || c == 'E'){
                    c = Character.MIN_VALUE;
                }
            }
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
        return "Without letter e";
    }

    @Override
    /**
     * Give the message
     * @return message
     */
    public String getMessage() {
        return "Remove all e in the text.";
    }
    
}