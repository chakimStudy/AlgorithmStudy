package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_S2_10819_차이를최대로 {
	
	static int N, answer;
	static int[] array;
	static int[] combArray;
	static boolean[] isSelected;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(in.readLine());
		array = new int[N];
		combArray = new int[N];
		isSelected = new boolean[N];
		answer = 0;
		String[] str = in.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(str[i]);
		} 
		
		combination(0);
		sb.append(answer);
		System.out.println(sb);
	}

	private static void combination(int count) {
		if(count == N) {
//			System.out.println(Arrays.toString(combArray));
			int result = calc();
			if(result > answer) answer = result;
			return;
		}
		for(int i = 0; i < N; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			combArray[count] = array[i];
			combination(count + 1);
			isSelected[i] = false;
		}
	}

	private static int calc() {
		
		int sum = 0;
		
		for(int i = 1; i < N; i++) {
			
			int temp = combArray[i - 1] - combArray[i];
			if(temp < 0) temp = -temp;
			sum += temp;
		}
		return sum;
	}
}
