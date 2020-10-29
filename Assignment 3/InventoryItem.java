import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author Rutvik Patel
 *
 */
public class InventoryItem {
	/**
	 * Stores a date for each item in the inventory item
	 */
	private Queue<LocalDate> expiries;

	/**
	 * Food Item that is stored in the inventory
	 */
	private FoodItem item;

	/**
	 * Maintain count of the amount stored in the LinkedList
	 */
	private int itemQuantityInStock;

	/**
	 * Initialization Constructor
	 * 
	 * @param item2 - FoodItem to initialize with
	 */
	public InventoryItem(FoodItem item2) {
		itemQuantityInStock = 0;
		this.item = item2;
		expiries = new LinkedList<LocalDate>();
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
		if (item == null)
			return false;
		if (item.addItem(scanner)) {
			// Input quantity
			while (!valid) {
				System.out.print("Enter the quantity for the item: ");
				if (scanner.hasNextInt()) {
					int temp = scanner.nextInt();
					if (temp < 0) {
						valid = false;
						System.out.println("Invalid input");
					} else {
						valid = true;
						return updateQuantity(scanner, temp);
					}
				} else {
					System.out.println("Invalid input");
					scanner.next();
					valid = false;
				}
			}
		}
		return valid;
	}

	/**
	 * Returns the item code so it may be used to compare objects
	 *
	 * @return Value of itemCode of the item data member
	 */
	public int getItemCode() {
		if (item != null)
			return item.getItemCode();
		return -1;
	}

	/**
	 * Reads a valid itemCode from the Scanner object and returns true/false if
	 * successful
	 *
	 * @param scanner - Scanner to use for input
	 * @return <code>true</code> if code was populated, <code>false</code> otherwise
	 */
	public boolean inputCode(Scanner scanner) {
		if (item == null)
			item = new FoodItem();
		return item.inputCode(scanner);
	}

	/**
	 * Prints the summary of the expiry dates for this item
	 */
	public void printExpirySummary() {
		System.out.println(toString());
		System.out.println("Expiry Details:");
		if (expiries.isEmpty()) {
			System.out.println("None");
			return;
		}
		Iterator<LocalDate> list = expiries.iterator();
		int count = 0;
		LocalDate compareTo = expiries.peek();
		if (compareTo != null && compareTo.equals(LocalDate.MAX))
			System.out.print("No Expiry: ");
		else
			System.out.print(compareTo + ": ");
		while (list.hasNext()) {
		LocalDate current = list.next();
			
			if (current.equals(compareTo))
				count++;
			else {
				System.out.println(count);
				compareTo = current;
				count = 1;
				if (compareTo.equals(LocalDate.MAX))
					System.out.print("No Expiry: ");
				else
					System.out.print(compareTo + ": ");

			}
		}
		System.out.println(count);
		System.out.println();
	}

	/**
	 * Go through the linked list and remove all the items that are expired.
	 * 
	 * @param todaysDate - Date passed in so that we can simulate scenarios
	 */
	public void removeExpiredItems(LocalDate todaysDate) {
		while (!expiries.isEmpty() && expiries.peek().compareTo(LocalDate.MAX) != 0
				&& expiries.peek().isBefore(todaysDate)) {
			expiries.remove();
			itemQuantityInStock--;
		}
	}

	@Override
	public String toString() {
		return item.toString() + " qty: " + itemQuantityInStock;
	}

	/**
	 * Updates the quantity field by amount (note amount could be positive or
	 * negative)
	 * 
	 * @param scanner - Scanner to use for input
	 * @param amount  - what to update by, either can be positive or negative
	 * @return Method returns <code>true</code> if successful, otherwise returns
	 *         <code>false</code>
	 */
	public boolean updateQuantity(Scanner scanner, int amount) {
		// If you are removing stock, then check that we have enough stock
		if (amount < 0 && itemQuantityInStock < (amount * -1)) {
			return false;
		}
		// Add items
		if (amount > 0) {
			// Input the expiry date
			boolean valid = false;
			LocalDate expiry = null;
			while (!valid) {
				System.out.print("Enter the expiry date of the item (yyyy-mm-dd or none): ");
				if (scanner.hasNext()) {
					String tempDate = scanner.next();
					try {
						if (tempDate.equalsIgnoreCase("none"))
							expiry = LocalDate.MAX;
						else
							expiry = LocalDate.parse(tempDate);
						valid = true;
					} catch (DateTimeParseException e) {
						System.out.println("Could not create date from input, please use format yyyy-mm-dd");
						System.out.println(e.getLocalizedMessage());
						valid = false;
					}
				} else {
					System.out.println("Invalid input");
					scanner.next();
					valid = false;
				}
			}

			for (int i = 0; i < amount; i++)
				expiries.add(expiry);
		} else {
			for (int i = 0; i < (amount * -1); i++)
				expiries.remove();
		}
		itemQuantityInStock += amount;
		return true;
	}
}
