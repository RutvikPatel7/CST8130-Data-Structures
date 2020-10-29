import java.util.Scanner;

public class Fruit extends FoodItem {
	/**
	 * 
	 */
	private String orchardName;

	/**
	 * Default Constructor
	 */
	public Fruit() {
		super();
		orchardName = "";
	}

	@Override
	public boolean addItem(Scanner scanner) {
		if(super.addItem(scanner))
		{
			System.out.print("Enter the name of the orchard supplier: ");
			orchardName = scanner.next();				
		}
		return true;
	}

	@Override
	public String toString() {
		return super.toString()+" orchard supplier: "+orchardName;
	}

}
