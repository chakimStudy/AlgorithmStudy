package ps.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G4_20056_마법사상어와파이어볼 {
	static class Ball{
		int r, c;
		int weight, direct, speed, cnt;
		int DIR;
		
		public Ball(int r, int c, int weight, int direct, int speed) { //cnt는 생성자에는 없어도됨
			super();
			this.r = r;
			this.c = c;
			this.weight = weight;
			this.direct = direct;
			this.speed = speed;
		}

		//기본생성자
		public Ball() {}
		
	}
	static int N, M, K;
	static Ball[][] map;
	static Ball[] balls;
	static Ball[][] moveMap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		//input
		map = new Ball[N][N];
		//이렇게 해주면 모든 필드 0으로 다 초기화.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new Ball();
			}
		}

		balls = new Ball[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].r = r;
			map[r][c].c = c;
			map[r][c].weight = m;
			map[r][c].speed = s;
			map[r][c].direct = d;
			map[r][c].cnt++;
			balls[i] = new Ball(r, c, m, s, d);
		}

		//logic
		while(K-- > 0) {
			moveMap = new Ball[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					moveMap[i][j] = new Ball();
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].cnt == 0) continue;
					else if (map[i][j].cnt == 1) {
						//해당 칸에 하나의 파이어볼만 존재 -> 그 정보에 따라 이동
						move(map[i][j]);
					}
					else {
						//해당 칸에 여러 개의 파이어볼 존재 -> 속성들이 합쳐지고, 그 정보에 따라 이동
						map[i][j].weight /= 5;
						if (map[i][j].weight == 0) continue;
						map[i][j].speed /= map[i][j].cnt;
						if (map[i][j].DIR == 0) {
							//0, 2, 4, 6
							map[i][j].direct=0; move(map[i][j]);
							map[i][j].direct=2; move(map[i][j]);
							map[i][j].direct=4; move(map[i][j]);
							map[i][j].direct=6; move(map[i][j]);
						}
						else {
							map[i][j].direct=1; move(map[i][j]);
							map[i][j].direct=3; move(map[i][j]);
							map[i][j].direct=5; move(map[i][j]);
							map[i][j].direct=7; move(map[i][j]);
						}
					}
				}
			}

			//map 업뎃
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = moveMap[i][j];
				}
			}

		}
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(moveMap[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();

//		//남아있는 상어의 질량 합 출력
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].cnt == 1) {
					sum += map[i][j].weight;
				}
				else if (map[i][j].cnt > 1) {
					sum += (map[i][j].weight/5) *4;
				}
			}
		}
		System.out.println(sum);
	}
	
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

	private static void move(Ball ball) {
		int moveR = ball.r + dr[ball.direct]*ball.speed;
		int moveC = ball.c + dc[ball.direct]*ball.speed;
		moveR %= N;
		moveC %= N;
		if (moveR < 0) moveR += N;
		if (moveC < 0) moveC += N;
		//양수로 맞춰줌.범위 안으로 맞춰줌.
		moveMap[moveR][moveC].r = moveR;
		moveMap[moveR][moveC].c = moveC;
		moveMap[moveR][moveC].cnt++;
		moveMap[moveR][moveC].weight += ball.weight;
		moveMap[moveR][moveC].speed += ball.speed;
		if (moveMap[moveR][moveC].cnt > 1) {
			 //두 개 이상이면
			if (moveMap[moveR][moveC].DIR == 0 && ((moveMap[moveR][moveC].direct % 2 == 0 && ball.direct % 2 == 0)
					||(moveMap[moveR][moveC].direct % 2 != 0 && ball.direct % 2 != 0))) moveMap[moveR][moveC].DIR = 0;
			else moveMap[moveR][moveC].DIR = 1;
		}
		else {
			//하나면
			moveMap[moveR][moveC].direct = ball.direct;
		}
		
	}
}
