import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		BinaryTreeNode head = null, tail = null;
 		int[] items 
// 		= {7, 14, 1, 17, 19, 4, 3};
// 		= new int[1000];
// 		for(int i = 0; i < 1000; i++) {
// 			items[i] = new Random().nextInt(100);
// 		}
 		= new int[1000000];
 		for(int i = 0; i < 1000000; i++) {
 			items[i] = new Random().nextInt(10000);
 		}
		
 		//  index -> node table for constant lookup
 		Map<Integer,BinaryTreeNode> nodesTable = new HashMap<>();
 		
		for(int i = 0; i < items.length; i++) {
			if(head == null) {
				head = tail = new BinaryTreeNode(items[i]);
			} else {
				tail.setRight(new BinaryTreeNode(items[i]));
				tail = tail.getRight();
			}
			nodesTable.put(i, tail);
		}
		
		long startTime, endTime;
		
//		startTime = System.currentTimeMillis();
//		
//		BinaryTreeNode sortedHead = new MergeSort().sort(head, 0, nodesTable.size() - 1);
//		
//		endTime = System.currentTimeMillis();
//		
//		System.out.println("Sequential Sort procedure took: "+(endTime - startTime)+" ms.");
		
		startTime = System.currentTimeMillis();
		
		// Sort the linked list using Parallel Merge Sort procedure
		BinaryTreeNode[] sortedHeads = new ParallelMergeSort().sort(nodesTable);
		
		// parallelly build Balanced Binary Search Trees (in-place)
		BinaryTreeNode[] bbstRoots = new ParallelBalancedBSTBuild(sortedHeads).getRoots();
		
		endTime = System.currentTimeMillis();
		
		System.out.println("Parallel Sort Procedure AND Balanced BST build procedure together took: "+(endTime - startTime)+" ms.");
		
		/*
		 * Now, search for a given item can be parallely run
		 * over the given array of bbst roots. The first thread
		 * that terminates and returns true for search will terminate
		 * the search procedure.
		 */
	}
}
