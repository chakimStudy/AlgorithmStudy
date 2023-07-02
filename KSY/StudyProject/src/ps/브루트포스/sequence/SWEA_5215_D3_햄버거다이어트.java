package ps.브루트포스.sequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_5215_D3_햄버거다이어트 {

	static class Food{
		int score;
		int ka;
		public Food(int score, int ka) {
			super();
			this.score = score;
			this.ka = ka;
		}
		
	}
	static int N, L;
	static Food[] foodInfo;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(in.readLine());
		int tc = 1;
		while(T-- > 0) {
			String[] line = in.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			L = Integer.parseInt(line[1]);
			foodInfo = new Food[N];
			//input
			for (int i = 0; i < N; i++) {
				line = in.readLine().split(" ");
				int score = Integer.parseInt(line[0]);
				int ka = Integer.parseInt(line[1]);
				foodInfo[i] = new Food(score, ka);
			}
			
			/*
			 * 조합.
			 * 매개변수 : start, 점수 합, 칼로리 합
			 * visited
			 */
			visited = new boolean[N];
			comb(0, 0, 0);
			sb.append("#" + tc + " " + maxSum + "\n");
			
			//init
			maxSum = 0;
			tc++;
		}
		System.out.println(sb);
	}

	static boolean[] visited;
	static int maxSum = 0;
	private static void comb(int start, int scoreSum, int kSum) {
		if (kSum > L) return;
		maxSum = (maxSum > scoreSum)? maxSum : scoreSum;
		
		for (int i = start; i < N; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			comb(i + 1, scoreSum + foodInfo[i].score, kSum + foodInfo[i].ka);
			visited[i] = false;
		}
	}
}
