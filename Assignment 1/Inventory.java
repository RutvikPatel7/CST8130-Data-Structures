import java.util.Scanner;
import java.util.regex.Pattern;

public class Inventory {
	/**
	 * List of FoodItems that represents our inventory
	 */
	private FoodItem[] inventory;

	/**
	 * Number of items that a user has entered
	 */
	private int numItems;

	/**
	 * Default Constructor
	 */
	public Inventory() {
		inventory = new FoodItem[20];
	}

	/**
	 * Reads from the Scanner object passed in and fills the data member fields of the class with valid data.
	 * @param scanner - Scanner to use for input
	 * @return <code>true</code> if all data members were successfully populated, <code>false</code> otherwise
	 */
	public boolean addItem(Scanner scanner) {

		if(numItems == 20)
		{
			System.out.println("Inventory full");
			return false;
		}
		boolean valid = false;
		FoodItem item = null;
		while(!valid)
		{
			System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)? ");
			if(scanner.hasNext(Pattern.compile("[fFvVpP]")))
			{
				String choice = scanner.next();
				switch(choice.toLowerCase())
				{
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
			}
			else
			{
				System.out.println("Invalid entry");
				scanner.next();
				valid = false;
			}
		}
		if(item.inputCode(scanner))
		{
			if(alreadyExists(item)<0)
			{
				if(item.addItem(scanner))
				{
					inventory[numItems] = item;
					numItems++;
					return true;
				}
				return false;
			}
			else
			{
				System.out.println("Item code already exists");
				return false;
			}
		}
		return true;
	}

	/**
	 * Search for a food item and see if it is already stored in the inventory
	 * @param item - FoodItem to look for
	 * @return - The index of item if it is found, -1 otherwise
	 */
	public int alreadyExists(FoodItem item) {
		for(int i=0;i<numItems;i++)
		{
			if(inventory[i].isEqual(item))
				return i;
		}
		return -1;
	}

	/**
	 * Update the quanity stored in the food item
	 * @param scanner - Input device to use 
	 * @param buyOrSell - If we are to add to quantity (<code>true</code>) or remove (<code>false</code>)
	 * @return
	 */
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
		// If there are no items then we can't update, return
		if(numItems == 0)
			return false;

		FoodItem temp = new FoodItem();
		temp.inputCode(scanner);
		int index = alreadyExists(temp);
		if(index != -1)
		{
			String buySell = buyOrSell?"buy":"sell";
			System.out.print("Enter valid quantity to "+buySell+": ");
			if(scanner.hasNextInt())
			{
				int amount = scanner.nextInt();
				if(amount > 0)
				{
					return inventory[index].updateItem(buyOrSell? amount: amount*-1);
				}
				else
				{
					System.out.println("Invalid quantity...");
				}
			}
			else
			{
				System.out.println("Invalid quantity...");
			}
		}
		return false;
	}

	@Override
	public String toString() {
		String returnString = "Inventory:\n";
		for(int i=0;i<numItems;i++)
			returnString += inventory[i].toString() + "\n";
		return returnString;
	}
}
