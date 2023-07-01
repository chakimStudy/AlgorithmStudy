package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution_S2_유기농배추 {
	
	static boolean[][] visited;
	static boolean[][] cabbage;
	
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String a = in.readLine();
		int t = Integer.parseInt(a);
		for(int test_case = 0; test_case < t; test_case++) 
		{
			String[] str = in.readLine().split(" ");
			M = Integer.parseInt(str[0]);
			N = Integer.parseInt(str[1]);
			int K = Integer.parseInt(str[2]);
			visited = new boolean[N][M];
			cabbage = new boolean[N][M];
			for(int i = 0; i < K; i++) {
				str = in.readLine().split(" ");
				cabbage[Integer.parseInt(str[1])][Integer.parseInt(str[0])] = true;
			}
			
			int ans = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(cabbage[i][j] && !visited[i][j]) {
						ans ++;
						bfs(i, j);
					}
				}
			}
			sb.append(ans);
			sb.append("\n");
		}
		System.out.print(sb);
	}

	private static void bfs(int x, int y) {
		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {x, y});
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			for(int i = 0; i < 4; i++) {
				int nx = temp[0] + dx[i];
				int ny = temp[1] + dy[i];
				if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || !cabbage[nx][ny]) continue;
				queue.offer(new int[] {nx ,ny});
				visited[nx][ny] = true;
			}
		}
	}
}
