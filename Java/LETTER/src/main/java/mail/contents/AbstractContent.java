package mail.contents;

/**
 *  The content of a letter
 */
public abstract class AbstractContent<T> implements Content {

	//ATRIBUTES

	protected final T value ;

	//BUILDERS
	
	public AbstractContent(T value) {
		this.value = value;
	}	

	//METHODS

	/**
	 * Give the value of the value
	 * @return the value
	 */
	public T getValue() {
		return this.value;
	}
	
	/**
	 * Give the string of content
	 * @return the String
	 */
	public String toString() {
		return ""+this.getValue();
	}

}
