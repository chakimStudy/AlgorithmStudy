package ps.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_14503_로봇청소기 {

	static int[][] map;
	static int N, M;
	static int r, c, d;
	
	//상 우 하 좌
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		//logic
		//0: 청소 안 함, 1: 벽, 2: 청소함
		while(true) {
			if (map[r][c] == 0) {
				map[r][c] = 2; //청소함
				cnt++;
			}
			
			if (!canGo(r, c)) {
				//후진
				int nr = r + dr[(d + 2) % 4];
				int nc = c + dc[(d + 2) % 4];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) break;
				if (map[nr][nc] == 1) break; //뺴먹음
				r = nr;
				c = nc;
			}
			else {
				while(true) {
					//반시계 회전
					d = (d + 3) % 4;
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
					if (map[nr][nc] == 0) {
						r = nr;
						c = nc;
						break;
					}
				}
				
			}
		}
		System.out.println(cnt);
	}
	private static boolean canGo(int curR, int curC) {
		for (int di = 0; di < 4; di++) {
			int nr = curR + dr[di];
			int nc = curC + dc[di];
			if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
			if (map[nr][nc] == 0) return true;
		}
		return false;
	}
}
