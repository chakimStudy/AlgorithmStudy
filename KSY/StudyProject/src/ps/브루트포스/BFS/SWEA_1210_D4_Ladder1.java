package ps.브루트포스.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;


//bfs
public class SWEA_1210_D4_Ladder1 {
	static class Pos{
		int r;
		int c;
		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static int N = 100;
	static int map[][] = new int[N][N];
	static StringBuffer sb = new StringBuffer();
	private static boolean[][] visited = new boolean[N][N];
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		
		int tc = 1;
		while(tc <= 10) {
			sb.append("#"+tc+" ");
			in.readLine();
			int startC = 0;
			for (int i = 0; i < N; i++) {
				String[] line = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(line[j]);
					if (map[i][j] == 2) startC = j;
				}
			}
			
			bfs(startC);
			
			
			tc++;
		}
		System.out.println(sb);
	}

	private static void bfs(int startC) {
		Queue<Pos> q = new ArrayDeque<>();
		
		q.offer(new Pos(N-1, startC));
		
		Pos lastPos = null;
		while(!q.isEmpty()) {
			Pos curPos = q.poll();
			map[curPos.r][curPos.c] = 3;

			//logic
			lastPos = curPos;
			
			//자식 추가(왼쪽, 오른쪽, 위)
			int[] dr = {0, 0, -1};
			int[] dc = {-1, 1, 0};
			for (int di = 0; di < dr.length; di++) {
				int nr = curPos.r + dr[di];
				int nc = curPos.c + dc[di];
				//인덱스 체크
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if (map[nr][nc] == 1) {
					q.offer(new Pos(nr, nc));
					break; //어차피 사다리 타고 가는 방향은 한 방향이니까....
				}
			}
		}
		sb.append(lastPos.c).append("\n");
	}
}
