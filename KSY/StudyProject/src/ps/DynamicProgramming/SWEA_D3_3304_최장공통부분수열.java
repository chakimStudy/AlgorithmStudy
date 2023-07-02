package ps.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_D3_3304_최장공통부분수열 {
	/*
	 * String 비교에는 equals
	 */
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= T; tc++) {
			String[] line = in.readLine().split(" ");
			String[] s1 = line[0].split("");
			String[] s2 = line[1].split("");
			
			//logic
			int N = s1.length;
			int M = s2.length;
			dp = new int[N][M];
			
			//init
			boolean flag = false;
			for (int i = 0; i < N; i++) {
				if (flag || s1[i].equals(s2[0])) {
					dp[i][0] = 1;
					flag = true;
				}
			}
			flag = false;
			for (int j = 0; j < M; j++) {
				if (flag || s1[0].equals(s2[j])) {
					dp[0][j] = 1;
					flag = true;
				}
			}
			
			//dp
			/*
			 * if (s1[i] == s2[j]) a = 1
			 * else a = 0;
			 * 
			 * dp[i][j] = max(dp[i-1][j], dp[i][j-1]) + a
			 * 
			 */
			
			for (int i = 1; i < N; i++) {
				for (int j = 1; j < M; j++) {
					if (s1[i].equals(s2[j])) {
						dp[i][j] = dp[i-1][j-1] + 1;
					}
					else {
						dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
					}
				}
			}
			
			sb.append("#" + tc + " " + dp[N-1][M-1] + "\n");
		}
		System.out.println(sb);
	}
}
