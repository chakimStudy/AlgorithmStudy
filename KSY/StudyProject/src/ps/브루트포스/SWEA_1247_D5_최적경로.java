package ps.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1247_D5_최적경로 {
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	static int peopleN;
	static int N = 100;
	
	static Pos start, end;
	static Pos[] stopBy;
	
	private static boolean[] visited;
	static int minDist = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(in.readLine());
		int tc = 1;
		while(T-- > 0) {
			sb.append("#").append(tc).append(" ");
			peopleN = Integer.parseInt(in.readLine());
			StringTokenizer st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			start = new Pos(r, c);
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			end = new Pos(r, c);
			
			stopBy = new Pos[peopleN];
			for (int i = 0; i < peopleN; i++) {
				r = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				stopBy[i] = new Pos(r, c);
			}
			
			//logic
			//순열 : cnt, visited
			visited = new boolean[peopleN];
			permutation(0, 0, start);
			
			sb.append(minDist).append("\n");
			
			//init
			tc++;
			Arrays.fill(visited, false);
			minDist = Integer.MAX_VALUE;
		}
		System.out.println(sb);
	}

	private static void permutation(int cnt, int subDist, Pos preVisit) {
		if (cnt == peopleN) {
			subDist += getDist(preVisit, end);
			minDist = Math.min(minDist, subDist);
			return;
		}
		if (minDist < subDist) return; //백트래킹
		
		for (int i = 0; i < peopleN; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			permutation(cnt+1, subDist + getDist(preVisit, stopBy[i]), stopBy[i]);
			visited[i] = false;
		}
	}

	private static int getDist(Pos prePos, Pos curPos) {
		return Math.abs(curPos.r-prePos.r) + Math.abs(curPos.c - prePos.c);
	}

}

//private static void bfs(int r, int c) {
//if (r == end.r && c == end.c) {
//	return;
//}
//
//int[] dr = {1, -1, 0, 0};
//int[] dc = {0, 0, 1, -1};
//for (int di = 0; di < 4; di++) {
//	int nr = r + dr[di];
//	int nc = c + dc[di];
//	if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
//	if (visited[nr][nc]) continue;
//
//	visited[nr][nc] = true;
//	
//	bfs(nr, nc);
//}
//}
