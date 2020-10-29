import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Tests the BinaryTree and Node class.
 * 
 * @author Rutvik Patel
 *
 */
public class Assign4 {

	/**
	 * Main Method
	 * @param args Passed in parameters, none supported
	 */
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		keyboard.useDelimiter(Pattern.compile("[\\r\\n]+"));
		BinaryTree<Contact> tree = new BinaryTree<Contact>();
		
		int choice = 0;
		while (choice != 7)
		{
			displayMenu();
			if(keyboard.hasNextInt())
			{
				choice = keyboard.nextInt();
				switch(choice)
				{
				case 1:
					// Display the Phone Tree
					System.out.println("Phone List");
					tree.displayInOrder();
					break;
				case 2:
					// Add one Contact to the List
					Contact newContact = new Contact();
					if(newContact.create(keyboard, false))
						tree.insertInTree(newContact);
					else
						System.out.println("No Contact Added");
					break;
				case 3:
					// Add Contacts from a File
					try
					{
						System.out.print("Enter name of file to write to: ");
						String filename = keyboard.next();
						
						File file = new File(filename);
						if(file.exists())
						{
							Scanner fileReader = new Scanner(file);
							fileReader.useDelimiter("\n");
							while(fileReader.hasNext())
							{
								Contact addContact = new Contact();
								addContact.create(fileReader, true);
								tree.insertInTree(addContact);
							}
						}
					}
					catch(Exception e)
					{
						System.out.println("Error: "+e.getMessage());
					}
					break;
				case 4:
					// Save Contacts to a File
					System.out.print("Specify a filename to save: ");
					if(keyboard.hasNext())
					{
						String filename = keyboard.next();
						try {
							File newFile = new File(filename);
							newFile.createNewFile();
							newFile.setWritable(true);
							Formatter formatter = new Formatter(newFile);
							tree.outputTree(formatter);
							formatter.flush();
							formatter.close();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					break;
				case 5:
					// Determine if a Contact is in the List
					Contact searchFor = new Contact();
					if(searchFor.create(keyboard, false))
						if(tree.searchTree(searchFor))
							System.out.println("Contact found in List");
						else
							System.out.println("Contact not in List");
					else
						System.out.println("Cannot search for contact");
					break;
				case 6: 
					// List out who calls whom - This is a pre-order traversal of the tree
					System.out.println("Here are everyone's responsibilities:");
					tree.displayChildren(" calls ");
					break;
				case 7:
					System.out.println("Exiting...");
					break;
					default:
						System.out.println("Invalid menu option");
				}
			}
		}
		keyboard.close();

	}
	
	/**
	 * Helper function to display menu to the console
	 */
	private static void displayMenu() {
		System.out.println();
		System.out.println("Please select one of the following:");
		System.out.println("1: Display the Phone Tree");
		System.out.println("2: Add one Contact to the List");
		System.out.println("3: Add Contacts from a File");
		System.out.println("4: Save Contacts to a File");
		System.out.println("5: Determine if a Contact is in the List");
		System.out.println("6: List out who calls whom");
		System.out.println("7: To Exit");
	}

}
