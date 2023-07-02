package ps.ㄱSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G2_17136_색종이붙이기 {
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}

	static int N = 10;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//logic
		/*
		 * for문으로 map 스캔하면서
		 * 1인 부분이 나오면
		 * 그 부분이 색종이의 왼쪽 위 모서리가 될 수 있는지 확인한다.
		 * 최소 개수의 색종이이므로.. 크기가 큰 색종이부터 붙여본다.(dfs)
		 * 이때 색종이는 1이 있는 곳에만 붙일 수 있다.
		 * 붙일 수 있으면 붙인다 (0으로 만든다)
		 * 
		 * for문으로 되돌ㄹ아와서 계속 탐색하면서 붙인다.
		 * 
		 */
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 1) attach(new Pos(r, c));
			}
		}
	}

	private static void attach(Pos pos) {
		for (int i = 5 ; i >= 1; i--) {
			fillMap(pos, i, myClone(map));
		}
	}

	private static void fillMap(Pos pos, int i, int[][] myMap) {
		
	}
	
	private static int[][] myClone(int[][] arr){
		int[][] clone = new int[arr.length][];
		for (int i = 0; i < arr.length; i++) {
			clone[i] = arr[i].clone();
		}
		return clone;
	}
}
