import java.util.Comparator;

/**
 * Used to compare objects of type FoodItem
 * 
 * @author Rutvik Patel
 *
 */
public class FoodItemComparator implements Comparator<FoodItem> {
	// Compares its two arguments for order. Returns a negative integer, zero, or a
	// positive integer as the first argument is less than, equal to, or greater
	// than the
	// second.
	@Override
	public int compare(FoodItem o1, FoodItem o2) {
		// Compare by item code.
		return o1.getItemCode() - o2.getItemCode();
	}

}
