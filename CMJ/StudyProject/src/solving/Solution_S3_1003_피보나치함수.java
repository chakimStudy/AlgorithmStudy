package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_S3_1003_피보나치함수 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = in.readLine();
		int T = Integer.parseInt(str);
		for(int test_case = 0; test_case < T; test_case++) {
			str = in.readLine();
			int N = Integer.parseInt(str);
			int[][] dp = new int[40 + 1][2];
			dp[0][0] = 1;
			dp[1][1] = 1;
			for(int i = 2; i < N + 1; i++) {
				dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
				dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
			}
			sb.append(dp[N][0] + " " + dp[N][1] + "\n");
		}
		System.out.println(sb);
	}
}
