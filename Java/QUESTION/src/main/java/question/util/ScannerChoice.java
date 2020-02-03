package question.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

import question.answers.*;

/**
 * The class for the scanner to input.
 */
public class ScannerChoice {

	////////////////
	// ATTRIBUTES //

	private Scanner scanner;
	public static final ScannerChoice SCANNER = new ScannerChoice();

	//////////////
	// BUILDERS //

	private ScannerChoice(){
		this.scanner = new Scanner(System.in);
	}

	/////////////
	// METHODS //

	/** 
	 * change the used input stream  
     * @param in the new used InputStream
     */
    public void setIn(BufferedReader in){
		this.scanner = new Scanner(in);
	}

	/** 
	 * set the used InputStream to System.in
	 */
    public void setInToSystemIn() {
		this.setIn(new BufferedReader(new InputStreamReader(System.in)));
	}

	/**
	 * read an choice in input from the scanner
	 * input is repeated as long as it is not correct
	 * @param ans the answer
	 * @param out the output
	 * @return the valid read input 
	 */
	public String readChoice(AbstractAnswer<?> ans, PrintStream out) {
		String input = null;
		
		while (! ans.hasGoodType(input)) {
			try {
				out.print("(" + ans.getType() + ")");
				input = this.scanner.nextLine().toLowerCase();
			} catch (InputMismatchException	 e){
				// consume the input
				this.scanner.skip(".*");
			}
		} 

		return input;
	}	
}
