package ps.shortestPath.floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D6_1263_사람네트워크2 {

	/*
	 * 문제에서 cc라는 것이, 한 사용자로부터 다른 모든 사용자까지의 거리의 합을 다 구해서, 그 합이 최소인 사람을 구하는 것.
	 * floyd 써보면,
	 * 일단 행렬 다 채운 다음에
	 * 각 row를 다 더한 값의 최솟값 출력
	 */
	
	static int N;
	static int[][] D;
	static int INF = 1000000; //
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			D = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int n = Integer.parseInt(st.nextToken());
					D[i][j] = (n == 0 && i != j)? INF : n;
				}
			}
			
			//logic
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					if (i == k) continue;
					
					for (int j = 0; j < N; j++) {
						if (i == j || k == j) continue;
						
						D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
					}
				}
			}
			
			
			//print
			int minSum = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					sum += D[i][j];
				}
				minSum = Math.min(minSum, sum);
			}
			
			sb.append(minSum + "\n");
		}
		System.out.println(sb);
	}
}
