package ps.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G3_20058_마법사상어와파이어스톰 {

	/*
	 * N, Q
	 * 2^N의 크기의 map
	 * (r, c) 값은 얼음의 양
	 * 
	 * 
	 * 1. 2^L의 부분 격자로 나눠 배돌 수행
	 * 2. 각각의 상하좌우에 얼음이 3개 이상 없으면 해당 칸의 얼음 -1
	 * -- 위를 총 Q번 시전
	 * 
	 * 결과
	 * 1. 남아있는 얼음의 합
	 * 2. 가장 큰 덩어리가 차지하는 칸의 개수 - bfs
	 */
	
	static int N, Q;
	static int[][] map;
	static int L;
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		N = (int) Math.pow(2, N);
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//logic
		st = new StringTokenizer(in.readLine());
		while(Q-- > 0) {
			L = Integer.parseInt(st.nextToken());
			L = (int) Math.pow(2, L);
			
			rotation();
			minus();
			
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
		}
		
		//결과
		int sum = 0;
		int maxCnt = 0;
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += map[i][j];
				if (map[i][j] > 0 && !visited[i][j]) {
					visited[i][j] = true;
					int cnt = bfs(new Pos(i, j));
					maxCnt = Math.max(cnt, maxCnt);
				}
				
			}
		}
		
		System.out.println(sum);
		System.out.println(maxCnt);
	}
	static boolean[][] visited;

	private static int bfs(Pos pos) {
		Queue<Pos> q = new ArrayDeque<>();
		
		q.offer(pos);
		int cnt = 1;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int di = 0; di < 4; di++) {
				int nr = cur.r + dr[di];
				int nc = cur.c + dc[di];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
				
				if (map[nr][nc] > 0) {
					//얼음인 부분을 계속 넣음
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc));
					cnt++;
				}
			}
		}
		return cnt;
	}
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	private static void minus() {
		int[][] minusMap = myClone(map);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) continue;
				
				//해당 좌표의 상하좌우를 보고(map) 얼음이 2개 이하면 --(minusMap)
				int iceCnt = 0;
				for (int di = 0; di < 4; di++) {
					int nr = i + dr[di];
					int nc = j + dc[di];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					if (map[nr][nc] > 0) iceCnt++;
				}
				if (iceCnt <= 2) minusMap[i][j]--;
			}
		}
		
		map = minusMap;
		
	}
	private static void rotation() {
		for (int i = 0; i < N; i+=L) {
			for (int j = 0; j < N; j+=L) {
				//i, j를 기준으로 돌림
				int[][] rot = new int[L][L];
				
				//배열 복사
				for (int r = i; r < i + L; r++) {
					for (int c = j; c < j + L; c++) {
						rot[r-i][c-j] = map[r][c];
					}
				}
				
				//돌림
				int[][] rot2 = new int[L][L];
                for(int r = 0; r < L; r++) {
                    for(int c = 0; c < L; c++) {
                    	rot2[r][c] = rot[L - 1 - c][r];
                    }
                }
//                for (int k= 0; k < L; k++) {
//                	System.out.println(Arrays.toString(rot2[k]));
//                }
                
				for (int r = i; r < i + L; r++) {
					for (int c = j; c < j + L; c++) {
						map[r][c] = rot2[r%L][c%L];
					}
				}
			}
		}
	}


	private static int[][] myClone(int[][] arr) {
		int[][] clone = new int[N][N];
		for (int i = 0; i < N; i++) {
			clone[i] = arr[i].clone();
		}
		return clone;
	}
	
	/*
	 * 막힌 부분
	 * 1. 배열 돌리기 ㅡㅡ
	 * 
	 */
}
