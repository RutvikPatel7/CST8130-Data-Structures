/**
 * Represents one node in a Binary Tree
 * @author Rutvik Patel
 *
 * @param <T> Type of Element to store in the node
 *
 */
public class BinaryTreeNode< T extends MyInterface<T>> {

	/**
	 * Stores the integer of this node
	 */
	private T data;
	/**
	 * Reference to the left BinaryTree
	 */
	private BinaryTreeNode<T> left;
	/**
	 * Reference to the right BinaryTree
	 */
	private BinaryTreeNode<T> right;

	/**
	 * Default Constructor
	 */
	public BinaryTreeNode() {
		left = null;   
		right = null; 
		data = null;
	}
	
	/**
	 * Constructor which specifies the data to be stored
	 * @param data - Data to store in the node to begin with
	 */
	public BinaryTreeNode(T data) {
		left = null;   
		right = null; 
		this.data = data;
	}
	
	/**
	 * Returns the data stored in this node
	 * @return Data member variable
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Returns the left Binary Tree
	 * @return Left binary tree
	 */
	public BinaryTreeNode<T> getLeft() {
		return left;
	}

	/**
	 * Returns the right Binary Tree
	 * @return Right binary tree
	 */
	public BinaryTreeNode<T> getRight() {
		return right;
	}
	
	/**
	 * Inserts a node into either the left or right tree where appropriate
	 * @param newData Reference to the new data
	 */
	public void insert (T newData) {
		if (newData.compareTo(data) < 0) {
			if (left == null)
				left = new BinaryTreeNode<T>(newData);
			else 
				left.insert(newData);
		} 
		else if (newData.compareTo(data) > 0) {
			if (right == null)
				right = new BinaryTreeNode<T>(newData);
			else 
				right.insert(newData);
		} 
		else 
			System.out.println("Duplicate - not adding " + newData);
	}
	
	/**
	 * Search method to find what is passed in
	 * @param searchFor - Element to search for
	 * @return <code>true</code> if the element is found, <code>false</code> otherwise
	 */
	public boolean search (T searchFor)
	{
		if(searchFor.compareTo(data) == 0)
			return true;
		else if (searchFor.compareTo(data) < 0) { 
			if (left == null)
				return false;
			else
				return left.search(searchFor);
		} 
		else if (searchFor.compareTo(data) > 0) {
			if (right == null)
				return false;
			else
				return right.search(searchFor);
		}
		return false;
	}
}
