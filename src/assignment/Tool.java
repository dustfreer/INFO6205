package assignment;

public class Tool {

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
}
