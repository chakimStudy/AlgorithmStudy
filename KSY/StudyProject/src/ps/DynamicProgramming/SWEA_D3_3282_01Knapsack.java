package ps.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_D3_3282_01Knapsack {
	/*
	 * 부분집합으로 하면 2의 100승으로 터진다.
	 * 다른 방법 => DP
	 * 
	 * **실수한부분
	 * - index가 꼬여서 items를 저장할 떄 인덱스를 하나씩 당겨서 저장함..
	 * - 앞으로는 인덱스 1부터 시작하면 그냥 통일하자
	 */
	
	static int N, K;
	static int[][] dp;
	static int[][] items;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			//물건 입력
			items = new int[N+1][2]; //1~N
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				items[i+1][0] = Integer.parseInt(st.nextToken());//부피
				items[i+1][1] = Integer.parseInt(st.nextToken());//가치
			}
			
			//logic
			dp = new int[N+1][K+1];//물건은 1~N 부피는 1~K
			for (int n = 1; n <= N; n++) {
				for (int k = 0; k <= K; k++) {
					//1~n번의 물건만 있을 때 부피가 k인 가방에 담을 수 있는 최대가지
					/*
					 * 
					 * 물건을 넣을 수 있을 때!!
					 * dp[n][k] = max(n번재 물건을 넣는 경우, n번째 물건을 넣지 않는 경우)
					 * 			= max(dp[n-1][k-w[n]] + benefit[n], dp[n-1][k])
					 * 
					 * 물건을 넣을 수 없으면
					 * dp[n][k] = n번째 물건을 넣지 않는 경우
					 * 
					 */
					dp[n][k] = dp[n-1][k];
					if (k >= items[n][0]) {
						dp[n][k] = Math.max(dp[n-1][k-items[n][0]] + items[n][1], dp[n][k]);
					}
				}
			}
			sb.append("#"+tc+" "+dp[N][K]+"\n");
		}
		System.out.println(sb);
	}
}
