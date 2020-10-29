import java.util.Formatter;
import java.util.Scanner;

/**
 * This class encapsulates the behaviour of an item of food to be used with Inventory
 * 
 * @author Rutvik Patel
 *
 */
public class FoodItem {
	/**
	 * Code for food item
	 */
	private int itemCode;

	/**
	 * Cost of the food item
	 */
	private float itemCost;

	/**
	 * Name of the food item
	 */
	private String itemName;

	/**
	 * Price of the food item
	 */
	private float itemPrice;

	/**
	 * Quantity of the food item
	 */
	private int itemQuantityInStock;

	/**
	 * Default Constructor
	 */
	public FoodItem() {
		itemCode = 0;
		itemName = "";
		itemPrice = 0.0f;
		itemCost = 0.0f;
		itemQuantityInStock = 0;
	}

	/**
	 * Reads from the Scanner object passed in and fills the data member fields of
	 * the class with valid data.
	 * 
	 * @param scanner  - Scanner to use for input
	 * @param fromFile - When <code>true</code>, the console output will not be displayed
	 * @return <code>true</code> if all data members were successfully populated,
	 *         <code>false</code> otherwise
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		boolean valid = false;

		if (!fromFile)
			System.out.print("Enter the name for the item: ");
		itemName = scanner.next();
		// Input quantity
		while (!valid) {
			if (!fromFile)
				System.out.print("Enter the quantity for the item: ");
			if (scanner.hasNextInt()) {
				itemQuantityInStock = scanner.nextInt();
				if (itemQuantityInStock < 0) {
					valid = false;
					System.out.println("Invalid input");
					itemQuantityInStock = 0;
				} else
					valid = true;
			} else {
				System.out.println("Invalid input");
				scanner.next();
				valid = false;
			}
		}

		// Input the cost
		valid = false;
		while (!valid) {
			if (!fromFile)
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
			if (!fromFile)
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
		return true;
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
	 * @param scanner  - Scanner to use for input
	 * @param fromFile - When true, we will not display the questions to the console
	 * @return <code>true</code> if code was populated, <code>false</code> otherwise
	 */
	public boolean inputCode(Scanner scanner, boolean fromFile) {
		boolean validInput = false;
		while (!validInput) {
			if (!fromFile)
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

	/**
	 * Outputs this object to the formatter specified
	 *
	 * @param writer - Formatter to output to
	 */
	public void outputItem(Formatter writer) {
		writer.format("%d\n", itemCode);
		writer.format("%s\n", itemName);
		writer.format("%d\n", itemQuantityInStock);
		writer.format("%.2f\n", itemCost);
		writer.format("%.2f\n", itemPrice);
	}

	@Override
	public String toString() {
		return "Item: " + itemCode + " " + itemName + " " + itemQuantityInStock + " price: $"
				+ String.format("%.2f", itemPrice) + " cost: $" + String.format("%.2f", itemCost);
	}

	/**
	 * Updates the quantity field by amount (note amount could be positive or
	 * negative)
	 *
	 * @param amount - what to update by, either can be positive or negative
	 * @return Method returns <code>true</code> if successful, otherwise returns
	 *         <code>false</code>
	 */
	public boolean updateItem(int amount) {
		// If you are removing stock, then check that we have enough stock
		if(amount < 0 && itemQuantityInStock < (amount*-1)) {
			return false;
		}
		itemQuantityInStock += amount;
		return true;
	}

}
