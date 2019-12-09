package assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import assignment.Assignment5.TreeNode;

public class Final_exam {
	public static class ListNode{
		int val;
		ListNode next;
		ListNode(int val){
			this.val = val;
			next = null;
		}
	}
	/*
	 * Q1, return the head of the final linked list.  
	 * Designed by Kai Tian;
	 */
	public ListNode removeZeroSumSublists(ListNode head) {
        if ( head == null ) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
       
        Map<Integer, ListNode> map = new HashMap<>();    
        int sum = 0;
        for ( ListNode temp = dummy; temp != null; temp = temp.next){
            sum += temp.val;
            map.put(sum, temp);
        }
        
        sum = 0; 
        for ( ListNode temp = dummy; temp != null; temp = temp.next){
            sum += temp.val;
            temp.next = map.get(sum).next;
        }
        return dummy.next;
    }
	
	/*
	 * Q2, You are given two non-empty linked lists representing two non-negative integers.
	 * The most significant digit comes first and each of their nodes contain a single digit. 
	 * Add the two numbers and return it as a linked list.
	 * Designed by Kai Tian;
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if ( l1 == null)
            return l2;
        if ( l2 == null)
            return l1;
        
        int count1 = 0;
        int count2 = 0;
        ListNode temp = l1;
        while ( temp != null){
            temp = temp.next;
            count1++;
        }
        
        temp = l2;
        while ( temp != null){
            temp = temp.next;
            count2++;
        }
        ListNode result1 = new ListNode(0);
        result1.next = l1;
        ListNode result2 = new ListNode(0);
        result2.next = l2;
        int diff = count1 - count2;
        ListNode result = help(result1,result2,diff);
        if ( result.val == 0)       
            return result.next;
        else 
            return result;
    }
    
    
    public ListNode help(ListNode l1, ListNode l2, int diff){
        ListNode temp = null;    
        int preVal = 0;
        if ( diff == 0){
            if ( l1 == null)
                return null;
            temp = help( l1.next, l2.next, diff);
            preVal = l1.val + l2.val;
        }else if ( diff > 0){
            temp = help ( l1.next, l2, diff - 1);
            preVal = l1.val;
        }else {
            temp = help ( l1, l2.next, diff + 1);
            preVal = l2.val;
        }
        if ( temp != null){    
            preVal = preVal + temp.val / 10;
            temp.val = temp.val % 10;          
        }
        ListNode result = new ListNode(preVal);
        
       
        result.next = temp;  
        return result;
    }
	/*
	 * Q3, return the final string after all such duplicate removals have been made.
	 * Designed by Kai Tian;
	 */
    public String removeDuplicates(String s, int k) {
        if ( s.length() == 1 ) return s;
        
        s = help(s,k);
        return s;
    }
    
    public String help(String s, int k){
        for ( int i = 0; i < s.length()-1; i++)
            for ( int j = i; j < s.length(); j++){
                char ch1 = s.charAt(i);
                char ch2 = s.charAt(j);
                if ( ch1 == ch2 ) {
                    if ( j - i == k-1 ){
                        s = s.substring(0,i) + s.substring(j+1);
                        return help(s, k);
                    }                    
                    else continue;
                }else{
                    i = j;
                }
            }
        return s;
    }
    
