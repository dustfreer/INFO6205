package assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Assignment5 {
	public static class TreeNode {
		  int val;
		  TreeNode left;
		  TreeNode right;
		  TreeNode(int x) { val = x; }
	}
	
	/*
	 * Q1, Given the root node of a binary search tree (BST) and a value. 
	 * You need to find the node in the BST that the node's value equals the given value.
	 * Designed by Kai Tian;
	 */
	public TreeNode searchBST(TreeNode root, int val) {
	    if ( root == null) return root;
	    
	    if ( root.val == val)
	    	return root;
	    else if ( root.val > val) {
	    	return searchBST(root.left, val);
	    }else
	    	return searchBST(root.right, val);	     
	}
	
	/*
	 * Q2, Given a binary search tree, 
	 * rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree,
	 * and every node has no left child and only 1 right child.
	 * Designed by Kai Tian;
	 */
	public static TreeNode increasingBST(TreeNode root) {
	    if ( root == null) return root;
	    
	    Queue<Integer> q = new LinkedList<Integer>();
	    help(root, q);
	    TreeNode result = new TreeNode(q.poll());
	    TreeNode temp = result;
	    while ( !q.isEmpty()) {
	    	int val = q.poll();
	    	temp.right = new TreeNode(val);
	    	temp = temp.right;    	
	    }
	    return result;  
	}
	public static void help(TreeNode root, Queue<Integer> q) {
		if ( root == null) return;
		help(root.left,q);
		q.add(root.val);
		help(root.right,q);
	}
	
	/*
	 * Q3, Invert a binary tree.
	 * Designed by Kai Tian;
	 */
	public static TreeNode invertTree(TreeNode root) {
	    if ( root == null) return root;
	    
	    TreeNode left = invertTree(root.left);
	    TreeNode right = invertTree(root.right);
	    root.left = right;
	    root.right = left;

	    return root;
	    
	}
	
	/*
	 * Q4, convert it to a height balanced BST.
	 * Designed by Kai Tian;
	 */
	public TreeNode sortedArrayToBST(int[] nums) {
	    if ( nums.length == 0) return null;
	    
	    int start = 0;
	    int end = nums.length-1;
	    TreeNode result = helpSort(nums, start, end);
	    return result;
	}
	
	public TreeNode helpSort(int[] n, int start, int end) {
		if ( start > end) return null;
		
		int cur = ((start+end)%2 == 0) ? (start+end)/2 : (start+end)/2+1 ;
		TreeNode curNode = new TreeNode(n[cur]);
		if ( start == end) return curNode;
		
		curNode.left = helpSort(n, start, cur-1);
		curNode.right = helpSort(n, cur+1, end);
		
		return curNode;
				
	}
	
	/*
	 * Q5, onvert it to a Greater Tree such that every key of the original BST
	 * Designed by Kai Tian;
	 */
	public TreeNode convertBST(TreeNode root) {
		if ( root == null ) return root;

	    HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
	    inOrderTraversal(root, m);
	    HashMap<Integer, Integer> result = new HashMap<Integer, Integer>(m);
	    for ( Map.Entry<Integer, Integer> entry : result.entrySet()) {
		    int sum = entry.getValue();
	    	for ( Map.Entry<Integer, Integer> entry1 : m.entrySet()) {
	    		if ( entry1.getValue() > entry.getKey())
	    			sum += entry1.getKey();
	    	}
	    	result.put(entry.getKey(), sum);
	    	sum = 0;
	    }
	    AddinOrderTraversal ( root, result);
	    return root;
	    
	}
	
	public static void inOrderTraversal(TreeNode root, HashMap<Integer, Integer> m) {
		if ( root == null ) return ;
		
		m.put(root.val, root.val);
		inOrderTraversal(root.left, m);		
		inOrderTraversal(root.right, m);		

	}
	public static void AddinOrderTraversal(TreeNode root, HashMap<Integer, Integer> m) {
		if ( root == null ) return ;
		
		root.val = m.get(root.val);
		AddinOrderTraversal(root.left, m);
		AddinOrderTraversal(root.right, m);		

	}
	
	/*
	 * Q6, Construct the maximum tree by the given array and output the root node of this tree.
	 * Designed by Kai Tian;
	 */
	public TreeNode constructMaximumBinaryTree(int[] nums) {
	    if ( nums.length == 0) return null;
	    int start = 0;
	    int end = nums.length;
	    return helpMaximumTree(nums, start, end-1);
	}
	
	public TreeNode helpMaximumTree(int[] nums, int start, int end) {
		if ( start > end) return null;
		
		int max = findMax(nums, start, end); 
		TreeNode node = new TreeNode(nums[max]);
		if ( start == end ) return node;
		
		if ( start < end) {
			node.left = helpMaximumTree(nums, start, max-1);
			node.right = helpMaximumTree(nums, max+1, end);
		}
		return node;
	}
	
	public int findMax(int[] nums, int start, int end) {
		int max = Integer.MIN_VALUE;
		int out = start;
		for ( int i = start; i <= end; i++) {
			if ( max < nums[i] ) {
				max = nums[i];
				out = i;
			}
		}
		return out;
	}
	/*
	 * Q7, Given a binary tree, return the inorder traversal of its nodes' values.
	 * Designed by Kai Tian;
	 */
	public List<Integer> inorderTraversal(TreeNode root) {
	    if ( root == null) return null;
	    
	    List<Integer> result = new ArrayList<Integer>();
	    inorder(root, result);
	    return result;
	}
	
	public void inorder(TreeNode root, List<Integer> l) {
		if ( root == null) return;
				
		inorder(root.left, l);
		l.add(root.val);
		inorder(root.right,l);		
	}
	
	/*
	 * Q8, Given preorder and inorder traversal of a tree, construct the binary tree.
	 * Designed by Kai Tian;
	 */
	int index = 0;
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if ( preorder.length != inorder.length ) return null;
	    
	    int start = 0;
	    int end = preorder.length;
	    
	    TreeNode result = helpBuild(preorder, inorder, start, end-1);
	    return result;
	}
	public int search(int[] inorder, int num, int start, int end) {
		for ( int i = start; i <= end; i++) {
			if ( inorder[i] == num)
				return i;
		}
		return 0;
	}
	public TreeNode helpBuild(int[] preorder, int[] inorder, int start, int end) {
		if ( start > end) return null;
		
		TreeNode node = new TreeNode(preorder[index++]);
		if ( start == end ) return node;
		
		int cur = search(inorder, node.val, start, end);
		node.left = helpBuild(preorder, inorder, start, cur-1);
		node.right = helpBuild(preorder, inorder, cur+1, end);
		
		return node; 
    
	}
	
	/*
	 * Q9, Given a binary tree and a sum, 
	 * find all root-to-leaf paths where each path's sum equals the given sum.
	 * Designed by Kai Tian;
	 */
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
	    List<Integer> temp = new ArrayList<Integer>();
	    
	    calculatePathSum(root, sum, 0, result, temp);
	    
	    return result;
	    
	}
	public static void calculatePathSum(TreeNode root, int sum, int i, List<List<Integer>> result, List<Integer> temp) {
		if ( root == null) return;
		
		temp.add(i, root.val);
		if ( root.left == null && root.right == null) {
			int sum1 = 0;
			for ( int n : temp)
				sum1 += n;
			if ( sum1 == sum )
				result.add(new ArrayList<Integer>(temp));
		}else {
			calculatePathSum(root.left, sum, i+1, result, temp);
			calculatePathSum(root.right, sum, i+1, result, temp);
		}				
	}
	
	/*
	 * Q10, Given a binary tree, return the postorder traversal of its nodes' values.
	 * Designed by Kai Tian;
	 */
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
	    if ( root == null) return result;
	    postorder(root, result);
	    return result;
	}
	public void postorder(TreeNode root, List<Integer> l) {
		if ( root == null) return;
				
		postorder(root.left, l);
		postorder(root.right,l);
		l.add(root.val);
	}
}
