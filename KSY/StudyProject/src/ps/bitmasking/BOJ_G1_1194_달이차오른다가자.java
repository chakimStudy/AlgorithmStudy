package ps.bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_G1_1194_달이차오른다가자 {
	static class Pos{
		int r, c;
		int depth;
		int key;
		public Pos(int r, int c, int depth, int key) {
			super();
			this.r = r;
			this.c = c;
			this.depth = depth;
			this.key = key;
		}
		
	}

	static int N, M;
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] split = in.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		
		Pos start = null;
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = in.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '0') {
					start = new Pos(i, j, 0, 0);
				}
			}
		}
		
		/*
		 * 모르겠어서 찾아봄
		 * 1. 비트마스킹
		 * 2. 3차원 visited 사용! - key의 상태에 따른 visited 배열을 따로 만든다.
		 * 처음에는 visited 배열을 다 q에 넣으면서 했지만
		 * 그러면 메모리 초과...
		 */
		//logic
		
		System.out.println(bfs(start));
		
	}

	private static int bfs(Pos start) {
		Queue<Pos> q = new ArrayDeque<>();
		boolean[][][] visited = new boolean[64][N][M]; //key의 64가지 상태에 따른 visited 배열을 따로 만든다.
		
		q.offer(start);
		visited[0][start.r][start.c] = true;
		
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int di = 0; di < 4; di++) {
				int nr = cur.r + dr[di];
				int nc = cur.c + dc[di];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				if (visited[cur.key][nr][nc] || map[nr][nc] == '#') continue;
				
				else if (map[nr][nc] == '1') {
					//탈출
					return cur.depth+1;
				}
				
				else if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
					
					//열쇠
					int newKey = cur.key | (1 << map[nr][nc] - 'a');
					if (!visited[newKey][nr][nc]) {
						//해당 키에 대해 방문하지 않았으면 방문
						visited[newKey][nr][nc] = true;
						q.offer(new Pos(nr, nc,cur.depth+1, newKey));
					}
				}
				
				else if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F') {
					//문
					//키가 있을 때만 통과
					if ((cur.key & (1 << map[nr][nc] - 'A')) != 0) { //0이 아님으로 해야 함
						visited[cur.key][nr][nc] = true;
						q.offer(new Pos(nr, nc,cur.depth+1, cur.key));
					}
				}
				
				else {
					//그냥 지나감
					visited[cur.key][nr][nc] = true;
					q.offer(new Pos(nr, nc,cur.depth+1, cur.key));
				}
			}
		}
		
		return -1;
	}
	
}
