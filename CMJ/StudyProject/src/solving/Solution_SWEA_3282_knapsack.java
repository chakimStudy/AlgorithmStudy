package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_SWEA_3282_knapsack {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int t = Integer.parseInt(in.readLine());
		for(int t_c = 1; t_c <= t; t_c ++) {
			String[] str = in.readLine().split(" ");
			int N = Integer.parseInt(str[0]);
			int K = Integer.parseInt(str[1]);
			int[] weight = new int[N + 1];
			int[] value = new int[N + 1];
			for(int i = 1; i <= N; i++) {
				str = in.readLine().split(" ");
				int V = Integer.parseInt(str[0]);
				int C = Integer.parseInt(str[1]);
				weight[i] = V;
				value[i] = C;
			}
			int[][] dp = new int[N + 1][K + 1];
			for(int i = 1; i <= N; i++) {
				for(int j = 0; j <= K; j++) {
					dp[i][j] = dp[i - 1][j];
					if(j >= weight[i]) {
						dp[i][j] = Math.max(dp[i - 1][j - weight[i]] + value[i], dp[i - 1][j]);
					}
				}
				
			}
			sb.append("#" + t_c + " " + dp[N][K]);
		}
		System.out.println(sb);
	}
}
