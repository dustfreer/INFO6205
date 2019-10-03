package assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class assignment2 {

	public static void swap(Integer[] arr, Integer i, Integer j) {
		int temp;
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static int ceilingSearchSortedArray(Integer[] arr, Integer x, int start, int end) {
		if( x < arr[start])
			return start;
		
		if( x > arr[end])
			return end;
		
		int mid = (start + end)/2;
		if( x == arr[mid])
			return mid;
		else if( x < arr[mid]) {
			if( mid - 1 >= 0 && arr[mid-1] > x )
				return mid;
			else 
				return ceilingSearchSortedArray( arr, x, start, mid);
		}
		else {
			if ( mid + 1 <= arr.length && arr[mid+1] > x)
				return mid+1;
			else 
				return ceilingSearchSortedArray( arr, x, mid, end);
		}				
	}
	
	/*
	 * Q1, find two numbers such that they add up to a specific target; 
	 * Designed by Kai Tian;
	 */
	public static Integer[] twoSum(Integer[] nums, Integer target) throws Exception{
		if( nums.length < 0 || target == null)
			throw new Exception("Not Implemented");
		
		Integer[] result = new Integer[2];
		Map<Integer, Integer> num = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++)
			num.put(nums[i], i);
				
		for (int i = 0; i < nums.length; i++) {
			Integer temp = target - nums[i];
			if (num.containsKey(temp) && num.get(i) != temp) {
				result[0] = i;
				result[1] = num.get(temp);
				return result;
			} 
		}
		throw new Exception("Not Implemented");
    }
	
	/*
	 * Q2, compute their intersection; 
	 * Designed by Kai Tian;
	 */
	public static Integer[] intersection(Integer[] nums1, Integer[] nums2) throws Exception {
		if( nums1.length <= 0 || nums2.length <= 0)
			throw new Exception("Not Implemented");
		
		Map<Integer, Integer> num = new HashMap<Integer, Integer>();
		ArrayList<Integer> array= new ArrayList<Integer>(); // implement arraylist to record unknown size array;
		
		for (int i = 0; i < nums1.length; i++) {
			if(!num.containsKey(nums1[i]))
				num.put(nums1[i], i);
		}
		for (int i = 0; i < nums2.length; i++) {
			if(num.containsKey(nums2[i])) {
				array.add(nums2[i]);
				num.remove(nums2[i]);
			}		
		}
		Integer[] result = new Integer[array.size()];
		for (int i = 0; i < array.size(); i++)
			result[i] = array.get(i);
		
		if (array.size() > 0)
			return result;
		else    
			throw new Exception("Not Implemented");
    }
	
	/*
	 * Q3, find a peak index in a mountain array;
	 * Designed by Kai Tian;
	 */
	public static Integer peakIndexInMountainArray(Integer[] A) throws Exception {
	    if ( A.length < 3)	 
	    	throw new Exception("Not Implemented");
	    
	    for (int i = 0; i < A.length - 1; i++)
	    	if (A[i] > A[i+1])
	    		return Integer.valueOf(i);
		throw new Exception("Not Implemented");
	    
	}
	/*
	 * Q4, Move all 1's to start of an array in a Binary array; 
	 * Designed by Kai Tian;
	 */
    public static Integer[] move1sToStart(Integer[] arr) throws Exception {
    	if (arr.length <= 0)
    		throw new Exception("Not Implemented");
    	
    	int low = 0;
    	int high = arr.length - 1;
    	int pivot = 1;
    	
    	while( low < high) {
    		if (arr[low] < pivot) {
    			swap(arr, low, high);
    			high--;    	    			
    		}
    		else {
    			low++;	
    		}    			    			
    	}
    	return arr;
    }

	/*
	 * Q5,Q6, Find closest pair from two sorted arrays; 
	 * Designed by Kai Tian;
	 */  
    public static Integer[] closestPairSumClosestToX(Integer[] arr1, Integer[] arr2, Integer X) throws Exception {
    	if ( arr1.length <=0 || arr2.length <=0)
    		throw new Exception("Not Implemented");
    	
    	int tempDiff = 0;
    	int diff = X;
    	int x = 0,y = 0;
    	for (int i = 0; i < arr1.length; i++)
    		for (int j = 0; j < arr2.length; j++) {
    			int sum = arr1[i] +arr2[j];
    			if ( X < sum )
    				tempDiff = sum - X;
    			else 
    				tempDiff = X - sum;
    			
    			if ( diff > tempDiff ) {
    				diff = tempDiff;
    				x = i;
    				y = j;    			
    			}    		
    		}
    	return new Integer[] {arr1[x],arr2[y]};    		
    }

	/*
	 * Q7, K Closest numbers to a number X; 
	 * Designed by Kai Tian;
	 */      
    
    public static Integer[] kClosestToX(Integer[] arr, Integer k, Integer x) throws Exception  {
    	if( arr.length <= 0 || k > arr.length)    	        
    		throw new Exception("Not Implemented");
    	
    	Integer[] result = new Integer[k];
    	if ( x <= arr[0]) {
    		for ( int i = 0; i < k; i++)
    			result[i] = arr[i];
    		return result;
    	}
    	else if ( x >= arr[arr.length-1]) {
    		for ( int i = arr.length-k-1,j=0; i < arr.length; i++)
    			result[j++] = arr[i];
    		return result;    		    			
    	}
    	else {
    		int highNum = ceilingSearchSortedArray(arr, 23, 0, arr.length-1);
     		int lowNum = highNum - 1;
     		int i = 0;
    		while ( k != 0 ) {
    			if ( x - arr[lowNum]< arr[highNum] - x ) {
    				result[i++] = arr[lowNum];
    				lowNum--;
    				if (lowNum < 0) {
    					break;
    				}
    			}
    			else {
    				result[i++] = arr[highNum];
    				highNum++;
    				if(highNum >= arr.length)
    					break;
    			}
    			k--;    				
    		}
    		
    		while ( i < k && lowNum < 0)
    			result[i++] = arr[highNum++];
    		
    		while ( i < k && highNum >= arr.length)
    			result[i++] = arr[lowNum--];
    		
    		return result;
    	}   	   	
    }

	/*
	 * Q8, remove Duplicates from Sorted Array Given a sorted array nums
	 * Designed by Kai Tian;
	 */ 
    public static Integer removeDuplicates(Integer[] arr) {
    	if ( arr.length <= 0)
    		return null;
    	
    	int count = 0;
    	for (int i = 0; i < arr.length; i++)
    		for (int j = i+1 ; j < arr.length; j++) {
    			if( arr[j] == arr[i] && arr[i] != null) {				
    				arr[j] = null;
    				count++;
    			}
    		}
    	

    	for (int i = 0; i < arr.length; i++) {
    		if (arr[i] == null) {
    			int j = i+1;
    			while(j < arr.length) {
    				if(arr[j] == null)
    					j++;
    				else {
    					arr[i] = arr[j];
    					arr[j] =null;
    					break;
    				}
    			}
    		}
    			
    	}
    	return arr.length-count;
    }
    
	/*
	 * Q9, Smallest Difference pair between two sorted arrays;
	 * Designed by Kai Tian;
	 */ 
    public static Integer[] smallestPairDifference(Integer[] arr1, Integer[] arr2) throws Exception {
    	if (arr1.length <= 0 || arr1.length <= 0)
    		 throw new Exception("Not Implemented");
    	
    	int diff = Math.max(arr1[0], arr2[0]) - Math.min(arr1[0], arr2[0]);
    	int x = 0, y = 0;
    	for(int i = 0; i < arr1.length; i++)
    		for( int j = 0; j < arr2.length; j++) {
    			int tempDiff = Math.max(arr1[i], arr2[j]) - Math.min(arr1[i], arr2[j]);
    			if ( diff > tempDiff) {
    				diff = tempDiff;
    				x = i;
    				y = j;
    			}
    				
    		}
    	return new Integer[] { arr1[x], arr2[y]};
       
    }
    
	/*
	 * Q10,  Check if there are duplicates in k distance;
	 * Designed by Kai Tian;
	 */
    public static boolean duplicatesInKDistance(String str, Integer k) throws Exception {
    	if ( str.length() <= 0 || k < 1)
    		throw new Exception("Not Implemented");
    	
    	for (int i = 0; i < str.length(); i++) {
    		if ( str.charAt(i) == str.charAt(k-1) && i != k-1)
    			return true;
    	}
    	return false;
    }
    
    
	public static void main(String[] args) {
	    Integer[] nums1 = new Integer[]{5, 6, 8, 10, 24};
	    Integer[] nums2 = new Integer[]{12, 15, 20, 25, 30};
	    Integer[] A = new Integer[]{1,2,1}; 
	    Integer[] arr = new Integer[]{5, 6, 8, 10,12,21, 24,25,32,37,45,51};
	    try {
	    	//swap(nums1, 1,2);
//	    	 Integer[] test = smallestPairDifference(nums1, nums2);
//	    	 Integer i = removeDuplicates(A);
//	    	 System.out.print(i);
//			for (Integer a : test)
//				System.out.print(a + " ");
//			
	    	String test = "ABACDEB";
	    	System.out.print(duplicatesInKDistance(test,3));
	    	//System.out.println(peakIndexInMountainArray(A));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
