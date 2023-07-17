package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_G5_9251_LCS {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] A = in.readLine().toCharArray();
		char[] B = in.readLine().toCharArray();
		
		int[][] dp = new int[A.length + 1][B.length + 1];
		
		for(int i = 1; i <= A.length; i++) {
			for(int j = 1; j <= B.length; j++) {
				if(A[i - 1] != B[j - 1]) {
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
				else {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
			}
		}
		System.out.println(dp[A.length][B.length]);
	}
}
