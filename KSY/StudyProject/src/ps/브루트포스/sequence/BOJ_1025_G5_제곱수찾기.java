package ps.브루트포스.sequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1025_G5_제곱수찾기 {

	static int N, M;
	static String[][] map;
	static long maxVal = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
		map = new String[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = in.readLine().split("");
		}
		
		//logic
		/*
		 * 예시를 보면 알 수 있지만..
		 * 등차수열의 공차는 0도 되고 음수도 된다!! 주의
		 */
		if (N == 1 && M == 1) {
			check(map[0][0]);
		}
		for (int dr = -N + 1; dr < N; dr++) {
			for (int dc = -M + 1; dc < M; dc++) {
				//모든 공차 조합 생성
				//해당 공차 조합에 대해서 숫자 생성 - 부분집합 ..?
				//맨 첫 원소만 선택해서 넘김. map의 모든 원소가 맨 첫 원소가 될 수 있음.
				if (dr == 0 && dc == 0) continue; //둘 다 0이면 makeNum에서 못빠져나옴
				for (int startR = 0; startR < N; startR++) {
					for (int startC = 0; startC < M; startC++) {
						makeNum(dr, dc, startR, startC, map[startR][startC]);
					}
				}
			}
		}
		System.out.println(maxVal);
	}

	private static void makeNum(int dr, int dc, int r, int c, String num) {
		check(num); //여기 넣으니까 10퍼까지는 올라감 : 선택한 행렬이 등차수열인거지, N, M 범위 내라고 모두 선택해야 하는건 아님!
		
		int nr = r + dr;
		int nc = c + dc;
		if (nr < 0 || nr >= N || nc < 0 || nc >= M) { //M을 N으로 함.. 바부
			//범위가 벗어나면 끝났으니까 리턴
			return;
		}
		makeNum(dr, dc, nr, nc, num.concat(map[nr][nc])); //선택함
//		makeNum(dr, dc, nr, nc, num); //선택 안 함 - 이 경우는 없을듯? 등차수열이니까
	}

	private static void check(String str) {
		int num = Integer.parseInt(str);
		if (Math.sqrt(num) != Math.ceil(Math.sqrt(num))) return; //완전제곱수가 아니면 리턴
		
		//완전제곱수이면, 가장 큰 값으로
		maxVal = Math.max(maxVal, num);
	}
}
