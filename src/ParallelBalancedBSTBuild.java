
public class ParallelBalancedBSTBuild {

	// number of logical processors available
	private int procs;
	
	// each logical core will separately handle its own BST build procedure
	private Thread[] threads;
	
	// instances of SortedDLLToBBST procedures.
	private SortedDLLToBBST[] bstBuild;
	
	// local reference to the given heads of sorted doubly linked lists
	private BinaryTreeNode[] sortedHeads;
	
	// stores the root nodes of the balanced Binary Search Trees.
	private BinaryTreeNode[] bbstRoots;
	
	public ParallelBalancedBSTBuild(BinaryTreeNode[] sortedHeads) {
		procs = Runtime.getRuntime().availableProcessors();
		threads = new Thread[procs];
		bstBuild = new SortedDLLToBBST[procs];
		bbstRoots = new BinaryTreeNode[procs];
		this.sortedHeads = sortedHeads;
		init();
	}
	
	/*
	 * Initiates the build process for all Binary Trees parallely.
	 */
	private void init() {
		
		// create instances of Build procedures using the sorted heads
		for(int i = 0; i < procs; i++) {
			bstBuild[i] = new SortedDLLToBBST(sortedHeads[i]);
		}
		
		// create instances of Threads for each build instance.
		for(int i = 0; i < procs; i++) {
			threads[i] = new Thread(bstBuild[i]);
		}
		
		// start the build procedure
		for(int i = 0; i < procs; i++) {
			threads[i].start();
		}
		
		// we'll wait until all threads are over
		for(int i = 0; i < procs; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// finally, store the root nodes of the balanced BSTs.
		for(int i = 0; i < procs; i++) {
			bbstRoots[i] = bstBuild[i].getRoot();
		}
		
	}
	
	public BinaryTreeNode[] getRoots() {
		return bbstRoots;
	}
}
