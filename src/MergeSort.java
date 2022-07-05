
/**
 * Implementation of Merge Sort for a Doubly Linked List
 */
public class MergeSort implements Runnable {
	
	private BinaryTreeNode head, sortedHead;
	private int p, r;
	
	public MergeSort() {}
	
	public MergeSort(BinaryTreeNode head, int p, int r) {
		this.head = head;
		this.p = p;
		this.r = r;
	}
	
	@Override
	public void run() {
		sortedHead = sort(head, p, r);
	}
	
	/**
	 * Sorts the given Doubly Linked List representation of Binary Tree
	 * Returns the head of the sorted Doubly Linked List.
	 */
	public BinaryTreeNode sort(BinaryTreeNode head, int p, int r) {
		if(p >= r) {
			return head;
		}
		
		// find middle node 
		int i = p, q = p + ((r - p) >> 1);
		BinaryTreeNode mid = head;
		while(i < q) {
			mid = mid.getRight();
			i++;
		}
		
		// Split the list into two halves
		BinaryTreeNode head2 = mid.getRight();
		mid.setRight(null);
		
		// sort the left half
		BinaryTreeNode left = sort(head, p, q);
		
		// sort the right half
		BinaryTreeNode right = sort(head2, q + 1, r);
		
		// merge the two sorted halves
		return merge(left, right);
	}

	/*
	 * Merges the two given Doubly Linked List representation of Binary Tree into one.
	 * Returns the head of the merged list.
	 */
	private BinaryTreeNode merge(BinaryTreeNode head1, BinaryTreeNode head2) {
		BinaryTreeNode head = null, ptr = null;
		while(head1 != null && head2 != null) {
			if(head1.getData() < head2.getData()) {
				if(head == null) {
					head = ptr = head1;
				} else {
					ptr.setRight(head1);
					ptr = head1;
				}
				head1 = head1.getRight();
			} else {
				if(head == null) {
					head = ptr = head2;
				} else {
					ptr.setRight(head2);
					ptr = head2;
				}
				head2 = head2.getRight();
			}
		}
		if(head1 != null) {
			ptr.setRight(head1);
		}
		if(head2 != null) {
			ptr.setRight(head2);
		}
		return head;
	}
	
	/**
	 * 
	 * @return the head of Sorted Doubly Linked List.
	 */
	public BinaryTreeNode getSortedHead() {
		return sortedHead;
	}
}
