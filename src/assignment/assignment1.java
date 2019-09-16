package assignment;

public class assignment1 {

	/*
	 * Q1, find the maximum value'index in array; Designed by Kai Tian;
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
	 * Q2, Kth Largest Element in an Array Designed by Kai Tian;
	 */
	public static int findKthLargest(int[] nums, int k) {
		if (nums.length == 0 || nums.length < k)
			return -1;

		sort(nums);
		return nums[nums.length-k];
	}

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arr = { 3, 2, 1, 5, 6, 4 };
		int index = findKthLargest(arr,2);

		sort(arr);
		for ( int i = 0; i < arr.length; i++)
			System.out.print(arr[i]+" ");
		 System.out.println(index);

	}

}
