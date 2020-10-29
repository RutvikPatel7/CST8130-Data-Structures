import java.util.Formatter;
import java.util.Scanner;

/**
 * Used as a contract so that our binary tree can store a generic type.
 * @author Rutvik Patel
 *
 * @param <T> Type to pass to Comparable
 */
public interface MyInterface<T> extends Comparable<T> {
	/**
	 * Create an object from user input
	 * @param scanner - Scanner to use for input, either a file or the keyboard
	 * @param readFromFile - If from file, don't display the prompts
	 * @return <code>True</code> if contact was successfully created, <code>false</code> if something went wrong
	 */
	public boolean create(Scanner scanner, boolean readFromFile);
	/**
	 * Writes the object to the location provided
	 * @param writer Mechanism to output to
	 * @return <code>true</code> if the method succeeds, <code>false</code> otherwise
	 */
	public boolean output(Formatter writer);
	
	/**
	 * Returns the major component of the object for display
	 * @return String representing the identifier of the object
	 */
	public String getIdentifier();
}
