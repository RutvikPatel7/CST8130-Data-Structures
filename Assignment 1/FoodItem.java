import java.util.Scanner;

public class FoodItem {
	/**
	 * 
	 */
	private int itemCode;

	/**
	 * 
	 */
	private String itemName;

	/**
	 * 
	 */
	private float itemPrice;

	/**
	 * 
	 */
	private float itemCost;

	/**
	 * 
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
	 * Reads from the Scanner object passed in and fills the data member fields of the class with valid data.
	 * @param scanner - Scanner to use for input
	 * @return <code>true</code> if all data members were successfully populated, <code>false</code> otherwise
	 */
	public boolean addItem(Scanner scanner) {
		boolean valid = false;

		System.out.print("Enter the name for the item: ");
		itemName = scanner.next();
		// Input quantity
		while(!valid)
		{
			System.out.print("Enter the quantity for the item: ");
			if(scanner.hasNextInt())
			{
				itemQuantityInStock = scanner.nextInt();
				if(itemQuantityInStock < 0)
				{
					valid = false;
					System.out.println("Invalid input");
					itemQuantityInStock = 0;
				}
				else
					valid = true;				
			}
			else
			{
				System.out.println("Invalid input");
				scanner.next();
				valid = false;
			}
		}

		// Input the cost
		valid = false;
		while(!valid)
		{
			System.out.print("Enter the cost of the item: ");
			if(scanner.hasNextFloat())
			{
				itemCost = scanner.nextFloat();
				if(itemCost < 0)
				{
					valid = false;
					System.out.println("Invalid input");
					itemCost = 0;
				}
				else
					valid = true;
			}
			else
			{
				System.out.println("Invalid input");
				scanner.next();
				valid = false;
			}
		}

		// Input the price
		valid = false;
		while(!valid)
		{
			System.out.print("Enter the sales price of the item: ");
			if(scanner.hasNextFloat())
			{
				itemPrice = scanner.nextFloat();
				if(itemPrice < 0)
				{
					valid = false;
					System.out.println("Invalid input");
					itemPrice = 0;
				}
				else
					valid = true;
			}
			else
			{
				System.out.println("Invalid input");
				scanner.next();
				valid = false;
			}
		}
		return true;
	}

	/**
	 * Reads a valid itemCode from the Scanner object and returns true/false if successful
	 * @param scanner - Scanner to use for input
	 * @return <code>true</code> if code was populated, <code>false</code> otherwise
	 */
	public boolean inputCode(Scanner scanner) {
		boolean validInput = false;
		while(!validInput)
		{
			//System.out.print("Enter valid item code: ");
			System.out.print("Enter the code for the item: ");
			if(scanner.hasNextInt())
			{
				itemCode = scanner.nextInt();
				validInput = true;
			}
			else
			{
				System.out.println("Invalid code");
				// Clear the invalid input
				scanner.next();
			}
		}
		return validInput;
	}

	/**
	 * Compares this object's item code with the one passed in
	 * @param item - object to compare with
	 * @return <code>true</code> if the itemCode of the object being acted on and the item object parameter are the same value, <code>false</code> otherwise
	 */
	public boolean isEqual(FoodItem item) {
		return itemCode == item.itemCode;
	}

	/**
	 * Updates the quantity field by amount (note amount could be positive or negative)
	 * @param amount - what to update by, either can be positive or negative
	 * @return Method returns <code>true</code> if successful, otherwise returns <code>false</code>
	 */
	public boolean updateItem(int amount) {
		// If you are removing stock, then check that we have enough stock
		if(amount< 0 && itemQuantityInStock > amount)
		{
			return false;
		}
		itemQuantityInStock += amount;
		return true;
	}

	@Override
	public String toString() {
		return "Item: "+itemCode+" "+itemName+" "+itemQuantityInStock+" price: $"+String.format("%.2f", itemPrice)+" cost: $"+String.format("%.2f", itemCost);
	}
}
