package assignment4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;



public class assignment4 {

	/*
	 * Q1, Find all the next greater numbers ;
	 * Designed by Kai Tian;
	 */
	public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
	    if ( nums1.length == 0) return nums1;
	    
	    int[] res = new int[nums1.length];
	    Map<Integer,Integer> n1 = new HashMap<Integer,Integer>();
	    for ( int i = 0, j = 0; i < nums1.length; i++) {
	    	n1.put( nums1[i], j++);
	    }
	    	
	    boolean exist = false;
	    for ( int i = 0; i< nums2.length; i++) {
	    	if ( n1.containsKey(nums2[i])) {
	    		for ( int j = i+1; j < nums2.length; j++) {				
	    			if ( nums2[j] > nums2[i]) {
	    				
	    				res[n1.get(nums2[i])] = nums2[j];
	    				exist = true;
	    				break;
	    			}	    			    	
	    		}	
	    		if ( !exist ) res[n1.get(nums2[i])] = -1;
	    		exist = false;
	    	}	
	    }
	    
	    return res;
	}
	
	/*
	 * Q2, vaild characters ;
	 * Designed by Kai Tian;
	 */
	public static boolean isValid(String s) {
		if ( s.length() == 0) return true;

		Stack<Character> sk = new Stack<>();
		for ( int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ( c == '{' || c == '[' || c == '(')
				sk.push(c);
			else if ( c == ')') {
				if ( sk.isEmpty() || sk.pop() != '(') return false;
			}
			else if ( c == ']') {
				if ( sk.isEmpty() || sk.pop() != '[') return false;
			}
			else if ( c == '}') {
				if ( sk.isEmpty() || sk.pop() != '{') return false;
			}
			
		}
		return sk.isEmpty();
	}
	
	/*
	 * Q3, Implement the following operations of a queue using stacks ;
	 * Designed by Kai Tian;
	 */

	Stack<Integer> sk1 = new Stack<Integer>();
	Stack<Integer> sk2 = new Stack<Integer>();
	
	public MyQueue() {
	    
	}
	

	/** Push element x to the back of queue. */
	public void push(int x) {
		sk1.push(x);
	}

	/** Removes the element from in front of queue and returns that element. */
	public int pop() {
		if ( sk2.isEmpty()) {
			while ( !sk1.isEmpty() )
				sk2.push(sk1.pop());
		}
		return sk2.pop();
	}

	/** Get the front element. */
	public int peek() {
		if ( sk2.isEmpty()) {
			while ( !sk1.isEmpty() )
				sk2.push(sk1.pop());
		}

		return sk2.peek();
	}

	/** Returns whether the queue is empty. */
	public boolean empty() {
		return (sk1.isEmpty() && sk2.isEmpty());
	}
	
	/*
	 * Q4, Given two strings S and T, 
	 * return if they are equal when both are typed into empty text editors.
	 * Designed by Kai Tian;
	 */
	
	public static boolean backspaceCompare(String S, String T) {
	    if ( S.length() == 0 && T.length() == 0 ) return true;
	    else if ( S.length() == 0 || T.length() == 0) return false;
	    
	    String s = help(S);
	    String t = help(T);
	    return (s.equals(t));
	    
	}
	
	public static String help(String S) {
		char[] s = S.toCharArray();
		Stack<Character> sk = new Stack<Character>();
		
		for ( int i = 0; i < s.length; i++) {
			if ( s[i] == '#') {
				if ( !sk.isEmpty() ) sk.pop();
			}else {
				sk.push(s[i]);
			}
		}
		
		return sk.toString();
	}
	
	/*
	 * Q5, Design a stack that supports push, pop, top, 
	 * and retrieving the minimum element in constant time.
	 * Designed by Kai Tian;
	 */
	Stack<Integer> sk1 = new Stack<Integer>();
	Stack<Integer> sk2 = new Stack<Integer>();
	public MinStack() {
    
	}

	public void push(int x) {
		sk1.push(x);
		if ( !sk2.isEmpty()) {
			int min = sk2.peek();					
			sk2.push( ( x > min ) ? min : x );
		}else sk2.push(x);
	}

	public void pop() {
		sk1.pop();
		sk2.pop();
	}
	 
	public int top() {
		return sk1.peek();
	}

	public int getMin() {
		return sk2.peek();
	}
	
	/*
	 * Q6, Evaluate the value of an arithmetic expression in Reverse Polish Notation
	 * Designed by Kai Tian;
	 */
	public int evalRPN(String[] tokens) {
		if ( tokens.length == 0) return 0;
		
		Stack<Integer> sk1 = new Stack<Integer>();
		List<String> l = Arrays.asList("+", "-", "*", "/");
		for ( String s : tokens) {
			if ( l.contains(s)) {
				int y = sk1.pop();
				int x = sk1.pop();
				int z = 0;
				if ( s.equals("+")) z = x + y;
				else if ( s.equals("-")) z = x - y;
				else if ( s.equals("*")) z = x * y;
				else if ( s.equals("/")) z = x / y;
				sk1.push(z);
			}
			else sk1.push(stringToInt(s));		
		}
		return sk1.peek();
	}
	public static int stringToInt(String s) {
		if ( s.length() == 0 || s == null) return 0;
		
		boolean isPositive = true;
		int result = 0;
		for ( int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ( !Character.isDigit(c)) isPositive = false;
			else {
				int n = Character.getNumericValue(c);
				result = result * 10 + n;
			}				
		}
		return (isPositive)? result : -result;
	}
	
	/*
	 * Q7, Given an absolute path for a file (Unix-style), 
	 * simplify it. Or in other words, convert it to the canonical path.
	 * Designed by Kai Tian;
	 */
	
	public static String simplifyPath(String path) {
		if ( path.length() == 0 || path == null) return path;
		
		Stack<Character> sk = new Stack<Character>();
		for ( char c : path.toCharArray()) {
			sk.push(c);
		}
		StringBuilder sb = new StringBuilder();
		int count = 0;
		while ( !sk.isEmpty() ) {
			if ( sk.peek() == '/' && sb.length() == 0) {
				sk.pop();
			}
			else if ( sk.peek() == '/' && sb.charAt(0) == '/') {
				sk.pop();
			}
			else if ( sk.peek() == '.') {
				StringBuilder temp = new StringBuilder();
				while ( sk.peek() != '/') temp.append(sk.pop());
				if ( sb.length() != 0 && sb.charAt(0) != '/') {
					sb.insert(0, temp); 
					continue;
				}
				if (temp.length() == 2 ) count++;					
				if (temp.length() > 2 ) sb.insert(0, temp);
			}
			else if ( count != 0 ) {
				while( !sk.isEmpty() && sk.peek() != '/')
					sk.pop();
				count --;
			} 			
			else
				sb.insert(0, sk.pop());
		}
		
		String res = sb.toString();
		return (res.length() == 0) ? "/" : res;
	}
	
	/*
	 * Q8, Given a char array representing tasks CPU need to do. 
	 * It contains capital letters A to Z where different letters represent different tasks
	 * Designed by Kai Tian;
	 */
	public static int leastInterval(char[] tasks, int n) {
		int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int time = 0;
        while (map[25] > 0) {
            int i = 0;
            while (i <= n) {
                if (map[25] == 0)
                    break;
                if (i < 26 && map[25 - i] > 0)
                    map[25 - i]--;
                time++;
                i++;
            }
            Arrays.sort(map);
        }
        return time;    
	}
	
	/*
	 * Q09, Given a non-negative integer num represented as a string, 
	 * remove k digits from the number so that the new number is the smallest possible.
	 * Designed by Kai Tian;
	 */
	public static String removeKdigits(String num, int k) {
	    if ( num.length() == 0 || num == null) return num;
	    
	    String smallest = "0";
	    if ( num.length() == k) return "0";
	    for ( int i = 0; i < k; i++) {
	    	smallest = findSmallest(num);	    	
	    }
	    return smallest;
	}
	
	public static String findSmallest(String num) {
		if ( num.length() == 0 || num == null) return num;
		if ( num.length() == 1) return "0";
		
		String smallest = num;
		for ( int i = 0; i < num.length()-1; i++) {
			String s = num.substring(0,i)+num.substring(i+1,num.length());
			smallest = compare(smallest, s);
		}
		return smallest;
			
	}
	
	public static String compare(String n1, String n2) {
		if ( n1.length() == 0 || n1 == null) return n2;
		if ( n2.length() == 0 || n2 == null) return n1;
		
		while( n1.charAt(0) == '0') n1.substring(1, n1.length());
		while( n2.charAt(0) == '0') n2.substring(1, n2.length());
		
		if ( n1.length() > n2.length()) return n1;
		else if ( n1.length() < n2.length()) return n2;
		else {
			for ( int i = 0; i < n1.length(); i++) {
				if ( n1.charAt(i) == n2.charAt(i) ) continue;
				if ( n1.charAt(i) > n2.charAt(i)) return n1;
				else return n2;
			}
			
		}
			
	}
	
	
	/*
	 * Q10, Given a nested list of integers, implement an iterator to flatten it.
	 * Designed by Kai Tian;
	 */
	private List<NestedInteger> nestedList = new ArrayList<NestedInteger>();
    List<Integer> result = new ArrayList<Integer>();
    int count;
    public NestedIterator(List<NestedInteger> nestedList) {
        result = flatten(nestedList);
        count = 0;
    }

    @Override
    public Integer next() { 
        if ( hasNext() ) return result.get(count++);
	    return null;	
    }

    @Override
    public boolean hasNext() {
        if ( count < result.size() )
	    	return true;
	    return false;
    }
    
    public List<Integer> flatten( List<NestedInteger> l ){
        List<Integer> result = new ArrayList<Integer>();
        
        for ( int i = 0; i < l.size(); i++){

            if ( l.get(i).isInteger() ){
                result.add( l.get(i).getInteger() );
            }else{
                result.addAll( flatten(l.get(i).getList()) );
            }            
        }
        return result;
    }
	
	/*
	 * Q11, Implement a queue by using two stacks and Optimize poll function(Dequeue() in C#).
	 * Designed by Kai Tian;
	 */
	Stack<Integer> sk1 = new Stack<Integer>();
	Stack<Integer> sk2 = new Stack<Integer>();
	
	public void add(int data) {
		sk1.push(data);
	}
	public int poll() {
		if ( sk2.isEmpty()) {
			while ( !sk1.isEmpty() )
				sk2.push(sk1.pop());
		}
		return sk2.pop();
	}
	public int getCount() {
		return sk1.size() + sk2.size();
	}
	
	/*
	 * Bonus, Implement a basic calculator to evaluate a simple expression string.
	 * Designed by Kai Tian;
	 */
	//(1+(4+5+2)-3)+(6+8)	
	public static int calculate(String s) {
	    if ( s.length() == 0) return 0;
	    if ( s.length() == 1) return Integer.valueOf(s);
	    
	    Stack<Object> sk = new Stack<Object>();
	    int number = 0;
	    for ( int i = s.length()-1; i >= 0; i--) {
	    	char c = s.charAt(i);
	    	if ( Character.isDigit(c) ) {
	    		int power = 0;
	    		while ( i >= 0 && Character.isDigit(s.charAt(i)) ) {
	    			number = number + (int)(s.charAt(i) - '0') * (int)Math.pow(10 , power++);
	    			i--;
	    		}
	    		sk.push(number);
	    		if ( i < 0) break;
	    		c = s.charAt(i);
	    		number = 0;
	    	}
	    	if ( c == '(') {	    
	    		number = (int)sk.pop();
	    		while ( !sk.isEmpty() && (char) sk.peek() != ')') {
	    			if ( (char)sk.pop() == '+') 
	    				number += (int)sk.pop();
	    			else {
	    				number -= (int)sk.pop();
	    			}
	    		}
	    		sk.pop();
	    		sk.push(number);
	    		number = 0;
	    		continue;
	    	}
	    	if ( c == ' ' ) continue;
	    	sk.push(c);	    		
	    }
	    
	    number = (int)sk.pop();
	    while ( !sk.isEmpty()) {
	    	if ( (char)sk.pop() == '+') {
				number += (int)sk.pop();
			}else{
				number -= (int)sk.pop();
			}
	    }
	    return number;
	}

	
	public static void main(String[] args) {
		Stack<Character> sk = new Stack<Character>();
		sk.push('8');
		sk.push('+');
		sk.push('8');
		sk.push('-');
		sk.push('8');
		System.out.println(help(0,sk));
	}
	


}
