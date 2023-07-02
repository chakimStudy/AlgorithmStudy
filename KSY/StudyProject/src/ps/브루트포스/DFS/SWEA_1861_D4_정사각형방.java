package ps.브루트포스.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1861_D4_정사각형방 {
	static int[][] room;
	static int N;
	static int roomNum;
	static int maxCnt;
	static boolean[][] visited;
	static boolean isChange;
	static boolean isEqual;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(in.readLine());
		int tc = 1;
		while(tc <= T) {
			sb.append("#" + tc + " ");
			N = Integer.parseInt(in.readLine());
			room = new int[N][N];
			for (int i = 0; i < N; i++) {
				String[] line = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					room[i][j] = Integer.parseInt(line[j]);
				}
			}
			
			//logic
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visited = new boolean[N][N];
					visited[i][j] = true;
					dfs(i, j, 1);
					if (isChange) roomNum = room[i][j];
					else if (isEqual) roomNum = (roomNum < room[i][j])? roomNum : room[i][j];

					//init
					isChange = false;
					isEqual = false;
				}
			}
			sb.append(roomNum + " " + maxCnt + "\n");
			
			roomNum = 0;
			maxCnt = 0;
			tc++;
		}
		System.out.println(sb);
	}

	
	private static void dfs(int r, int c, int cnt) {
		if (maxCnt < cnt) {
			maxCnt = cnt;
			isChange = true;
		}
		else if (maxCnt == cnt && roomNum > room[r][c]) 
			isEqual = true;
		
		
		//유도조건
		//이동 : 상 하 좌 우
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		for (int di = 0; di < 4; di++) {
			int nr = r + dr[di];
			int nc = c + dc[di];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			
			if ((room[nr][nc] - room[r][c] == 1) && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr, nc, cnt+1);
				visited[nr][nc] = false;
			}
		}
	}
}
