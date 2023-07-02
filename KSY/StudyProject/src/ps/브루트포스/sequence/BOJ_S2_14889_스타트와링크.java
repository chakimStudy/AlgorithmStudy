package ps.브루트포스.sequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_14889_스타트와링크 {

	static int N;
	static int[][] S;
	static boolean[] visited;
	static int minDiff = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		S = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//logic
		visited = new boolean[N];
		combination(0, 0);
		
		System.out.println(minDiff);
	}
	private static void combination(int start, int cnt) {
		if (cnt == N/2) {
			int diff = calculate();
			minDiff = Math.min(diff, minDiff);
			return;
		}
		
		for (int i = start; i < N; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			combination(i+1, cnt+1);
			visited[i] = false;
		}
	}
	private static int calculate() {
		int trueSum = 0;
		int falseSum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i] && visited[j]) trueSum += S[i][j];
				else if (!visited[i] && !visited[j]) falseSum += S[i][j];
			}
		}
		return Math.abs(trueSum - falseSum);
	}
}
