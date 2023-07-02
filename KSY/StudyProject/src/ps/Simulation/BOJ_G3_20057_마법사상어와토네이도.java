package ps.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G3_20057_마법사상어와토네이도 {
	/*
	 * 배돌 + a
	 * 
	 * 좌 하 우 상 
	 * 
	 * r: 0
	 * c: -1
	 * 
	 * r: +1
	 * c: 0
	 * 
	 * r: 0
	 * c: +2;
	 * 
	 * r: -2
	 * c: 0
	 * ---
	 * 
	 * r: 0
	 * c: -3
	 * 
	 * 같은 stride를 두 번씩 하면서 
	 */

	static int N;
	static int[][] map;
	//좌하우상
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {-1, 0, 1, 0};
	static int outOfBound = 0;
	static int original = 0;
	static int use = 0;
	
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
		int stride = 1;
		int tR = N/2;
		int tC = N/2;
		int di = 0; //시작은 좌
		boolean isEnd = false;
		while(true) {
			//stride만큼 이동
			for (int i = 0; i < stride; i++) {
				tR += dr[di];
				tC += dc[di];
//				System.out.println(tR + " " + tC);
//				System.out.println("di " + di);

				//토네이도 발생
				move(tR, tC, di); //nr, nc에 있는 모래가 현재 방향과 비율에 맞게 흩어짐.

				if (tR == 0 && tC == 0) {
					isEnd = true;
					break;
				}
//				//print
//				for (int r = 0; r < N; r++) {
//					System.out.println(Arrays.toString(map[r]));
//				}
//				System.out.println();
			}
			if (isEnd) break;
			
			//인덱스 처리
			di = (di + 1) % 4;
			//좌 또는 우 일때 stride 증가
			if (di == 0 || di == 2) stride++;
		}
		System.out.println(outOfBound);
	}
	//좌 하 우 상
	private static void move(int tR, int tC, int di) {
		//init
		original = map[tR][tC];
		use = 0;
		
		//x
		int preR = tR - dr[di];
		int preC = tC - dc[di];
		//a
		int nR = tR + dr[di];
		int nC = tC + dc[di];
		
		if (di == 0 || di == 2) {
			//좌우
			/*
			 * x의 상하 1, y의 상하 7, 2, a의 상하 10, a의 다음 5
			 */
			
			//pre: 1
			int amount = (int) (original * 0.01); //1퍼
			spread(preR, preC, amount, new int[] {1, 3}, 1);
			
			//tR, tC
			//한 칸
			amount = (int) (original *0.07);
			spread(tR, tC, amount, new int[] {1, 3}, 1);
			//두 칸
			amount = (int) (original *0.02);
			spread(tR, tC, amount, new int[] {1, 3}, 2);
			
			//알파
			amount = (int) (original *0.1);
			spread(nR, nC, amount, new int[] {1, 3}, 1);
			//알파 앞
			amount = (int) (original *0.05);
			spread(nR, nC, amount, new int[] {di}, 1);
			
		}
		else {
			//상하
			/*
			 * x의 좌우 1, y의 좌우 7, 2, a의 좌우 10, a의 다음 5
			 */
			
			//pre: 1
			int amount = (int) (original * 0.01); //1퍼
			spread(preR, preC, amount, new int[] {0, 2}, 1);
			
			//tR, tC
			//한 칸
			amount = (int) (original *0.07);
			spread(tR, tC, amount, new int[] {0, 2}, 1);
			//두 칸
			amount = (int) (original *0.02);
			spread(tR, tC, amount, new int[] {0, 2}, 2);
			
			//알파
			amount = (int) (original *0.1);
			spread(nR, nC, amount, new int[] {0, 2}, 1);
			//알파 앞
			amount = (int) (original *0.05);
			spread(nR, nC, amount, new int[] {di}, 1);
			
		}
		
		//알파 자리에 남은 거 더해줌
		int amount = original - use;
		spread(tR, tC, amount, new int[] {di}, 1);
		map[tR][tC] = 0;
	}
	private static void spread(int r, int c, int amount, int[] arr, int stride) {
		for (int i : arr) {
			int nr = r;
			int nc = c;
			for (int k = 0;  k < stride; k++){
				nr += dr[i];
				nc += dc[i];
			}
//			System.out.println(nr + " " + nc);
			use += amount;
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) 
				outOfBound += amount;
			else {
				map[nr][nc] += amount;
			}
		}
	}
}
