package mail.contents;


/**
 * The text content of a letter
 */
public class Text extends AbstractContent<String> {

	// BUILDERS

	public Text(String string) {
		super(string);
	}

	//METHODS

	/**
	 * Give the string of a content
	 * @return a string
	 */
	public String toString() {
		return "a text " + super.toString();
	}
	
}
