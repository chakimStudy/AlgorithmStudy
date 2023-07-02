package ps.ㄱSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S2_1012_유기농배추 {

	static int[][] map;
	static int N, M, K;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			//배추위치
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(in.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				map[r][c] = 1;
			}
			
			//탐색하면서 방문했으면 0으로 바꿈. 덩어리 개수 카운트
			int cnt = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] == 1) {
						//배추 있으니까 여기부터 bfs
						bfs(r, c);
						cnt++;
					}
				}
			}
			sb.append(cnt + "\n");
		}
		System.out.println(sb);
	}
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	private static void bfs(int r, int c) {
		Queue<Pos> q = new ArrayDeque<>();
		
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		q.offer(new Pos(r, c));
		map[r][c] = 0; //방문함
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int di = 0; di < 4; di++) {
				int nr = cur.r + dr[di];
				int nc = cur.c + dc[di];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 0) continue;
				//배추가 잇으면
				map[nr][nc] = 0; //0으로 만들고
				q.offer(new Pos(nr, nc)); //넣음
			}
		}
	}
}
