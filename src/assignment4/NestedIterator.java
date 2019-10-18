package assignment4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NestedIterator implements Iterator<Integer>{
	
	 public interface NestedInteger {
		 
		      // @return true if this NestedInteger holds a single integer, rather than a nested list.
		      public boolean isInteger();
		 
		      // @return the single integer that this NestedInteger holds, if it holds a single integer
		      // Return null if this NestedInteger holds a nested list
		      public Integer getInteger();
		 
		      // @return the nested list that this NestedInteger holds, if it holds a nested list
		      // Return null if this NestedInteger holds a single integer
		      public List<NestedInteger> getList();
		  }
	
	private List<NestedInteger> nestedList = new ArrayList<NestedInteger>();
	private Integer i = 0;
	public NestedIterator(List<NestedInteger> nestedList) {
	    this.nestedList = nestedList;   
	}

	public Integer next() {	
		 if ( hasNext() ) {
		    	if ( nestedList.get(i).isInteger() ) {
		    		Integer out = nestedList.get(i).getInteger();		    		
		    		nestedList.remove(0);
		    		return out;
		    	}
		    	else {
		    		List<NestedInteger> l = nestedList.get(i).getList();
		    		NestedIterator ni = new NestedIterator(l);
		    		return ni.next();
		    	}
		    }
	        return null;	
	}

	public boolean hasNext() {
	    if ( nestedList.isEmpty() )
	    	return false;
	    return true;
	}

	
	public static void main(String[] args) {
		List<Integer> l = new ArrayList<Integer>();
		l.add(0);
		l.add(1);
		l.add(2);
		l.remove(0);
		System.out.println(l.toString());

	}

}
