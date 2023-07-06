package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_G3_1520_내리막길 {
	
	static int[][] map;
	
	static int M, N;
	
	static int ans = 0;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] str = in.readLine().split(" ");
		M = Integer.parseInt(str[0]);
		N = Integer.parseInt(str[1]);
		map = new int[M][N];
		for(int i = 0; i < M; i++) {
			str = in.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		int[][] visited = new int[M][N];
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				visited[i][j] = -1;
			}
		}
		dfs(0, 0, visited);
		System.out.println(visited[0][0]);
	}

	private static int dfs(int x, int y, int[][] visited) {
		if(x == M - 1 && y == N - 1) {
			return 1;
		}
		if(visited[x][y] != -1) {
			return visited[x][y];
		}
		visited[x][y] = 0;
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
			if(map[x][y] > map[nx][ny])
			visited[x][y] += dfs(nx, ny, visited);
		}
		return visited[x][y];
	}
}
