package plugin;

public interface Plugin {
	
	public static final String PLUGIN_NAME = "plugins";
	
	/**
	 * Allows to transform a string
	 * @param s string to transform
	 * @return string transformed
	 */
	public String transform(String s);
	
	/**
	 * Give the label
	 * @return label
	 */
	public String getLabel();
	
	/**
	 * Give the message
	 * @return message
	 */
	public String getMessage();

}
