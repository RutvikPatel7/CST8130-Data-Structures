import java.util.Formatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a contact in the phone call tree.
 * 
 * @author Rutvik Patel
 *
 */
public class Contact implements MyInterface<Contact> {
	/**
	 * Stores the full name of the contact
	 */
	private String name;
	/**
	 * Stores the phone number of the contact
	 */
	private String phoneNumber;

	/**
	 * Default Constructor
	 */
	public Contact() {
		name = "";
		phoneNumber = "";
	}

	/**
	 * Initial Constructor specifying all data
	 * @param name - Name of contact
	 * @param phoneNumber - Phone number of contact
	 */
	public Contact(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Copy Constructor
	 * @param toCopy - Contact to take values from
	 */
	public Contact(Contact toCopy)
	{
		this.name = toCopy.name;
		this.phoneNumber = toCopy.phoneNumber;
	}
	
	@Override
	public boolean equals(Object obj) {
		Contact other = (Contact) obj;
		// The contacts are the same if the contact name and number are the same.
		return (name == other.name) && (phoneNumber == other.phoneNumber);
	}

	@Override
	public String toString() {
		return name + ": " + phoneNumber;
	}

	@Override
	public int compareTo(Contact o) {
		// Compare the contact name and then phoneNumber
		int retVal = name.compareToIgnoreCase(o.name);
		// if the names are equal, compare the phone numbers
		if(retVal == 0)
			return phoneNumber.compareTo(o.phoneNumber);
		return retVal;
	}

	@Override
	public boolean create(Scanner scanner, boolean readFromFile) {
		if (!readFromFile)
			System.out.print("Enter name of contact: ");
		if (scanner.hasNext())
			name = scanner.next();

		if (!readFromFile)
			System.out.print("Enter phone number for contact: ");
		String pattern = "1?\\s*-?\\(?(\\d{3})\\)?-?\\s*(\\d{3})-?\\s*(\\d{4})";
		Pattern pat = Pattern.compile(pattern);
		if (scanner.hasNext(pat))
		{
			String temp = scanner.next();
			Matcher match = pat.matcher(temp);
			if(match.find())
				phoneNumber = "1-"+match.group(1)+"-"+match.group(2)+"-"+match.group(3);
			else
				phoneNumber = temp;
		}
		else {
			System.out.println("Malformed phone number");
			scanner.next();
			return false;
		}

		if (name.trim().equals("") || phoneNumber.trim().equals("")) {
			System.out.println("You must specify a name and phone number to create a contact");
			return false;
		}
		return true;

	}	

	@Override
	public boolean output(Formatter writer)
	{
		try
		{
			if(writer != null)
			{
				writer.format("%s\n", name);
				writer.format("%s\n", phoneNumber);
			}
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
			return false;
		}
		return true;
	}
	
	@Override
	public String getIdentifier() {
		return name;
	}
}
