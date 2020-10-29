import java.util.Formatter;

/**
 * Represents the Binary Tree data structure
 * @author Rutvik Patel
 * @param <T> Type of Element to store in the tree
 *
 */
public class BinaryTree< T extends MyInterface<T> > {

	/**
	 * Reference to the root Node of the tree
	 */
	BinaryTreeNode<T> root = null;

	/**
	 * Insert the data into the tree
	 * @param newData New data to store in the tree
	 */
	public void insertInTree (T newData) {
		if (root == null) 
			root = new BinaryTreeNode<T>(newData);
		else 
			root.insert(newData);
	}
	
	/**
	 * Search the tree for a value.  
	 * @param searchFor - What to look for
	 * @return - True if the item is found, false otherwise.
	 */
	public boolean searchTree(T searchFor)
	{
		return searchTree(root, searchFor);
	}
	
	/**
	 * Helper function for searchFor to find a value starting at the root
	 * @param subRoot - Root to start the search in
	 * @param searchFor - What to look for
	 * @return - True if the item is found, false otherwise.
	 */
	private boolean searchTree(BinaryTreeNode<T> subRoot, T searchFor)
	{
		if (subRoot == null)   
			return false;
		else
			return root.search(searchFor);
	}

	/**
	 * Method to display the contents of the tree
	 */
	public void displayInOrder () {
		if(root == null)
			System.out.println("List is Empty");
		displayInOrder (root);
	}
	
	/**
	 * Traverse the tree using InOrder traversal and display the content to the console
	 * @param subRoot The node to start with
	 */
	private void displayInOrder (BinaryTreeNode<T> subRoot){
		if (subRoot == null)   
			return;
		displayInOrder (subRoot.getLeft());
		System.out.println(subRoot.getData());
		displayInOrder (subRoot.getRight());
	}
	
	/**
	 * Traverse the tree and write it to the Formatter
	 * @param writer Mechanism to output to
	 * @return <code>true</code> if the method succeeds, <code>false</code> otherwise
	 */
	public boolean outputTree(Formatter writer)
	{
		return outputPostOrder(root, writer);
	}
	
	/**
	 * Helper function to be called recursively to traverse the tree in post order.
	 * @param subRoot The node to start with
	 * @param writer Mechanism to output to
	 * @return <code>true</code> if the method succeeds, <code>false</code> otherwise
	 */
	private boolean outputPostOrder(BinaryTreeNode<T> subRoot, Formatter writer)
	{
		if(subRoot == null)
			return true;
		outputPostOrder(subRoot.getLeft(), writer);
		outputPostOrder(subRoot.getRight(), writer);
		return subRoot.getData().output(writer);
	}
	
	/**
	 * Traverse the tree in pre-Order displaying each node and the identifiers of it's left and right nodes
	 * @param relation - Word that describes the relationship between node and left and right, for example, " children "
	 */
	public void displayChildren(String relation)
	{
		displayChildren(root, relation);
	}
	
	/**
	 * Helper function to traverse pre-Order to display the relationship between node, left and right
	 * @param subRoot - The node to start with
	 * @param relation - String used when displaying the tree to describe the relationship between the nodes
	 */
	private void displayChildren(BinaryTreeNode<T> subRoot, String relation)
	{
		if(subRoot == null)
			return;
		System.out.print(subRoot.getData().getIdentifier()+relation);
		if(subRoot.getLeft()!=null)
			System.out.print(subRoot.getLeft().getData().getIdentifier());
		if(subRoot.getLeft()!=null && subRoot.getRight() != null)
			System.out.print(" and ");
		if(subRoot.getRight() != null)
			System.out.print(subRoot.getRight().getData().getIdentifier());
		if(subRoot.getLeft()==null && subRoot.getRight() == null)
			System.out.print("No Contact");
		System.out.println();
		displayChildren(subRoot.getLeft(), relation);
		displayChildren(subRoot.getRight(), relation);		
	}
}
