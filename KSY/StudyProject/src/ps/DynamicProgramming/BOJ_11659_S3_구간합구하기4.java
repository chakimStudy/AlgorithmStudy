package ps.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11659_S3_구간합구하기4 {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static int n;
	static int[] nums;
	static int[] sums;
	
	public static void main(String[] args) throws IOException {
		StringBuffer sb = new StringBuffer();
		
		String[] nm = in.readLine().split(" ");
		n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);
		
		nums = getLine();
		sums = new int[nums.length];
		findSumArr();
		
		while(m-- > 0) {
			int[] indexes = getLine();
			sb.append(getSum(indexes[0], indexes[1])).append('\n');
		}
		System.out.println(sb);
	}

	private static void findSumArr() {
		sums[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			sums[i] = sums[i - 1] + nums[i];
		}
	}

	private static int getSum(int i, int j) {
		int res = sums[j - 1];
		if (i - 2 >= 0) res -= sums[i - 2];
		return res;
	}
	//1~3 : 0~2
	private static int[] getLine() throws IOException {
		String[] numArr = in.readLine().split(" ");
		int[] nums = new int[numArr.length];
		for (int i = 0; i < numArr.length; i++) {
			nums[i] = Integer.parseInt(numArr[i]);
		}
		return nums;
	}
	
	
}
