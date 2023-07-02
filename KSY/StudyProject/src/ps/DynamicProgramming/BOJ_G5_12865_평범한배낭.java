package ps.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_12865_평범한배낭 {

	static class Item{
		int w, v;

		public Item(int w, int v) {
			super();
			this.w = w;
			this.v = v;
		}
		
	}
	
	static int N, K;
	static Item[] items;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] split = in.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		K = Integer.parseInt(split[1]);
		
		items = new Item[N+1];
		for (int i = 1; i <= N; i++) {
			split = in.readLine().split(" ");
			items[i] = new Item(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
		}
		//logic
		dp = new int[N+1][K+1];
		//0번 물건, 무게 0은 다 0으로 초기화 자동.
		
		for (int n = 1; n <= N; n++) {
			for (int w = 1; w <= K; w++) {
				if (items[n].w > w) {
					//못넣음
					dp[n][w] = dp[n-1][w];
				}
				else {
					//넣은 경우 or 못 넣은 경우 중 좋은 거
					dp[n][w] = Math.max(dp[n-1][w], dp[n-1][w-items[n].w] + items[n].v);
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
