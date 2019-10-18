package assignment4;

import java.util.Stack;

public class MyQueue {
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
}
