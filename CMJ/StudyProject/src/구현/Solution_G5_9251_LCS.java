package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_G5_9251_LCS {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] A = in.readLine().toCharArray();
		char[] B = in.readLine().toCharArray();
		
		int[][] dp = new int[A.length][B.length];
		
		for(int i = 0; i < A.length; i++) {
			for(int j = 0; j < B.length; j++) {
				if(i == 0 || j == 0) {
					if(A[i] == B[j]) {
						dp[i][j] = 1;
					}
				}else {
					if(A[i] != B[j]) {
						dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
					}
					else {
						dp[i][j] = dp[i - 1][j - 1] + 1;
					}
				}
			}
		}
		System.out.println(dp[A.length - 1][B.length - 1]);
	}
}
