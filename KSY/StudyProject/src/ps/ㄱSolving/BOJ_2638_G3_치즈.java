package ps.ㄱSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2638_G3_치즈 {
	
	static class Chz{
		int exist;
		int outCnt;
		public Chz(int exist, int outCnt) {
			super();
			this.exist = exist;
			this.outCnt = outCnt;
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
		
		Queue<Pos> oneQ = new ArrayDeque<>();
		map = new Chz[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = new Chz(Integer.parseInt(st.nextToken()), 0);
				if (map[i][j].exist == 1) oneQ.offer(new Pos(i, j));
			}
		}
		
		//logic
		
		
		
		
		
		
		
		
		/*
		 * 1.
		 * oneQ를 돌면서
		 * bfs 하면서
		 * 사면중 밖으로 나가는 0이 몇개인지 체크
		 * 
		 * 2.
		 * bfs 후에 
		 * 2개 이상이면 -> 0으로 만들고 사면탐색하면서 outCnt++;
		 * 2개 이하면 -> 패스
		 * 
		 * 
		 * 2를 반복(치즈가 없을때까지)
		 */
		
		//사면탐색 -> 나가는 0이 몇개인지 체크
//		while(!oneQ.isEmpty()) {
//			Pos cur = oneQ.poll();
//			
//			for (int di = 0; di < 4; di++) {
//				int nr = cur.r + dr[di];
//				int nc = cur.c + dc[di];
//				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
//				if (map[nr][nc].exist == 1) continue;
//				
//				visited = new boolean[N][M];
//				visited[nr][nc] = true;
//				if (map[nr][nc].exist == 0 && isOutside(nr, nc)) map[cur.r][cur.c].outCnt++;
//			}
//		}
		//뭔가 여기서 시간이 많이 걸리는 거 같음.
		// 그냥 out인 부분을 -1로 채우자
		
//		top();
//		down();
//		left();
//		right();

		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j].exist + " ");
			}
			System.out.println();
		}
		System.out.println();
		while(!oneQ.isEmpty()) {
			Pos cur = oneQ.poll();
			
			int outCnt = 0;
			for (int di = 0; di < 4; di++) {
				int nr = cur.r + dr[di];
				int nc = cur.c + dc[di];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				if (map[nr][nc].exist == -1) outCnt++;
			}
			map[cur.r][cur.c].outCnt = outCnt; 
		}
		
		int time = 0;
		boolean haveChz = true;
		while(haveChz) {
			haveChz = false;
			
			Queue<Pos> addQ = new ArrayDeque<>();
			//치즈 녹음
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j].exist == 1) {
						if (map[i][j].outCnt >= 2) {
							map[i][j].exist = 0; //녹음
//							addCnt(i, j); 여기서 하면 안됨
							addQ.offer(new Pos(i, j));
						}
						haveChz = true;									//치즈존재
					}
				}
			}
			
			//녹은 치즈 주위에 1이 있으면 cnt++
			while(!addQ.isEmpty()) {
				Pos cur = addQ.poll();
				
				for (int di = 0; di < 4; di++) {
					int nr = cur.r + dr[di];
					int nc = cur.c + dc[di];
					if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
					if (map[nr][nc].exist == 1)
						map[nr][nc].outCnt++;
				}
			}

			time++;
		}

		System.out.println(time-1);
	}

	private static void down() {
		for (int j = 0; j < M; j++) {
			for (int i = N-1; i >= 0; i--) {
				if (map[i][j].exist == 0) map[i][j].exist = -1;
				else if (map[i][j].exist == 1) break;
			}
		}
	}

	private static void top() {
		for (int j = 0; j < M; j++) {
			for (int i = 0; i < N; i++) {
				if (map[i][j].exist == 0) map[i][j].exist = -1;
				else if (map[i][j].exist == 1) break;
			}
		}
	}

	private static void right() {
		for (int i = 0; i < N; i++) {
			for (int j = M-1; j >= 0; j--) {
				if (map[i][j].exist == 0) map[i][j].exist = -1;
				else if (map[i][j].exist == 1) break;
			}
		}
	}

	private static void left() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j].exist == 0) map[i][j].exist = -1;
				else if (map[i][j].exist == 1) break;
			}
		}
	}

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	private static boolean[][] visited;
	
	private static boolean isOutside(int r, int c) {
		//해당 지점에서부터 dfs를 수행했을 때 하나라도 인덱스 밖으로 나가면 true, 안나가면 false
		
		for (int di = 0; di < 4; di++) {
			int nr = r + dr[di];
			int nc = c + dc[di];
			if (nr < 0 || nr >= N || nc < 0 || nc >= M) return true; //인덱스 밖으로 나가면 true
			if (visited[nr][nc] || map[nr][nc].exist == 1) continue;
			
			visited[nr][nc] = true;
			if(map[nr][nc].exist == 0 && isOutside(nr, nc)) return true;
//			visited[nr][nc] = false;
		}
		return false;
	}
}
