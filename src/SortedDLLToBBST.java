
/**
 * Given a Sorted DLL, representing Binary Tree nodes, 
 * in-place convert it into a Balanced BST.
 */
public class SortedDLLToBBST implements Runnable {
	
	private BinaryTreeNode head, root;

	public SortedDLLToBBST(BinaryTreeNode head) {
		this.head = head;
	}
	
	@Override
	public void run() {
		buildBBST();
	}
	
	/*
	 * Time Complexity: 
	 * 		T(n) = 2 * T(n/2) + O(n) = O(n log n)
	 * 
	 * where, O(n) time is needed to count the # nodes in DLL.
	 * 		  T(n/2) time is needed to build the left-half of BBST.
	 * 		  T(n/2) time is needed to build the right-half of BBST.
	 * 
	 */
	private void buildBBST() {
		if(head == null) {
			return;
		}
		/*
		 * First, count the number of nodes in the list.
		 */
		int n = 0;
		for(BinaryTreeNode ptr = head; ptr != null; ptr = ptr.getRight()) {
			++n;
		}
		/*
		 * Now, recursively build a Balanced BST.
		 */
		root = build(head, 0, n - 1);
	}
	
	private BinaryTreeNode build(BinaryTreeNode head, int start, int end) {
		// base case
		if(start > end) {
			return null;
		}
		// find mid index to divide the list: O(1)
		int mid = start + (end - start) / 2;
		/*
		 * First, build left-sub-tree
		 */
		BinaryTreeNode left = build(head, start, mid - 1);
		
		BinaryTreeNode root = null;
		
		if(head.getRight() != null) {
			/*
			 * Interchange data between head node and head.next node
			 * This is done to maintain in-place tree build-up.
			 * and to mantain correctness of recursive callbacks.
			 */
			int temp = head.getData();
			head.setData(head.getRight().getData());
			head.getRight().setData(temp);
		
			/*
			 * root node should now point to the head.next node
			 * (not to the head node)!
			 */
			root = head.getRight();
		} else {
			/*
			 * root node points to the last node in the list.
			 */
			root = head;
		}
		/*
		 * Now, get rid of head.next from the given DLL.
		 */
		if(head.getRight() != null) {
			head.setRight(head.getRight().getRight());
		}
		/*
		 * Append left-sub-tree to this root
		 */
		root.setLeft(left);
		/*
		 * Lastly, build the right-sub-tree
		 */
		root.setRight(build(head, mid + 1, end));
		
		return root;
	}
	
	public BinaryTreeNode getRoot() {
		return root;
	}

}