	/*
	 * Q4, find the number of possible combinations that add up to a positive integer target.
	 * Designed by Kai Tian;
	 */
    
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        
        for ( int i = 1; i <= target; i++){
            for ( int j = 0; j < nums.length; j++){
                if ( i - nums[j] >= 0)
                    dp[i] += dp[i-nums[j]];
            }
        }
        return dp[target];
    }
    
	/*
	 * Q5, Given a 2D board and a word, find if the word exists in the grid.
	 * Designed by Kai Tian;
	 */
    char[][] board;
    String word;
    int m;
    int n;
    public boolean exist(char[][] board, String word) {
        if ( word.length() == 0 ) return false;
             
        this.board = board;
        this.word = word;
        this.m = board.length;
        this.n = board[0].length;
        for ( int i = 0; i < m; i++){
            for ( int j = 0; j < n; j++){
                if ( board[i][j] == word.charAt(0)){
                    if (help(i, j, 0))
                        return true;
                }                
            }
        }     
        return false;
    }
    
    public boolean help(int i, int j, int index){
        if ( index == word.length() ) {
            return true;
        }
        if( i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word.charAt(index)) {
            return false;
        }
        
        char temp = board[i][j];
        board[i][j] = '-';
        if ( help(i+1, j, index+1) ) {
        	return true;
        }
        if ( help(i, j+1, index+1) ) {
        	return true;
        }
        if ( help(i, j-1, index+1) ) {
        	return true;
        }
        if ( help(i-1, j, index+1) ) {
        	return true;
        }
        board[i][j] = temp; 
        	        	                                       
        return false;
    }
    
	/*
	 * Q6, determine the maximum amount of money you can rob tonight without alerting the police.
	 * Designed by Kai Tian;
	 */
    public int rob(int[] nums) {
        int len = nums.length;
        if ( len == 0 ) return 0;
        if ( len == 1 ) return nums[0];
        
        int[] dp1 = new int[len];
        dp1[0] = nums[0];
        dp1[1] = Math.max(nums[0],nums[1]);
        for ( int i = 2; i < len-1; i++){
            dp1[i] = Math.max(dp1[i-1], dp1[i-2]+nums[i]);
        }
        dp1[len-1] = dp1[len-2];
        
        int[] dp2 = new int[len];
        dp2[1] = nums[1];
        for ( int i = 2; i < len ; i++){
            dp2[i] = Math.max(dp2[i-1], dp2[i-2]+nums[i]);
        }
        return Math.max(dp1[len-1],dp2[len-1]);
    }
    
	/*
	 * Q7, Given a sorted array and a target value, return the index if the target is found.
	 * Designed by Kai Tian;
	 */
    public int searchInsert(int[] nums, int target) {
        if ( nums.length == 0) return 0;
        
        int start = 0;
        int end = nums.length-1;
        while ( start <= end){
            int mid = (start+end)/2;
            if ( nums[mid] > target){
                end = mid - 1;
            }else if ( nums[mid] < target){
                start = mid +1;
            }else
                return mid;
        }
        return start;
    }
    
    /*
	 * Q8, Sort a linked list in O(n log n) time using constant space complexity.
	 * Designed by Kai Tian;
	 */
    public ListNode sortList(ListNode head) {
        if ( head == null || head.next == null ) return head;
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode front = head;
        ListNode mid = head;
        while ( front != null && front.next != null ){
            front = front.next.next;
            mid = mid.next;
            dummy = dummy.next;
        }
        dummy.next = null;
        
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        
        head = merge(left, right);
        return head;        
    }
    
    public ListNode merge(ListNode p, ListNode q){
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while ( p != null && q != null){
            if ( p.val < q.val){
                cur.next = p;
                p = p.next;                
            }else{
                cur.next = q;
                q = q.next;
            }
            cur = cur.next;
        }
        if ( p != null){
            cur.next = p;
        }
        if ( q != null){
            cur.next = q;
        }
        return head.next;
    }
    
    /*
	 * Q9, Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the origina
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
	 * Q10, Given a binary tree, return the preorder traversal of its nodes' values.
	 * Designed by Kai Tian;
	 */
	List<Integer> result;
    public List<Integer> preorderTraversal(TreeNode root) {
        result = new ArrayList<>();
        if ( root == null ) return result;
        preOrder(root);
        return result;
    }
    
    public void preOrder(TreeNode root){
        if ( root == null ) return;
        
        result.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }
    
    /*
	 * Q11, Given an encoded string, return its decoded string.
	 * Designed by Kai Tian;
	 */
    public String decodeString(String s) {
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
    public int findEnd(String s, int start){
        int count = 1;
        int i = start;
        while ( i < s.length() ){        
            if ( s.charAt(i) == '[')                        
                count ++;        
            if ( s.charAt(i) == ']')     {
                 count --;
                 if ( count == 0)
                       return i;
            }       
            i++;               
        }      
        return -1;
    }
    
    /*
	 * Q12, Given n pairs of parentheses, 
	 * write a function to generate all combinations of well-formed parentheses.
	 * Designed by Kai Tian;
	 */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        if ( n == 0 ) return result;
        
        henerateParenthesisUtils("", result, 0, 0, n);
        return result;
    }
    
    public void henerateParenthesisUtils(String cur, List<String> result, int left, int right, int n){
        if ( cur.length() == n*2){
            result.add(cur);
            return;
        }
        if ( left < n)
        	henerateParenthesisUtils(cur+"(",result, left+1, right, n);
        if ( right < left )
        	henerateParenthesisUtils(cur+")",result, left, right+1, n);
    }
    
    /*
	 * Q13, You are climbing a stair case. It takes n steps to reach to the top.
	 * Designed by Kai Tian;
	 */
    public int climbStairs(int n) {
        if ( n == 1) return 1;
        
        int n1 = 1;
        int n2 = 2;
        for ( int i = 3; i <= n; i++){
            int result = n1 + n2;
            n1 = n2;
            n2 = result;
        }
        return n2;
    }
    
    /*
	 * Q14, YGiven an integer array nums, find the contiguous subarray within an array (containing at least one number)
which has the largest product.
	 * Designed by Kai Tian;
	 */
    public int maxProduct(int[] nums) {
        if ( nums.length == 0 ) return 0;
        int len = nums.length;
        int[] max = new int[len];
        int[] min = new int[len];
        
        max[0] = nums[0]; min[0] = nums[0];
        int result = nums[0];
        for ( int i = 1; i < nums.length; i++ ){
            max[i] = Math.max(nums[i], Math.max(max[i-1]*nums[i], min[i-1]*nums[i]));
            min[i] = Math.min(nums[i], Math.min(max[i-1]*nums[i], min[i-1]*nums[i]));
            result = Math.max(result, Math.max(max[i], min[i]));
        }
        return result;
    }
    
    /*
	 * Q15, Given a string, your task is to count how many palindromic substrings in this string.
	 * Designed by Kai Tian;
	 */
    public int countSubstrings(String S) {
        int N = S.length(), temp = 0;
        for (int i = 0; i <= 2*N-1; i++) {
            int left = i / 2;
            int right = left + i % 2;
            while (left >= 0 && right < N && S.charAt(left) == S.charAt(right)) {
            	temp++;
                left--;
                right++;
            }
        }
        return temp;
    }
	public static void main(String[] args) {
		ListNode test = new ListNode(4);
		test.next = new ListNode(2);
		test.next.next = new ListNode(1);
		test.next.next.next = new ListNode(3);
		//test.next.next.next.next = new ListNode(-2);
		//System.out.println(sortList(test));
	}
}
