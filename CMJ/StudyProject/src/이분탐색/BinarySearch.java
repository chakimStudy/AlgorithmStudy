package 이분탐색;

import java.util.Arrays;

public class BinarySearch {
	
	public static int binarySearch(int[] array, int M) {
		Arrays.sort(array);
		
		int left = 0;
		int right = array.length - 1;
		int mid = 0;
		
		while(left <= right) {
			mid = (left + right) / 2;
			if( M == array[mid] ) {
				return mid;
			}else if(array[mid] < M) {
				left = mid + 1;
			}else {
				right = mid - 1;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		
		int[] array = {4, 5, 2, 7, 6};
		System.out.println(binarySearch(array, 5));
	}
}
