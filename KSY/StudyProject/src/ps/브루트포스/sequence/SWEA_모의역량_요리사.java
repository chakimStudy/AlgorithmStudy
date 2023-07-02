package ps.브루트포스.sequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_모의역량_요리사 {

	/*
	 * 음식당 식재료 수는 N/2로 확정. 조합 ! T or F
	 * 아니 근데 음식이 2개임
	 * 
	 */
	static int N;
	static int[][] S;
	static int minDiff = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(in.readLine());
		int tc = 1;
		while(T-- > 0) {
			sb.append("#").append(tc).append(" ");
			
			N = Integer.parseInt(in.readLine());
			S = new int[N][N]; //초기화...
			for (int i = 0; i < N; i++) {
				String[] line = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					S[i][j] = Integer.parseInt(line[j]);
				}
			}
			
			//logic: 두 재료를 선택.. 부분집합.. 
			
			visited = new boolean[N];
			
			comb(0, 0);
			sb.append(minDiff).append("\n");
			
			tc++;
			
			//init
			minDiff = Integer.MAX_VALUE;
		}
		System.out.println(sb);
	}
	
	static boolean[] visited;

	private static void comb(int cnt, int start) {
		if (cnt == N/2) {
			int diff = getDiff();
			minDiff = (minDiff < diff) ? minDiff : diff;
			return;
		}
		
		for (int i = start; i < N; i++) {
			//방문체크할 필요는 없음
			visited[i] = true;
			comb(cnt + 1, i + 1);
			visited[i] = false;
		}
	}

	private static int getDiff() {
		int trueSum = 0;
		int falseSum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				if (visited[i] && visited[j]) trueSum += (S[i][j] + S[j][i]);
				else if (!visited[i] && !visited[j]) falseSum += (S[i][j] + S[j][i]);
			}
		}
		return Math.abs(trueSum - falseSum);
	}


}
