package ps.브루트포스.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_15683_감시 {
	static class Pos{
		int r, c;
		int num;
		public Pos(int r, int c, int num) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
		}
		
	}

	static int N, M;
	static int[][] map;
	static List<Pos> ls = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 5) ls.add(new Pos(i, j, map[i][j]));
			}
		}
		
		//logic
		/*
		 * 0 빈칸
		 * 6 벽
		 * 1~5 cctv
		 * 
		 * dfs로 회전시키며 간다.
		 */
		dfs(0, myClone(map));
		System.out.println(minZero);
	}
	//상우하좌
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int minZero = Integer.MAX_VALUE;
	private static void dfs(int idx, int[][] m) {
		if (idx == ls.size()) {
			//0을 카운트하여 최소 저장
			int zero = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (m[i][j] == 0) zero++;
				}
			}
			minZero = Math.min(minZero, zero);
			return;
		}
		
		Pos cur = ls.get(idx);
		
		switch(cur.num){
		case 1: 
			//상 or 하 or 좌 or 우 - 회전 시마다 새로운 map를 생성하여 표시해줌
			for (int di = 0; di < 4; di++) {
				//새로운 맵에 체크함
				int[][] newM = myClone(m);
				int r = cur.r;
				int c = cur.c;
				while(true) {
					int nr = r + dr[di];
					int nc = c + dc[di];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= M || newM[nr][nc] == 6) break; //벽만 못넘어가나?
					newM[nr][nc] = -1; //감시하는 부분
					r = nr;
					c = nc;
				}
				dfs(idx+1, newM);
			}
			break;
		case 2: 
			//상하 or 좌우
			
			//상하
			for (int[] arr : new int[][] {{0, 2},  {1, 3}}) {
				int[][] newM = myClone(m);
				for (int di : arr) { 
					int r = cur.r;
					int c = cur.c;
					while(true) {
						int nr = r + dr[di];
						int nc = c + dc[di];
						
						if (nr < 0 || nr >= N || nc < 0 || nc >= M || newM[nr][nc] == 6) break; //벽만 못넘어가나?
						newM[nr][nc] = -1; //감시하는 부분
						r = nr;
						c = nc;
					}
				}
				dfs(idx+1, newM);
			}
			
			break;
		case 3: 
			//상우, 우하, 하좌, 좌상

			for (int[] arr : new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 0}}) {
				int[][] newM1 = myClone(m);
				for (int di : arr) { 
					int r = cur.r;
					int c = cur.c;
					while(true) {
						int nr = r + dr[di];
						int nc = c + dc[di];
						
						if (nr < 0 || nr >= N || nc < 0 || nc >= M || newM1[nr][nc] == 6) break; //벽만 못넘어가나?
						newM1[nr][nc] = -1; //감시하는 부분
						r = nr;
						c = nc;
					}
				}
				dfs(idx+1, newM1);
			}
			break;
		case 4: 
			//상, 우, 하, 좌
			for (int[] arr : new int[][] {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}}) {
				int[][] newM1 = myClone(m);
				for (int di : arr) { 
					int r = cur.r;
					int c = cur.c;
					while(true) {
						int nr = r + dr[di];
						int nc = c + dc[di];
						
						if (nr < 0 || nr >= N || nc < 0 || nc >= M || newM1[nr][nc] == 6) break; //벽만 못넘어가나?
						newM1[nr][nc] = -1; //감시하는 부분
						r = nr;
						c = nc;
					}
				}
				dfs(idx+1, newM1);
			}
			break;
		case 5: 
			//회전없음
			int[][] newM1 = myClone(m);
			for (int di = 0; di < 4; di++) {
				//새로운 맵에 체크함
				int r = cur.r;
				int c = cur.c;
				while(true) {
					int nr = r + dr[di];
					int nc = c + dc[di];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= M || newM1[nr][nc] == 6) break; //벽만 못넘어가나?
					newM1[nr][nc] = -1; //감시하는 부분
					r = nr;
					c = nc;
				}
			}
			dfs(idx+1, newM1);
			break;
		}
		
	}
	private static int[][] myClone(int[][] m) {
		int[][] clone = new int[N][M];
		for (int i = 0; i < N; i++) {
			clone[i] = m[i].clone();
		}
		return clone;
	}
	/*
	 * 실수한 부분
	 * 1. col 최댓값으로 M이 아니라 N 씀..
	 */
}
