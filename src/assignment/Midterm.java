package assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import assignment.Assignment5.TreeNode;
import assignment.assignment3.ListNode;

public class Midterm {
	/*
	 * Q1, Q1 Given a collection of intervals, merge all overlapping intervals.
	 * Designed by Kai Tian;
	 */
	public static int[][] merge(int[][] intervals) {
		if ( intervals.length <= 1 ) return intervals;
		
		for ( int i = 0; i < intervals.length - 1; i++ ) 
			for ( int j = i+1; j < intervals.length; j++) {			
				if ( intervals[i][0] > intervals[j][0]) {
					swap(intervals,i, j);			
				}
			}
		
		Stack<Integer> s = new Stack<Integer>();
		s.push(intervals[0][0]);
		s.push(intervals[0][1]);
		for ( int i = 1; i < intervals.length; i++) {
			int n1 = intervals[i][0];
			int n2 = intervals[i][1];
			if ( n2 <= s.peek())
				continue;
			if ( s.peek() >= n1 ) {
				s.pop();
				s.push(n2);
			}else {
				s.push(n1);
				s.push(n2);
			}
		}
		int[][] result = new int[s.size()/2][2];
		int i = result.length-1;
		while ( !s.isEmpty() ) {
			result[i][1] = s.pop();
			result[i--][0] = s.pop();
		}
		return result;			
	}	
	public static void swap(int[][] intervals, int i, int j) {
		int[][] temp = new int[1][2];
		temp[0][0] = intervals[i][0];
		temp[0][1] = intervals[i][1];
		intervals[i][0] = intervals[j][0];
		intervals[i][1] = intervals[j][1];
		intervals[j][0] = temp[0][0];
		intervals[j][1] = temp[0][1];
	}
	
	/*
	 * Q2, Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehan
	 * Designed by Kai Tian;
	 */
	public int search(int[] nums, int target) {
		if ( nums.length == 0) return -1;
        int start = 0;
        int end = nums.length-1;
        int result = -1;
        while ( start < end){
            int index = (start+end)/2;
            if ( target == nums[index] ) 
                result = index;
            else if ( nums[index] > nums[start]){
                if ( target < nums[index])
                    end = index -1;
                else
                    start = index + 1;
            }else{
                if ( target > nums[index] && target < nums[end])
                    start = index + 1;
                else
                    end = index - 1;
            }            
        }
        return result;
	}
	
	/*
	 * Q3, Given a linked list, rotate the list to the right by k places, where k is non-negative.
	 * Designed by Kai Tian;
	 */
	public ListNode rotateRight(ListNode head, int k) {
		if ( head == null || head.next == null ) return head;
		
		ListNode temp = head;
		int len = 1;
		while ( temp.next != null) {
			temp = temp.next;
			len++;
		}
		temp.next = head;
		k = len - k % len;
		while ( k > 0 ) {
			head = head.next;
			temp = temp.next;
			k--;			
		}
		temp.next = null;
		return head;
	}
	
	/*
	 * Q4,  Given a singly linked list, group all odd nodes together followed by the even nodes. 
	 * Please note here we are talking about the node number and not the value in the nodes.
	 * Designed by Kai Tian;
	 */
	public ListNode oddEvenList(ListNode head) {
		if ( head == null || head.next == null || head.next.next == null)
			return head;
		ListNode pre = new ListNode(0);
		pre.next = head;
		ListNode back = head.next;
        ListNode temp = back;
		while ( head.next != null && back.next != null) {
			head.next = back.next;
			head = head.next;
			back.next = head.next;
            back = back.next;
		}
		head.next = temp;
		return pre.next;
	}
	
	/*
	 * Q5,  Given a balanced parentheses string S, compute the score of the string based on the following rule:
	 * Designed by Kai Tian;
	 */
	public int scoreOfParentheses(String s) {
		int depth = 0;
        int score = 0;
        for ( int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if ( ch == '('){
                depth++;
            }else{
                depth--;
                if ( s.charAt(i-1) == '('){
                	score += Math.pow(2,depth);
                }
            }
        }
        return score;	        
    }
	
	/*
	 * Q6,  Given an encoded string, return its decoded string.
	 * Designed by Kai Tian;
	 */
    public String decodeString(String s){
    	if ( s == null || s.length() < 1)
            return s;
        
        String result = "";
        int num = 0;
        for ( int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if ( Character.isDigit(c)) {
                num = num * 10;
                num += Character.getNumericValue(c);
            }     
            else if ( c == '['){
                int end = findEnd(s, i+1);
                String res = decodeString(s.substring(i+1, end));
                for ( int j = 0; j < num; j++){
                	result += res;
                }
                num = 0;
                i = end;
                continue;
            }
            else 
            	result += c;
        }
        return result;
        
    }
    
    public static int findEnd(String s, int start){
        int count = 1;
        int i = start;
        while ( i < s.length() ){        
            if ( s.charAt(i) == '[')                        
                count ++;        
            if ( s.charAt(i) == ']'){
                 count --;
                 if ( count == 0)
                       return i;
            }       
            i++;               
        }     
        return -1;
    }
    
