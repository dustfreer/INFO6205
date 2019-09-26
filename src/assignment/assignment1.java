package assignment;

public class assignment1 {

	/*
	 * Q1, find the maximum value'index in array; 
	 * Designed by Kai Tian;
	 */
	public static int maxIndex(int[] arr) {
		if (arr.length == 0)
			return -1;

		int index = 0;
		int maxValue = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (maxValue < arr[i]) {
				maxValue = arr[i];
				index = i;
			}
		}
		return index;
	}

	/*
	 * Q2, Kth Largest Element in an Array 
	 * Designed by Kai Tian;
	 */
	public static int findKthLargest(int[] nums, int k) {
		if (nums.length == 0 || nums.length < k)
			return -1;

		sort(nums);
		return nums[nums.length-k];
	}
	
	/*
	 * Q3, find index of two numbers such that they add up to a specific targe;
	 * Designed by Kai Tian;
	 */
	public static int[] twoSum(int[] nums, int target) {
		if (nums.length <= 1)
			return null;
		
		int[] index = new int[2];
		for (int i = 0; i < nums.length-1; i++)
			for (int j = i+1; j< nums.length; j++) {
				if (nums[i] == target - nums[j]) {
					index[0] = i;
					index[1]= j;
					break;
				}
			}
		return index;
			
	}
	
	/*
	 * sort
	 * Designed by Kai Tian;
	 */
	static void sort(int[] arr) {
		if (arr.length == 0)
			return;
		int k;
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i; j < arr.length; j++) {
				if (arr[j] < arr[i]) {
					k = arr[i];
					arr[i] = arr[j];
					arr[j] = k;
				}
			}
		}
	}

	/*
	 * Q4, find the first unique char in a string;
	 * Designed by Kai Tian;
	 */
	static int firstUniqChar(String s) {
		if( s.length() == 0)
			return -1;
		
		char[] char1 = s.toCharArray();
		int index = -1;
		for (int i = 0; i < char1.length - 1; i++) {
			boolean repeat = false;
			int j = i + 1;
			while ( j < char1.length) {
				if ( char1[j] == char1[i]) {
					repeat = true;
					break;
				}else
					j++;
			}
			if ( !repeat ) {
				index = i;
				break;
			} 			
		}
		return index;
	}
	
	/*
	 * Q5, find maximum Profit;
	 * Designed by Kai Tian;
	 */
	public static int maxProfit(int[] prices) { 
		if ( prices.length == 0)
			return -1;
		
		int max = 0;
		for(int i = 0; i < prices.length-1; i++)
			for (int j = i + 1; j < prices.length; j++) {
				int tmp = prices[j] - prices[i];
				if (max < tmp) {
					max = tmp;
				}
			}
		return max;
	}
	
	public static void main(String[] args) {
/* ----------------for test
		int[] arr = {0, 2, 1, 5, 9, 4};
		int index = findKthLargest(arr,2);

		sort(arr);
		for ( int i = 0; i < arr.length; i++)
			System.out.print(arr[i]+" ");
		int a[] = twoSum(arr, 10);
		System.out.println(a[0] + " " + a[1]);
		String s = "dafjkqaslfdsjkl";
		int aa = firstUniqChar(s);
		System.out.println(aa);
		System.out.println(maxProfit(arr));
*/

	}

}





