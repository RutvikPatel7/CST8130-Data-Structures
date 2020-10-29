import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class encapsulates the behaviour of an inventory
 * @author Rutvik Patel
 *
 */
public class Inventory {
	/**
	 * List of FoodItems that represents our inventory
	 */
	private ArrayList<FoodItem> inventory;

	/**
	 * Default Constructor
	 */
	public Inventory() {
		inventory = new ArrayList<FoodItem>(20);
	}

	/**
	 * Reads from the Scanner object passed in and fills the data member fields of
	 * the class with valid data.
	 * 
	 * @param scanner  - Scanner to use for input
	 * @param fromFile - When true, we will not display the questions to the console
	 * @return <code>true</code> if all data members were successfully populated,
	 *         <code>false</code> otherwise
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		boolean valid = false;
		FoodItem item = null;
		while (!valid) {
			if (!fromFile)
				System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)? ");
			if (scanner.hasNext(Pattern.compile("[fFvVpP]"))) {
				String choice = scanner.next();
				switch (choice.toLowerCase()) {
				case "f":
					item = new Fruit();
					break;
				case "v":
					item = new Vegetable();
					break;
				case "p":
					item = new Preserve();
					break;
				default: // Should not get here.
					item = new FoodItem();
					break;
				}
				valid = true;
			} else {
				System.out.println("Invalid entry");
				scanner.next();
				valid = false;
			}
		}
		if (item.inputCode(scanner, fromFile)) {
			if (alreadyExists(item) < 0) {
				if (item.addItem(scanner, fromFile)) {
					insertItem(item);
					return true;
				}
				return false;
			} else {
				System.out.println("Item code already exists");
				return false;
			}
		}
		return true;
	}

	/**
	 * Search for a food item and see if it is already stored in the inventory
	 * 
	 * @param item - FoodItem to look for
	 * @return - The index of item if it is found, -1 otherwise
	 */
	public int alreadyExists(FoodItem item) {
		return binarySearch(item.getItemCode(), 0, inventory.size()-1);
	}

	/**
	 * Search for the code using a binary search algorithm
	 * @param itemCode - Code to search for
	 * @param start - Index to start at
	 * @param end - Index to end at
	 * @return - Index of the found item or -1 if it is not found
	 */
	private int binarySearch(int itemCode, int start, int end) {
		int middle = (start + end) / 2;
		if (start > end)
			return -1;
		if (inventory.isEmpty())
			return -1;
		if (inventory.get(middle).getItemCode() == itemCode)
			return middle;
		if (inventory.get(middle).getItemCode() > itemCode)
			return binarySearch(itemCode, start, middle - 1);
		if (inventory.get(middle).getItemCode() < itemCode)
			return binarySearch(itemCode, middle + 1, end);
		return -1;
	}

	/**
	 * Inserts an item into the inventory and maintains sort order
	 * 
	 * @param item - Item to add to the inventory
	 */
	private void insertItem(FoodItem item) {
		// Used to compare FoodItems
		FoodItemComparator comp = new FoodItemComparator();
		for (int i = 0; i < inventory.size(); i++) {
			// If the item is greater than the one in inventory, insert, insert here and
			// push everything else out
			if (comp.compare(inventory.get(i), item) >= 0) {
				inventory.add(i, item);
				return;
			}
		}
		inventory.add(item);
	}

	/**
	 * Add to inventory from a file
	 * 
	 * @param scanner - Scanner to use for input
	 */
	public void readFromFile(Scanner scanner) {
		try {
			System.out.print("Enter the filename to read from: ");
			String filename = scanner.next();

			File file = new File(filename);
			if (file.exists()) {
				Scanner fileReader = new Scanner(file);
				fileReader.useDelimiter("[\\r\\n]+");
				while (fileReader.hasNext()) {
					if (!addItem(fileReader, true)) {
						System.out.println("Error Encountered while reading the file, aborting...");
						break;
					}
				}
			}
			else
			{
				System.out.println("File Not Found, ignoring...");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	/**
	 * Saves the content of inventory to a file.
	 * 
	 * @param scanner - Scanner to use for input
	 */
	public void saveToFile(Scanner scanner) {
		try {
			System.out.print("Enter the filename to save to: ");
			String filename = scanner.next();

			File file = new File(filename);
			file.createNewFile();
			file.setWritable(true);
			Formatter writer = new Formatter(file);
			ListIterator<FoodItem> iter = inventory.listIterator();
			while (iter.hasNext()) {
				iter.next().outputItem(writer);
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.out.println("Could not create file, " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	/**
	 * Asks a user for an item code to search for and then displays that item if found
	 * 
	 * @param scanner - Scanner to use for input
	 */
	public void searchForItem(Scanner scanner) {
		FoodItem itemToSearchFor = new FoodItem();
		itemToSearchFor.inputCode(scanner, false);
		int index = binarySearch(itemToSearchFor.getItemCode(), 0, inventory.size()-1);
		if (index == -1)
			System.out.println("Code not found in inventory...");
		else
			System.out.println(inventory.get(index).toString());
	}

	@Override
	public String toString() {
		String returnString = "Inventory:\n";
		ListIterator<FoodItem> items = inventory.listIterator();
		while (items.hasNext())
			returnString += items.next().toString() + "\n";
		return returnString;
	}

	/**
	 * Update the quantity stored in the food item
	 * 
	 * @param scanner   - Input device to use
	 * @param buyOrSell - If we are to add to quantity (<code>true</code>) or remove
	 *                  (<code>false</code>)
	 * @return <code>true</code> if update was successfully, <code>false</code> otherwise
	 */
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
		// If there are no items then we can't update, return
		if (inventory.isEmpty())
			return false;

		FoodItem temp = new FoodItem();
		temp.inputCode(scanner, false);
		int index = alreadyExists(temp);
		if (index != -1) {
			String buySell = buyOrSell ? "buy" : "sell";
			System.out.print("Enter valid quantity to " + buySell + ": ");
			if (scanner.hasNextInt()) {
				int amount = scanner.nextInt();
				if (amount > 0) {
					return inventory.get(index).updateItem(buyOrSell ? amount : amount * -1);
				} else {
					System.out.println("Invalid quantity...");
				}
			} else {
				System.out.println("Invalid quantity...");
			}
		}
		return false;
	}
}
