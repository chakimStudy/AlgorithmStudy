package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_S3_1485_정사각형 {
	
	static boolean[] isSelected;
	static int[][] dot; 
	static int[] array;
	static boolean answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int t_c = Integer.parseInt(in.readLine());
		for(int t = 0; t < t_c; t++) {
			dot = new int[4][2];
			array = new int[3];
			answer = false;
			isSelected = new boolean[4];
			for(int i = 0; i < 4; i++) {
				String[] str = in.readLine().split(" ");
				int a = Integer.parseInt(str[0]);
				int b = Integer.parseInt(str[1]);
				dot[i][0] = a;
				dot[i][1] = b;
			}
			permutation(0);
			if(answer) System.out.println("1");
			else System.out.println("0");
		}
	}

	private static void permutation(int count) {
		if(count == 3) {
//			System.out.println(Arrays.toString(array));
			double first = getDistance(dot[0], dot[array[0]]);
			boolean flag = true;
			for(int i = 0; i < 2; i++) {
				double now = getDistance(dot[array[i]], dot[array[i + 1]]);
				if(now != first) {
					flag = false;
					break;
				}
			}
			if(first != getDistance(dot[0], dot[array[2]])) flag = false;
			if(flag) {
				if(getDistance(dot[array[0]], dot[array[2]]) == first * Math.sqrt(2) && getDistance(dot[array[0]], dot[array[2]]) == getDistance(dot[0], dot[array[1]])) {
					answer = true;
				}
			}
			return;
		}
		for(int i = 1; i < 4; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			array[count] = i;
			permutation(count + 1);
			isSelected[i] = false;
		}
		
	}
	
	private static double getDistance(int[] a, int[] b) {
		double temp = Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
//		System.out.println(temp);
		return temp;
	}
}
