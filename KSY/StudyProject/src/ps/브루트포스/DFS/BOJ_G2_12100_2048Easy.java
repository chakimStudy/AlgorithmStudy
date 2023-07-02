package ps.브루트포스.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G2_12100_2048Easy {

	static int N;
	static int[][] map;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//logic
		//dfs
		
		for (int di = 0; di < 4; di++) {
			dfs(di, myClone(myClone(map)), 1);
		}
		
		System.out.println(maxBlock);
	}
	
	//상,하,좌,우
	/*
	 * 상 : col -> row (N-1~0)
	 * 하 : col -> row (0~N-1)
	 * 좌 : row -> col (N-1~0)
	 * 우 : row -> col (0~N-1)
	 */
	static int[][] curMap = null;
	static int maxBlock = 0;
	private static void dfs(int di, int[][] myMap, int cnt) {
		curMap = myMap;


		if (cnt == 6) {
			int maxVal = getMax();
			maxBlock = Math.max(maxVal, maxBlock);
			return;
		}
		//di 방향대로 가면서, 값을 당겨옴
		switch(di) {
		case 0:
			upDown(N-1, 0, -1);
			break;
		case 1:
			upDown(0, N-1, 1);
			break;
		case 2:
			leftRight(N-1, 0, -1);
			break;
		case 3:
			leftRight(0, N-1, 1);
			break;
		}
//		
//		System.out.println("di " +di + " cnt " + cnt);
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(myMap[i]));
//		}
//		System.out.println();
		
		for (int nextDi = 0; nextDi < 4; nextDi++) {
			dfs(nextDi, myClone(myMap), cnt + 1);
		}
	}
	private static int getMax() {
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, curMap[i][j]);
			}
		}
		return max;
	}
	private static void leftRight(int start, int end, int diff) {
		for (int r = 0; r < N; r++) {

			Queue<Integer> q = new ArrayDeque<>();
			//일단 0이 아닌 값들을 다 큐에 넣었다가
			int c = start;
			while(true) {
				if (curMap[r][c] != 0) q.offer(curMap[r][c]);
				if (c == end) break;
				c+=diff;
			}
			if (q.isEmpty()) continue; // 다 비어있으면 다음으로 패스
			
			//빼면서 계산
			Queue<Integer> resQ = new ArrayDeque<>(); //결과 담음
			int pre = q.poll();
			int cur = 0;
			while(!q.isEmpty()) {
				cur = q.poll();
				if (cur == pre) {
					resQ.offer(pre*2);
					pre = 0;
				}
				else {
					if (pre != 0) resQ.offer(pre);
					pre = cur;
				}
			}
			if (pre != 0) {
				resQ.offer(pre);
			}
			
			//계산한 걸 쌓아줌
			c = start;
			while(true) {
				if (!resQ.isEmpty()) {
					curMap[r][c] = resQ.poll();
				}
				else
					curMap[r][c] = 0;
				
				if (c == end) break;
				c+=diff;
			}
			
		}
	}
	private static void upDown(int start, int end, int diff) {
		for (int c = 0; c < N; c++) {

			Queue<Integer> q = new ArrayDeque<>();
			//일단 0이 아닌 값들을 다 큐에 넣었다가
			int r = start;
			while(true) {
				if (curMap[r][c] != 0) q.offer(curMap[r][c]);
				
				if (r == end) break;
				r += diff;
			}
			if (q.isEmpty()) continue; // 다 비어있으면 다음으로 패스
			
			//빼면서 계산
			Queue<Integer> resQ = new ArrayDeque<>(); //결과 담음
			int pre = q.poll();
			int cur = 0;
			while(!q.isEmpty()) {
				cur = q.poll();
				if (cur == pre) {
					resQ.offer(pre*2);
					pre = 0;
				}
				else {
					if (pre != 0) resQ.offer(pre);
					pre = cur;
				}
			}
			if (pre != 0) {
				resQ.offer(pre);
			}
			
			//계산한 걸 쌓아줌
			r = start;
			while(true) {
				if (!resQ.isEmpty()) {
					curMap[r][c] = resQ.poll();
				}
				else
					curMap[r][c] = 0;
				if (r == end) break;
				r += diff;
			}
			
		}

	}
	private static int[][] myClone(int[][] map2) {
		int[][] clone = new int[N][N];
		for (int i = 0; i < N; i++) {
			clone[i] = map2[i].clone();
		}
		return clone;
	}
}
