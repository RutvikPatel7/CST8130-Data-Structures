import java.util.Formatter;
import java.util.Scanner;

/**
 * @author Rutvik Patel
 *
 */
public class Vegetable extends FoodItem {
	/**
	 * Name of the farm the vegetable came from
	 */
	private String farmName;

	/**
	 * Default Constructor
	 */
	public Vegetable() {
		super();
		farmName = "";
	}

	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {
		if (super.addItem(scanner, fromFile)) {
			if (!fromFile)
				System.out.print("Enter the name of the farm supplier: ");
			farmName = scanner.next();
		}
		return true;
	}

	@Override
	public void outputItem(Formatter writer) {
		writer.format("v\n");
		super.outputItem(writer);
		writer.format("%s\n", farmName);
	}

	@Override
	public String toString() {
		return super.toString() + " farm supplier: " + farmName;
	}
}
