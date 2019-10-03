package assignment;

import java.util.HashMap;
import java.util.Map;

public class assignment3 {
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			this.val = x;
		}
	}

	/*
	 * Q1, remove the n-th node from the end of list and return its head; 
	 * Designed by Kai Tian;
	 */
	public static ListNode removeNthFromEnd(ListNode head, int n) {
    	if ( head == null || n < 0)
    		return null;
    	
        ListNode back = head;
        
        int count = countList(head);	
        if ( n >= count)
        	return null;
        
        for (int i = 0; i < count - n - 1; i++) {
        	back = back.next;
        }
        back.next = null;
        return head;
    }

	/*
	 * Q2, swap every two adjacent nodes and return its head.
	 * by Kai Tian;
	 */
    public static ListNode swapPairs(ListNode head) {
        if ( head == null || head.next == null )
        	return head;
        
        ListNode front = head;
        ListNode temp1 = head;
        ListNode temp2;

        ListNode back;
        head = head.next;
       
        while ( front != null && front.next != null ) {
        	front = front.next;
        	temp2 = front;  
        	front = front.next;
        	temp2.next = temp1;
        	
        	if ( front == null) {
        		temp1.next = null;
        		return head;
        	}else if ( front.next == null) {
        		temp1.next = front;
        		return head;
        	}
        	back = front.next;
        	temp1.next = back; 
        	temp1 = front;
        	temp2 = front;
        }
        return head;
    }

	/*
	 * Q3, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list
	 * Designed by Kai Tian;
	 */
    public static ListNode deleteDuplicates(ListNode head) {      
    	if ( head == null || head.next == null)
        	return head;
        
    	ListNode prehead = new ListNode(-1);
    	prehead.next = head;
    	ListNode cur = head;
    	
    	ListNode result = prehead.next;
    	
    	while ( cur != null) {
    		if ( cur.next != null && cur.next.val == cur.val) { 
    			
    			prehead.next = nextNoneDuplicateNode(cur.next);
    			cur = prehead.next;
    		}else {
    			prehead = cur;
    			cur = cur.next;
    		}    		
    	}
    	return result;
    	         
    }
    
    public static ListNode nextNoneDuplicateNode(ListNode node) {
    	int n = node.val;
    	while ( node != null && node.val == n)
    		node = node.next;
    	return node;
    }
    
	/*
	 * Q4, Given a sorted linked list, delete all duplicates such that each element appear only once
	 * Designed by Kai Tian;
	 */
    public ListNode deleteDuplicates1(ListNode head) {
        if ( head == null)
        	return null;
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int i = 0;
        ListNode temp = head;
        map.put(i++, head.val);
        while ( head.next != null) {
        	if ( !map.containsValue(head.next.val)) {        	
        		map.put(i++, head.next.val);
        		head = head.next;
        	} else {
        		head.next = head.next.next;
        	}
        	
        }
        return temp;
    }
    
	/*
	 * Q5, return deep copy;
	 * Designed by Kai Tian;
	 */
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
        
    }

    public Node copyRandomList(Node head) {
    	if (head == null) {
			return null;
		}

		Node oriHead = head;
		while (oriHead != null) {
			Node add = new Node(oriHead.val, null, null);
			add.next = oriHead.next;
			oriHead.next = add;
			oriHead = oriHead.next.next;
		}

		Node tempHead = head;
		Node addHead = head;
		addHead = addHead.next;
		while (tempHead != null) {
			addHead.random = tempHead.random;
			if ( addHead.next == null)
				break;
			addHead = addHead.next.next;
			tempHead = tempHead.next.next;
		}

		Node oriResult = head;
		Node result = head;
		result = result.next;
		Node result1 = result;
		while (oriResult != null) {
			oriResult.next = result.next;
			oriResult = oriResult.next;
			if ( oriResult != null) {
				result.next = oriResult.next;
				result = result.next;
			}
		}
		return result1;
    }
    
	/*
	 * Q6, Sort a linked list using insertion sort;
	 * Designed by Kai Tian;
	 */

    public static ListNode insertionSortList(ListNode head) {
        if ( head == null || head.next == null)
        	return head;
        
        ListNode node = new ListNode(head.val);
        ListNode result = node;
        ListNode temp = head;
        ListNode mid = null;
        temp = temp.next;
        head.next = null;
        while ( temp != null) {	
        	if ( temp.val >= node.val) {		
        		if ( node.next == null) {
           			node.next = temp;	
        			mid = temp.next;	
        			temp.next = null;	
        		}else {     					
        			while ( node.next != null) {
        				if (temp.val < node.next.val) {       					
                    		mid = temp.next;
                    		temp.next = node.next;
                    		node.next = temp;
                    		break;
            			}else
            				node = node.next;
        			}
        			
        		}
        	}	
        	else {	
        		mid = temp.next;
          		temp.next = node;	
        		result = temp;
        		}
        	node = result;
        	temp = mid;
        }
        return result;
    }

	/*
	 * Q7, Write a function to delete a node ;
	 * Designed by Kai Tian;
	 */
    public static void deleteNode(ListNode node, int val) {
        
    	ListNode temp = new ListNode(-1);
    	temp.next = node;
    	ListNode result = temp;
    	while ( node != null) {
    		if ( node.val == val)
    			temp.next = node.next;
    		else {
    			temp = node;
    		}  
    		node = node.next;
    	}
    	result = result.next;    	
    }
    
	/*
	 * Q8, return a middle node of linked list
	 * Designed by Kai Tian;
	 */
	public static ListNode middleNode(ListNode head) {
        if ( head == null || head.next == null)
        	return head;
        
        int count = countList(head);
        if ( count % 2 == 0) {
        	int i = count/2;
        	while ( i > 0) {
        		head = head.next;
        		i--;
        	}
        }else {
        	int i = count/2;
        	while ( i > 0) {
        		head = head.next;
        		i--;
        	}
        }
        return head;
    }
	
	/*
	 * Q9, Reverse a linked list from position m to n. Do it in one-pass
	 * Designed by Kai Tian;
	 */

	public static ListNode reverseBetween(ListNode head, int m, int n) {
        if ( head == null || head.next == null)
        	return head;
        
        ListNode front = head;
        int i = m;
        int j = n;
        while ( i > 2) {
        	front = front.next;
        	i--;
        }
        ListNode front1 = front;
        front = front.next;
        
        ListNode back = head;
        while ( j > 1) {
        	back = back.next;
        	j--;
        }
        
        ListNode back1;
        ListNode mid = front;
        front = front.next;
        mid.next = back.next;
        

        while ( n - m > 0 ) {
        	back1 = mid;
        	mid = front;
        	front = front.next;
        	mid.next = back1;        
        	m++;        	        	
        }
        front1.next = mid;
        return head;

    }
	
	/*
	 * Q10, Add the two numbers and return it as a linked list
	 * Designed by Kai Tian;
	 */

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {     
		if ( l1 == null)
			return l2;
		if ( l2 == null)
			return l1;
		
		ListNode result;
		int count1 = countList(l1);
		int count2 = countList(l2);
		if ( count1 > count2) {
			result = l1;
			int n = count1 - count2;
			while ( n > 0) {
				l1 = l1.next;
				n--;
			}
			while ( l1 != null) {
				l1.val = l1.val + l2.val;
				l1 = l1.next;
				l2 = l2.next;
			}
		}else {
			result = l2;
			int n = count2 - count1;
			while ( n > 0) {
				l2 = l2.next;
				n--;
			}
			while ( l2 != null) {
				l2.val = l1.val + l2.val;
				l1 = l1.next;
				l2 = l2.next;
			}
		}
		
		result = reverse(result);
		ListNode temp = result;
		while ( result != null) {
			if ( result.val >= 10) {
				result.val = result.val % 10;
				result = result.next;
				result.val++;
			}else 
				result = result.next;
		}
		temp = reverse(temp);
		return temp;
		
    }
	
	/*
	 * Q10 follow up, no reserve, Add the two numbers and return it as a linked list
	 * Designed by Kai Tian;
	 */
	public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {     
		if ( l1 == null)
			return l2;
		if ( l2 == null)
			return l1;
	
		int count1 = countList(l1);
		int count2 = countList(l2);
		
		ListNode result1 = new ListNode(0);
		result1.next = l1;
        ListNode result2 = new ListNode(0);
        result2.next = l2;
        ListNode head = helpAddTwoNumbers(result1, result2, count1 - count2);
        return head.val == 0 ? head.next : head;
		
	}
	
	public static ListNode helpAddTwoNumbers(ListNode l1, ListNode l2, int diff) {
		int val = 0;
		ListNode temp = null;
		if ( diff == 0) {
			if (l1 == null) {
                return null;
            }
			temp = helpAddTwoNumbers( l1.next, l2.next, diff);
			val = l1.val + l2.val;
		}else if ( diff > 0) {
			temp = helpAddTwoNumbers( l1.next, l2, diff-1);
			val = l1.val;
		}else {
			temp = helpAddTwoNumbers( l1, l2.next, diff-1);
			val = l2.val;
		}
		if ( temp != null) {
			val += temp.val / 10;
			temp.val = temp.val % 10;
		}
		ListNode cur = new ListNode(val);
        cur.next = temp;
        return cur;
			
	}
	
	public static void PrintList(ListNode head){	
		ListNode temp = head;
	    while(temp != null){	    
	    	System.out.print(temp.val + "-> ");	            
	    	temp = temp.next;
	    }
	    System.out.println("NULL");	
	}
	
	public static int countList(ListNode head) {
		int count = 0;
		while ( head != null) {
			head = head.next;
			count++;				
		}
		return count;	
	}
	
	public static ListNode reverse(ListNode head) {
		if ( head == null || head.next == null)
			return head;
		
		ListNode back = null;
		ListNode mid = head;
		ListNode front = head;
		front = front.next;
		while ( front != null) {
			mid.next = back;
			back = mid;
			mid = front;
			front = front.next;
		}
		mid.next = back;
		return mid;
	}
	

    

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);	    
		node1.next = new ListNode(2);	    
		node1.next.next = new ListNode(3);	    
		node1.next.next.next = new ListNode(4);
		//node1.next.next.next.next = new ListNode(5);
		//node1.next.next.next.next.next = new ListNode(8);
		//deleteNode(node1, 6);
		
		ListNode node2 = new ListNode(9);	    
		node2.next = new ListNode(2);	    
		node2.next.next = new ListNode(3);	    
		node2.next.next.next = new ListNode(4);
		node2.next.next.next.next = new ListNode(1);
		PrintList(insertionSortList(node2));
		
		
		
		
		
	}
}
