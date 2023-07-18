package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_S1_11497_통나무건너뛰기 {

	static boolean[] isSelected;
	static int[] combArray;
	
	static int[] array;
	
	static int answer;
	
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(in.readLine());
		for(int t_c = 0; t_c < t; t_c++) {
			N = Integer.parseInt(in.readLine());
			String[] str = in.readLine().split(" ");
			array = new int[N];

			for(int i = 0; i < N; i++) {
				array[i] = Integer.parseInt(str[i]);
			}
			combArray = new int[N];
			isSelected = new boolean[N];
			
			answer = Integer.MAX_VALUE;
			combination(0);
			System.out.println(answer);
		}
	}


	private static void combination(int count) {
		if(count == N) {
			int temp = calc();
			if(temp < answer) answer = temp;
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
		int result = 0;
		for(int i = 1; i < N; i++) {
			int temp = combArray[i] - combArray[i - 1];
			if(temp < 0) temp = -temp;
			if(temp > result) result = temp;
		}
		int temp = combArray[N - 1] - combArray[0];
		if(temp < 0) temp = -temp;
		if(temp >result) result = temp;
		return result;
	}
}
