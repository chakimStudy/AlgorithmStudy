package ps.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_14891_톱니바퀴 {

	static int N = 4, M = 8;
	static int[][] wheel;
	static int[] index; //휠의 시작 인덱스 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		wheel = new int[N][M];
		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split("");
			for (int j = 0; j < M; j++) {
				wheel[i][j] = Integer.parseInt(split[j]); //j = 2, 6번!
			}
		}
		
		//logic
		index = new int[N]; //휠의 시작 인덱스 저장. 초기값은 0. 각각 +2, +6 하면 오른쪽, 왼쪽 접한 부분.
		int K = Integer.parseInt(in.readLine());
		while(K-- > 0) {
			int[] move = new int[N];
			StringTokenizer st = new StringTokenizer(in.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1;
			int DIR = Integer.parseInt(st.nextToken());
			move[num] = DIR;
			
			//현재 톱니바퀴보다 오른쪽 톱니바퀴들 움직이기
			int preWh = num;
			for (int wh = num+1; wh < N; wh++) {
				int preNS = wheel[preWh][getIdx(index[preWh] + 2)];
				int curNS = wheel[wh][getIdx(index[wh] + 6)];
				
				if (preNS != curNS) {
					//다르면 반대 방향으로 움직임
					move[wh] = -move[preWh];
					preWh = wh;
				}
				else {
					//같으면 안움직임. 여기서 끝
					break;
				}
			}
			
			//현재 톱니바퀴보다 왼쪽 톱니바퀴들 움직이기
			preWh = num;
			for (int wh = num-1; wh >= 0; wh--) {
				int preNS = wheel[preWh][getIdx(index[preWh] + 6)];
				int curNS = wheel[wh][getIdx(index[wh] + 2)];
				
				if (preNS != curNS) {
					//다르면 반대 방향으로 움직임
					move[wh] = -move[preWh];
					preWh = wh; //첨에 이거 업뎃 안해줘서 틀림
				}
				else {
					//같으면 안움직임. 여기서 끝
					break;
				}
			}
			
			//찐으로 움직이기
			for (int i = 0; i < N; i++) {
				index[i] -= move[i]; //
				index[i] = getIdx(index[i]);
			}
		}
		
		//최종 톱니바퀴
		int score = 0;
		for (int i = 0; i < N; i++) {
			if (wheel[i][getIdx(index[i])] == 1) {
				//S극인 경우
				score += Math.pow(2, i);
			}
		}
		System.out.println(score);
	}

	private static int getIdx(int i) {
		return (i+8) % 8;
	}
}
