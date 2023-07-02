package ps.브루트포스.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_G4_3055_탈출 {
	/*
	 * . 비어있음
	 * * 물
	 * X 돌
	 * 
	 * dfs
	 * 1. 물이 확장(먼저 함)
	 * 2. 고슴도치 이동
	 */

	static class Pos{
		int r, c;
		int time;

		public Pos(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	static int R, C;
	static char[][] map;
	static int goR, goC;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] split = in.readLine().split(" ");
		R = Integer.parseInt(split[0]);
		C = Integer.parseInt(split[1]);
		
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = in.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'S') {
					goR = i;
					goC = j;
				}
			}
		}
		
		//logic
		bfs(); //시간
		
		System.out.println((minTime == -1)? "KAKTUS" : minTime);
	}
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int minTime = -1;
	private static void bfs() {
		Queue<Pos> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[R][C];
		
		q.offer(new Pos(goR, goC, 0)); //고슴도치 위치
		visited[goR][goC] = true;
		
		int preTime = -1;
		while(!q.isEmpty()) {
			Pos cur = q.poll();

			//물이 확장
			if (preTime != cur.time) {
				spreadWater();
				preTime = cur.time;
			}
			//고슴도치의 이동
			for (int di = 0; di < 4; di++) {
				int nr = cur.r + dr[di];
				int nc = cur.c + dc[di];
				
				if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc]) continue;
				
				//도착지 또는 빈칸으로만 갈 수 있음
				if (map[nr][nc] == 'D') {
					//도착!
					minTime = cur.time+1;
					return;
				}
				//빈칸으로 이동
				if (map[nr][nc] == '.') {
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc, cur.time+1));
				}
			}
			
		}
	}
	private static void spreadWater() {
		//clone
		char[][] clone = new char[R][C];
		for (int i = 0; i < R; i++) {
			clone[i] = map[i].clone();
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == '*') { //문자가 0이면 = 이전에 생긴 물이면
					//상하좌우에 퍼짐
					for (int di = 0; di < 4; di++) {
						int nr = i + dr[di];
						int nc = j + dc[di];
						if (nr < 0 || nr >= R || nc < 0 || nc >= C || clone[nr][nc] != '.') continue;
						//빈곳에만 물이 퍼짐
						clone[nr][nc] = '*';
					}
				}
			}
		}
		map = clone;
	}
}
