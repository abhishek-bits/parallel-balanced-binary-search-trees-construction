
public class BinaryTreeNode {
	
	private int data;
	private BinaryTreeNode left, right;
	
	public BinaryTreeNode(int data) {
		this(data, null, null);
	}
	
	// Designated Constructor
	public BinaryTreeNode(int data, BinaryTreeNode left, BinaryTreeNode right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public int getData() {
		return data;
	}
	
	public BinaryTreeNode getLeft() {
		return left;
	}
	
	public BinaryTreeNode getRight() {
		return right;
	}
}
