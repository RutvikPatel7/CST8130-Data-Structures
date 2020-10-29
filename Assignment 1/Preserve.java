import java.util.Scanner;

public class Preserve extends FoodItem {
	/**
	 * 
	 */
	private int jarSize;

	/**
	 * Default Constructor
	 */
	public Preserve() {
		super();
		jarSize = 0;
	}

	@Override
	public boolean addItem(Scanner scanner) {
		boolean valid = false;
		if(super.addItem(scanner))
		{
			// Input quantity
			while(!valid)
			{
				System.out.print("Enter the size of the jar in millilitres: ");
				if(scanner.hasNextInt())
				{
					jarSize = scanner.nextInt();
					if(jarSize < 0)
					{
						valid = false;
						System.out.println("Invalid input");
						jarSize = 0;
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
		}
		return true;
	}

	@Override
	public String toString() {
		return super.toString()+" size: "+jarSize+"mL";
	}

}
