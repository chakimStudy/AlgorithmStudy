package ps.브루트포스.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_2636_치즈 {
	static class Chz{
		int chz;
		boolean end;
		public Chz(int chz, boolean end) {
			super();
			this.chz = chz;
			this.end = end;
		}
		@Override
		public String toString() {
			return "Chz [chz=" + chz + ", end=" + end + "]";
		}
		
	}
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}

	static int N, M;
	static Chz[][] map;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Chz[N][M];

		int chzCnt = 0;
		
		for (int i = 0; i< N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = new Chz(Integer.parseInt(st.nextToken()), false);
				if (map[i][j].chz == 1) chzCnt++;
			}
		}
		
		//logic
		int time = 0;
		int saveCnt = chzCnt;
		while(true) {
			for (int i = 0; i < N; i++) {
				for (int j =0; j < M; j++) {
					if (map[i][j].chz == 0) {
						bfs(new Pos(i, j));
						break;
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j].end) {
						map[i][j].chz = 0;
						map[i][j].end = false;
						chzCnt--;
					}
				}
			}
			
			time++;
			if (chzCnt == 0) break;
			saveCnt = chzCnt;
		}
		System.out.println(time);
		System.out.println(saveCnt);
	}

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	private static void bfs(Pos pos) {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(pos);
		
		boolean[][] visited = new boolean[N][M];
		visited[pos.r][pos.c]= true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int di = 0; di < 4; di++) {
				int nr = cur.r + dr[di];
				int nc = cur.c + dc[di];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if (visited[nr][nc]) continue;
				
				if (map[nr][nc].chz == 1) map[nr][nc].end = true;
				else {
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc));
				}
			}
		}
		
		
	}
}
