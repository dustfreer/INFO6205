package assignment4;

import java.util.Stack;

public class quene {

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
}
