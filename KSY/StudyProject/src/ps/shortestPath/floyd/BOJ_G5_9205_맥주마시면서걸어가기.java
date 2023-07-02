package ps.shortestPath.floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_9205_맥주마시면서걸어가기 {

	/*
	 * 집 ~ 편의점 ~ 페스티벌
	 * 
	 * 편의점을 경유할 수 있다 (k = 1~N)
	 * 모든 편의점의 경우에 따라 거리를 다 보면서
	 * int beerN = Math.올림(거리 / 50);
	 * beerN <= 20 이면 가능!! -> T
	 * 아니면 -> F
	 * 
	 * T/F이기 때문에 가중치는 딱히 없는듯
	 * 
	 * 마지막에 집 ~ 페스티벌 거리가 INF가 아니라면 Happy
	 * INF라면 sad 출력
	 * 
	 */
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	static int N;
	static boolean[][] D;
	static Pos[] pos;
	static int INF = 10000000;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		while(T-- > 0) {
			//input
			N = Integer.parseInt(in.readLine());
			D = new boolean[N+2][N+2];
			pos = new Pos[N+2];
			
			StringTokenizer st = null;
			for (int i = 0; i < N+2; i++) {
				st = new StringTokenizer(in.readLine());
				pos[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			/*
			 * D 초기화.
			 * 거리 / 50 > 20 이면 무한대, 아니면 그 갯수 저장 -> 갯수 말고 거리 저장. 50미터 단위니까.
			 * 
			 * 경유해서 가지 않는 경우로 초기화. 직통으로 가는 경우.
			 */
			for (int i = 0; i < N+2; i++) {
				for (int j = 0; j < N+2; j++) {
					if (i == j) continue;
					
					if (Math.abs(pos[i].x - pos[j].x) + Math.abs(pos[i].y - pos[j].y) <= 1000)
						D[i][j] = true;
				}
			}
			
			/*
			 * 하다 보니까 D는 그냥 boolean으로 해서 되는지 안되는지만 봐도 될듯.
			 * 일단 연습하는 겸 INF 쓰자 -> 이것땜에 틀리나..?
			 */
			for (int k = 0; k < N+2; k++) { //경유지 : 편의점
				for (int i = 0; i < N+2; i++) {
					//출발지
					if (i == k) continue;
					for (int j = 0; j < N+2; j++) {
						//목적지
						if (j == k || j == i) continue;
						
						//경유해서 간다면
						if (D[i][k] && D[k][j]) {
							D[i][j] = true;
						}
					}
				}
				
			}

			if (D[0][N+1]) {
				System.out.println("happy");
			}
			else {
				System.out.println("sad");
			}
		}
	}
}