package telran.people;

import java.util.Arrays;

public class Search {
	
	public static int binaryFirst(int[] arr, int target) {
		int ind = arr.length;
		
		while (Arrays.binarySearch(arr, 0, ind, target) >= 0) {
			ind = Arrays.binarySearch(arr, 0, ind, target);
		}
		return ind == arr.length ? Arrays.binarySearch(arr, 0, ind, target) : ind;
	}
	
	
	public static int binaryLast(int[] arr, int target) {
	    int left = 0;
	    int right = arr.length - 1;
	    int result = -1;

	    while (left <= right) {
	        int mid = left + (right - left) / 2;
	        
	        if (arr[mid] <= target) {
	            result = mid;
	            left = mid + 1;  // Continue searching on the right side for the last occurrence
	        } else {
	            right = mid - 1;
	        }
	    }

	    if (result != -1 && arr[result] == target) {
	        return result;
	    } else {
	        return -(result + 1) - 1;  // Calculate and return the insertion point
	    }
	}



}
