package mail.contents;


/**
 * The number content for a letter
 */
public class Number extends AbstractContent<Integer> {

	//BUILDERS

	public Number(Integer value) {
		super(value) ;
	}

	//METHODS

	/**
	 * Give the string of content
	 * @return a String
	 */
	public String toString() {
		return super.toString();
	}
}
