package ps.브루트포스.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_14502_연구소 {
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}

	/*
	 * 모든 0 중에서 3개를 골라서 1로 만들고
	 * 바이러스 퍼트리고
	 * 안전영역 개수 세면 됨
	 */
	
	static int N, M;
	static int[][] map;
	static int[][] cloneMap;
	static int maxCnt = 0;
	static List<Pos> virus = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		cloneMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) virus.add(new Pos(i, j));
			}
		}
		
		//logic
		// 0 세 개 고름 (r, c, cnt)
		comb(0);
		
		System.out.println(maxCnt);
	}

	private static void comb(int cnt) {
		if (cnt == 3) {
			for (int i = 0; i < N; i++) {
				cloneMap[i] = map[i].clone();
			}
			spreadVirus();
			countSafeArea();
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					comb(cnt+1);
					map[i][j] = 0;
				}
			}
		}
		
	}

	private static void countSafeArea() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (cloneMap[i][j] == 0) cnt++;
			}
		}
		maxCnt = Math.max(maxCnt, cnt);
	}

	private static void spreadVirus() {
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};

		Queue<Pos> q = new ArrayDeque<>();
		for (Pos v : virus) {
			q.offer(v);
			
			while(!q.isEmpty()) {
				Pos cur = q.poll();
				
				for (int di = 0; di < 4; di++) {
					int nr = cur.r + dr[di];
					int nc = cur.c + dc[di];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
					if (cloneMap[nr][nc] == 0) {
						cloneMap[nr][nc] = 2;
						q.add(new Pos(nr, nc));
					}
				}
			}
		}
		

	}

}
