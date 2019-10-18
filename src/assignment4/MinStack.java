package assignment4;

import java.util.Stack;

class MinStack{
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
	
	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		System.out.println(minStack.getMin());
		minStack.pop();
		System.out.println(minStack.top());      
		System.out.println(minStack.getMin());   
	}
}