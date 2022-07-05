# Parallel Construction of Balanced Binary Search Trees

## Guide

[Prof.Tathagata Ray](https://universe.bits-pilani.ac.in/hyderabad/tathagatr/Profile)

## Motivation

Faster database transactions requires data to be stored in efficient format. The search time required to fetch a particular keyword from a database can be minimized if data is stored in some standrard format, thereby the problem of converting unsorted data into balanced binary search trees seems important and it will help improving the overall database processing speed.

## Reference

[Parallel Construction of Binary Trees based on Sorting](https://www.vestnik-donstu.ru/jour/article/view/1440/1429)

## Implementation 

- *Step 1*: Preprocessing is done to assign indices to each Binary Tree Node in the unsorted linked list, so that we can have O(1) lookup time.

- *Step 2*: Applied Parallel Merge Sort such that each thread parallelly runs its own merge sort procedure for the given sub-list.

- *Step 3*: The Heads of sorted doubly linked lists (from step 2) are again passed on to the Parallel Balanaced BST procedure where each thread parallelly runs its own build procedure.

- *Step 4*: The Root nodes of the Balanaced Binary Search Trees (from Step 3) can provide better Time Complexity as a given item can be searched parallelly among all the trees.

## Achievements

Achieved **60% improvement** in the Time complexity cumulatively for both sorting the data and creating the balanced BSTs, both of which are done in parallel using 'k' threads, where 'k' is the count of logical cores in the processing unit.

## Outcomes

- Faster Search Time
- Better Space Utilization
- Tree heights strictly O(log n), where, `(k - 1) * n + (n - 1)` is the total number of nodes in the given unsorted input.
