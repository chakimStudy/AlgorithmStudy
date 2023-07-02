package ps.브루트포스.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1767_프로세서연결하기 {
	
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}

	static int N;
	static int[][] map;
	static List<Pos> cores = new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			N = Integer.parseInt(in.readLine());
			
			map = new int[N][N];
			StringTokenizer st = null;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) cores.add(new Pos(i, j));
				}
			}
			
			//logic
			dfs(0, 0, myClone(map), 0);
			
			sb.append(minLen).append("\n");
			
			//init
			maxCoreCnt = 0;
			minLen = Integer.MAX_VALUE;
			cores.clear();
		}
		System.out.println(sb);
	}
	
	private static int[][] myClone(int[][] map2) {
		int[][] clone = new int[N][N];
		for (int i = 0; i < N; i++) {
			clone[i] = map2[i].clone();
		}
		return clone;
	}

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int maxCoreCnt = 0;
	static int minLen = Integer.MAX_VALUE;
	
	private static void dfs(int idx, int cnt, int[][] preMap, int len) {
		//백트래킹 추가 : 현재 연결된 코어 + 남은 코어 < 최대 코어
		if (cnt + (cores.size() - idx) < maxCoreCnt) {
			return;
		}
		
		if (maxCoreCnt == cnt) {
			minLen = Math.min(minLen, len); //최솟값으로
		}
		else if (maxCoreCnt < cnt) {
			maxCoreCnt = cnt;
			minLen = len;
		}
		if (idx == cores.size()) return;

		Pos core = cores.get(idx);

		//상하좌우로 연결하여 연결되면 연결해서 넘김
		boolean already = false;
		for (int di = 0; di < 4; di++) {
			int nr = core.r;
			int nc = core.c;
			boolean isConnected = false;
			int go = 0;
			while(true) {
				nr += dr[di];
				nc += dc[di];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
					//범위를 벗어나면 전원에 연결된 것.
//					System.out.println(nr + " " + nc);
					isConnected = true;
					if (go == 0) {
						//무조건 연결됨. 
						already = true;
					}
					break;
				}
				if (preMap[nr][nc] != 0) {
					//빈칸이 아니면 갈 수 없음. 즉 연결할 수 없음.
					break;
				}
				go++;
			}
			if (already) {
//				dfs(idx+1, cnt+1, myClone(preMap), len);
				break;
			}
			//연결되었으면
			if (isConnected) {
				//맵에 표시해서 다음으로 넘김
				int[][] coreMap = myClone(preMap);
				nr = core.r;
				nc = core.c;
				int l = 0;
				while(true) {
					nr += dr[di];
					nc += dc[di];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) break;
					l++;
					coreMap[nr][nc] = 2; //전선 연결은 2로 표시
				}
				dfs(idx+1, cnt+1, coreMap, len+l);
			}
			//연결되지 않았으면 패스
		}

		//연결하지않고 그냥 넘김
		if (!already) dfs(idx+1, cnt, myClone(preMap), len);
		else dfs(idx+1, cnt+1, myClone(preMap), len);
	}
}
