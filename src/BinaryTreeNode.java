
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
	
	public void setData(int data) {
		this.data = data;
	}
	
	public BinaryTreeNode getLeft() {
		return left;
	}
	
	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}
	
	public BinaryTreeNode getRight() {
		return right;
	}
	
	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "["+data+"]";
	}
}
