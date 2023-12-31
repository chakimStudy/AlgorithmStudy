package ps.브루트포스.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15686_G5_치킨배달 {
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		@Override
		public boolean equals(Object obj) {
			Pos casted = (Pos)obj;
			return r == casted.r && c == casted.c;
		}
	}

	static int N, M;
	static int[][] map;
	static int minCityLen = Integer.MAX_VALUE-1;
	static int chI = 0;
	static int[][][] depth;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//logic
		depth = new int[N][N][13]; //bfs depth를 저장할 배열
		//아래에서 min을 쓰기 때문에 다 최댓값으로 초기화 필요

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2) {
					findChickenLen(new Pos(i, j));
//					findHome();
					chI++;
				}
			}
		}
		
		//print - M개의 치킨집만 살아남았을 떄 도시의 치킨거리 최솟값. 즉 minHomeLen
		//부분집합
		selected = new boolean[chI];
		subset(0, 0);
		System.out.println(minCityLen);
	}

	static boolean[] selected;
	private static void subset(int cnt, int sel) {
		if (sel == M) {
			//M개 다 고름
			//이것들로 도시 치킨 거리 구하기
			int cityLen = findCityLen();
			minCityLen = Math.min(minCityLen, cityLen);
			return; //!!
		}
		if (cnt == chI) return; //위랑 순서
		
		selected[cnt] = true;
		subset(cnt+1, sel + 1);
		
		selected[cnt] = false;
		subset(cnt+1, sel);
	}


	private static int findCityLen() {
		int homeLen = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) { //집인 경우 좌표값에 대한 치킨거리 추가
					int min = 100000000;
					for (int k = 0; k < chI; k++) {
						if (selected[k]) {
							min = Math.min(min, depth[i][j][k]);
						}
					}
					homeLen += min;
				}
			}
		};
		return homeLen;
	}

	private static void findChickenLen(Pos pos) {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(pos);
		
		
		boolean[][] visited = new boolean[N][N];
		visited[pos.r][pos.c] = true;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				depth[i][j][chI] = Integer.MAX_VALUE;
		}
		depth[pos.r][pos.c][chI] = 0; 
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			int[] dr = {1, -1, 0, 0};
			int[] dc = {0, 0, -1, 1};
			for (int di = 0; di < 4; di++) {
				int nr = cur.r + dr[di];
				int nc = cur.c + dc[di];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if (visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				q.offer(new Pos(nr, nc));
				depth[nr][nc][chI] = Math.min(depth[nr][nc][chI], depth[cur.r][cur.c][chI] + 1); //최소거리
			}
		}
	}

}

/*
 * 
 * r과 c는 1부터 시작 - 노상관
 * 
 * 치킨거리: 집과 가장 가까운 치킨집 사이의 거리
 * 도시의 치킨거리
 * 
 * 0빈칸 1집 2치킨
 * 
 * 치킨집의 최대 개수 - 가장 많은 수익을 낼 수 있는 치킨집
 * 도시의 치킨거리가 최소
 * = 집 ~치킨집 사이 거의 최소
 * 
 * 일단 최대한 치킨집이 많을수록 거리는 줄거들 거 같은데
 * 그러면 원래 치킨집 중 부분집합으로 M개 골랐을 때 도시의 치킨거리 구해서 최소인거 저장하고.. 이런식으로.....음
 * 이건 너무 비효율적이고
 * 
 * 2를 만나면
 * 집까지의 치킨거리를 다 구한다.
 * 모든 2에 대해서.
 * 치킨거리 map을 만들어서
 * 3차원배열..>?은 에바고
 * 그냥 치킨거리 Map<Position, List<Integer>> s을 만들면 되겠다.
 */