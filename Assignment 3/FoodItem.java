import java.util.Scanner;

/**
 * This class encapsulates the behaviour of an item of food to be used with
 * Inventory
 *
 * @author Rutvik Patel
 *
 */
public class FoodItem {
	/**
	 * Code of the food item
	 */
	private int itemCode;

	/**
	 * Cost of the item
	 */
	private float itemCost;

	/**
	 * Name of the item
	 */
	private String itemName;

	/**
	 * Price of the item
	 */
	private float itemPrice;

	/**
	 * Default Constructor
	 */
	public FoodItem() {
		itemCode = 0;
		itemName = "";
		itemPrice = 0.0f;
		itemCost = 0.0f;
	}

	/**
	 * Reads from the Scanner object passed in and fills the data member fields of
	 * the class with valid data.
	 *
	 * @param scanner - Scanner to use for input
	 * @return <code>true</code> if all data members were successfully populated,
	 *         <code>false</code> otherwise
	 */
	public boolean addItem(Scanner scanner) {
		boolean valid = false;

		System.out.print("Enter the name for the item: ");
		itemName = scanner.next();

		// Input the cost
		valid = false;
		while (!valid) {
			System.out.print("Enter the cost of the item: ");
			if (scanner.hasNextFloat()) {
				itemCost = scanner.nextFloat();
				if (itemCost < 0) {
					valid = false;
					System.out.println("Invalid input");
					itemCost = 0;
				} else
					valid = true;
			} else {
				System.out.println("Invalid input");
				scanner.next();
				valid = false;
			}
		}

		// Input the price
		valid = false;
		while (!valid) {
			System.out.print("Enter the sales price of the item: ");
			if (scanner.hasNextFloat()) {
				itemPrice = scanner.nextFloat();
				if (itemPrice < 0) {
					valid = false;
					System.out.println("Invalid input");
					itemPrice = 0;
				} else
					valid = true;
			} else {
				System.out.println("Invalid input");
				scanner.next();
				valid = false;
			}
		}
		return valid;
	}

	/**
	 * Returns the item code so it may be used to compare objects
	 *
	 * @return Value of itemCode data member
	 */
	public int getItemCode() {
		return itemCode;
	}

	/**
	 * Reads a valid itemCode from the Scanner object and returns true/false if
	 * successful
	 *
	 * @param scanner - Scanner to use for input
	 * @return <code>true</code> if code was populated, <code>false</code> otherwise
	 */
	public boolean inputCode(Scanner scanner) {
		boolean validInput = false;
		while (!validInput) {
			System.out.print("Enter the code for the item: ");
			if (scanner.hasNextInt()) {
				itemCode = scanner.nextInt();
				validInput = true;
			} else {
				System.out.println("Invalid code");
				// Clear the invalid input
				scanner.next();
			}
		}
		return validInput;
	}

	/**
	 * Compares this object's item code with the one passed in
	 *
	 * @param item - object to compare with
	 * @return <code>true</code> if the itemCode of the object being acted on and
	 *         the item object parameter are the same value, <code>false</code>
	 *         otherwise
	 */
	public boolean isEqual(FoodItem item) {
		return itemCode == item.itemCode;
	}

	@Override
	public String toString() {
		return "Item: " + itemCode + " " + itemName + " price: $" + String.format("%.2f", itemPrice) + " cost: $"
				+ String.format("%.2f", itemCost);
	}

}
