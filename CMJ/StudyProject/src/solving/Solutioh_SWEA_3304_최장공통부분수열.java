package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solutioh_SWEA_3304_최장공통부분수열 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in  = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(in.readLine());
		for(int test_case = 1; test_case <= t; test_case++) {
			String[] str = in.readLine().split(" ");
			String A = str[0];
			String B = str[1];
			int[][] dp = new int[A.length() + 1][B.length() + 1];
			
			for(int i = 1; i <= A.length(); i++) {
				for(int j = 1; j <= B.length(); j++) {
					if(A.charAt(i - 1) == B.charAt(j - 1)) {
						dp[i][j] = dp[i - 1][j - 1] + 1;
					}
					else {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
					}
				}
			}
			
			System.out.println("#" + test_case + " " + dp[A.length()][B.length()]);
		}
	}
}