	/*
	 * Q7,  Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
	 * Designed by Kai Tian;
	 */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	if ( root == null ) return null;
        if ( root == p || root == q) return root;
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if ( left != null && right != null ) return root;
        if ( left == null && right == null ) return null;
        return (left == null) ? right : left;
	}
    
	/*
	 * Q8,  Given a binary tree, determine if it is a valid binary search tree (BST).
	 * Designed by Kai Tian;
	 */
    public boolean isValidBST(TreeNode root) {
    	return isValidBSTUtil(root, Long.MIN_VALUE, Long.MAX_VALUE);	
	}
    public static  boolean isValidBSTUtil(TreeNode root, long minValue, long maxValue) {
    	if ( root == null )
    		return true;
    	if ( root.val < minValue || root.val > maxValue)
    		return false;
    	return isValidBSTUtil(root.left, minValue, root.val) &&
    			isValidBSTUtil(root.right, root.val, maxValue);
    }
    
	/*
	 * Q9,  Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary
	 * Designed by Kai Tian;
	 */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
    	List<Integer> result = new ArrayList<>();
    	if (root != null) {
    		result.add(root.val);
    		result.addAll(getLeft(root.left, new ArrayList<>(), true));
    		result.addAll(getRight(root.right, new ArrayList<>(), true));
    	}
    	return result;
    }

    private List<Integer> getLeft(TreeNode root, List<Integer> leftPart, boolean isBoundary) {
    	if (root != null) {
    		if (isBoundary || isLeaf(root)) leftPart.add(root.val);
    		getLeft(root.left, leftPart, isBoundary);
    		getLeft(root.right, leftPart, (isBoundary && (root.left == null)));
    	}
    	return leftPart;
    }

    private List<Integer> getRight(TreeNode root, List<Integer> rightPart, boolean isBoundary) {
    	if (root != null) {
    		getRight(root.left, rightPart, (isBoundary && (root.right == null)));
    		getRight(root.right, rightPart, isBoundary);
    		if (isBoundary || isLeaf(root)) rightPart.add(root.val);
    	}
    	return rightPart;
    }

    private boolean isLeaf(TreeNode node) {
    	return node != null && node.left == null && node.right == null;
    }
    
	/*
	 * Q10,  Given two binary trees and imagine that when you put one of them to cover the other, 
	 * some nodes of the two trees are overlapped while the others are not.
	 * Designed by Kai Tian;
	 */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
    	if ( t1 == null || t2 == null) return (t1 == null) ? t2 : t1;
    	t1.val = t1.val + t2.val;
    	mergeTreesUilt(t1, t2, t1.left, t2.left,true);
    	mergeTreesUilt(t1, t2, t1.right, t2.right,false);
    	
    	return t1;    	
    }
    
    public static void mergeTreesUilt(TreeNode p1, TreeNode p2, TreeNode t1, TreeNode t2, boolean isLeft) {
    	if ( (t1 == null && t2 == null) || (t1 != null && t2 == null) )
    		return ;
    	if ( t1 == null && t2 != null ) {
    		if ( isLeft)
    			p1.left = t2;
    		else
    			p1.right = t2;
    		return;
    	}    	
    	t1.val = t1.val + t2.val;    	
    	mergeTreesUilt(t1, t2 , t1.left, t2.left, true);
    	mergeTreesUilt(t1, t2 , t1.right, t2.right, false);
    }
    
	/*
	 * Q11,  Given two sequences pushed and popped with distinct values, 
	 * return true if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.
	 * Designed by Kai Tian;
	 */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
    	if ( pushed.length == 0 || popped.length == 0) return true;
    	Stack<Integer> s = new Stack<Integer>();
    	
    	int i = pushed.length-1;
    	int j = 0;
    	while ( pushed[i] != popped[j] && i >= 0) {
    		s.push(pushed[i]);
    		i--;
    	}

    	while ( j < popped.length) {
    		if ( i >= 0) {
    			int num1 = pushed[i];
        		int num2 = popped[j]; 
        		if ( num1 != num2 ) {
        			if ( s.isEmpty() )
        				return false;
        			else {
        				pushed[i+1] = s.pop();
        				i++;
        			}
        			continue;
        		}else {
        			i--;
        			j++;
        		}
    		}else if ( !s.isEmpty()){
    			pushed[i+1] = s.pop();
				i++;
    		}else
    			return false;
    	}    	
    	return true;
    }
    
	/*
	 * Q12,  Given two sequences pushed and popped with distinct values, 
	 * return true if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.
	 * Designed by Kai Tian;
	 */

    public String reverseParentheses(String s) {
    	if ( s.length() == 0 ) return s;
    	
    	List<String> l = new ArrayList<String>();
    	l.add(new String());
        StringBuilder sb = new StringBuilder();
        int depth = 0;
        for ( int i = 0; i < s.length(); i++) {
        	char ch = s.charAt(i);
        	if ( ch == '(' ) {
        		depth ++;
        		String temp = new String();
        		l.add(temp);
        	}else if ( ch == ')') {
        		sb = new StringBuilder(l.get(depth));
    			l.set(depth-1, l.get(depth-1).concat(sb.reverse().toString()));
    			l.remove(depth);        		        		
        		depth --;        		
        	}else
        		l.set(depth, l.get(depth).concat(String.valueOf(ch)) );
        }        
        return l.get(0);
    }
    
}
