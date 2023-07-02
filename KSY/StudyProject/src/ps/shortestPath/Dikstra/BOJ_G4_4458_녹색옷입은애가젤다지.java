package ps.shortestPath.Dikstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G4_4458_녹색옷입은애가젤다지 {

	static int N;
	static int[][] map, cost;
	
	static class Pos{
		int r, c, cost;

		public Pos(int r, int c, int cost) {
			super();
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int tc = 1;
		while(true) {
			N = Integer.parseInt(in.readLine());
			if (N == 0) break;
			sb.append("Problem " + tc + ": ");

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//logic
			cost = new int[N][N];
			//초기값 - 초기 위치
			for (int i = 0; i < N; i++) {
				Arrays.fill(cost[i], Integer.MAX_VALUE);
			}
			cost[0][0] = map[0][0];
			
			//다익스트라..
			
			PriorityQueue<Pos> pq = new PriorityQueue<>(new Comparator<Pos>() {

				@Override
				public int compare(Pos o1, Pos o2) {
					return o1.cost - o2.cost;
				}
			});
			pq.offer(new Pos(0, 0, cost[0][0]));
			boolean[][] visited = new boolean[N][N];
//			visited[0][0] = true;
			
			int[] dr = {-1, 1, 0, 0};
			int[] dc = {0, 0, -1, 1};
			while(!pq.isEmpty()) {
				Pos cur = pq.poll();
				visited[cur.r][cur.c] = true;
				
				//주위를 돌면서 비용 업데이트
				for (int di = 0; di < 4; di++) {
					int nr = cur.r + dr[di];
					int nc = cur.c + dc[di];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					if (visited[nr][nc]) continue;
					
					//원래 비용과 여기로 가는 비용 중 작은 값으로 갱신
					cost[nr][nc] = Math.min(cost[nr][nc], cost[cur.r][cur.c] + map[nr][nc]);
					pq.offer(new Pos(nr, nc, cost[nr][nc]));
				}
			}
			
			
			sb.append(cost[N-1][N-1] + "\n");
			tc++;
		}
		System.out.println(sb);
	}
}
