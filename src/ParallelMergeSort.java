import java.util.Map;

/**
 * Runs the Merge Sort Algorithm parallelly for the given Doubly Linked List representation of a Binary Tree.
 */
public class ParallelMergeSort {

	// number of logical processors available
	private int procs;
	
	// each logical core will separately handle its own merge sort instance
	private Thread[] threads;
	
	// instances of merge sort procedures
	private MergeSort[] mergeSorts;
	
	// typically store the result of the merge sort per thread.
	private BinaryTreeNode[] sortedHeads;
	
	public ParallelMergeSort() {
		procs = Runtime.getRuntime().availableProcessors();
		threads = new Thread[procs];
		mergeSorts = new MergeSort[procs];
		sortedHeads = new BinaryTreeNode[procs];
	}
	
	/**
	 * Parallely applies Merge Sort procedure to the given data.
	 * @param nodesTable the index based Doubly Linked Lists (of Binary Tree Nodes) for O(1) lookup.
	 * @return the heads of the sorted doubly linked lists
	 */
	public BinaryTreeNode[] sort(Map<Integer,BinaryTreeNode> nodesTable) {
		
		int size = nodesTable.size();
		
		int offset = size / (procs - 1);
		int rem = size % (procs - 1);
		
		int p = 0, r = offset;
		
		for(int i = 0; i < procs - 1; i++) {
			// make sure that the tail node of this sub-list points to null
			// this is done to avoid abnormal behaviour.
			nodesTable.get(r - 1).setRight(null);
			// now instatiate the merge sort procedure with this sub-list
			mergeSorts[i] = new MergeSort(nodesTable.get(p), 0, offset - 1);
			p = r;
			r = r + offset;
		}
		mergeSorts[procs - 1] = new MergeSort(nodesTable.get(p), 0, rem - 1);
		
		for(int i = 0; i < procs; i++) {
			threads[i] = new Thread(mergeSorts[i]);
		}
		
		// start merge sort for each thread
		for(int i = 0; i < procs; i++) {
			threads[i].start();
		}
		
		// we should wait until all threads are over.
		for(int i = 0; i < procs; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// store the results of each sorted list
		// (heads of the sorted doubly linked lists)
		for(int i = 0; i < procs; i++) {
			sortedHeads[i] = mergeSorts[i].getSortedHead();
		}
		
		// return the final result
		return sortedHeads;
	}
	
}
