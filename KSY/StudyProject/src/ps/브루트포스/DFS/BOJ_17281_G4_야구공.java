package ps.브루트포스.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_17281_G4_야구공 {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static int maxScore = 0;
	static int[][] map;
	static int[] ru = new int[5];
	static int T;

	static List<int[]> perm = new ArrayList<>();
	static int[] permSub = new int[8];
	static boolean[] isVisited = new boolean[9];
	static int permIdx = 0;

	public static void main(String[] args) throws IOException {
		input();

		//logic
		/*
		 * 1. 8!
		 * 총 9명의 선수
		 * 1번 선수는 4번 타자로 결정
		 * 나머지 8명의 선수를 순열을 구해 8! 표를 얻음
		 * 
		 * 2. 
		 * 얻은 표를 가지고 한 경우씩 야구 게임을 시뮬레이션 한다.
		 * 
		 */
		getPermutation(0);
		int c = 0;
		for (int[] line : perm) {
			//해당 permutation 순서로 경기 시작
			int lineI = 0;
			int player = line[lineI]; //첫 번쨰 선수는 4주자로 고정 - 첫 번째 선수 뺴고 pem함
			int outCnt = 0;
			int score = 0;
			
			int idx = 0;
			int ining = 0;
			
			while(true) {
				ru[0] = 1; //player .입장! -빼먹음

				 //이동
				if (map[ining][player] == 0) 
					outCnt++;
				else 
					goRu(map[ining][player]);
				
				//아웃체크
				if (outCnt == 3) {
					ining++;
					outCnt = 0;
					score += ru[4];	//각 이닝이 시작할 때 주자는 없다. 고로 이닝마다 더해줌..(뺴먹음)
					Arrays.fill(ru, 0);
				}
				//이닝체크
				if (ining >= T) break;
				
				//player 다음 주자 선택
				idx++; //몇 번째 주자인지(0~8)
				idx %= 9;
				if (idx == 3) 	//네 번쨰 주자면 1번 선수로 고정
					player = 0;
				else {			//나머지는 permutation에 따름
					lineI++;
					lineI %= 8;
					player = line[lineI];
				}
			}
			maxScore = (maxScore > score) ? maxScore : score;
		}
		System.out.println(maxScore);
		
	}

	private static void goRu(int i) {
		for (int j = 3; j >= 0; j--) {
			if (ru[j] != 0) {
				if (j + i < ru.length) ru[j + i] += 1;
				else ru[4] += 1;	//인덱스 벗어난거면 홈으로 들어옴
				ru[j] = 0;
			}
		}
	}

	private static void getPermutation(int cnt) {
		if (cnt == 8) {
			perm.add(permSub.clone());
			/*
			 * Arrays.fill(permSub, 0); //초기화 -- 안됨. 배열은 주소...
			그냥 새로 할당..하는 것도 재귀함수에 맞지 않음
			그래서 그냥 clone을 list에 넣어줌!
			 */
			return;
		}
		for (int i = 1; i <= 8; i++) {
			if (isVisited[i]) continue;

			permSub[cnt] = i;
			isVisited[i] = true;
			getPermutation(cnt + 1);
			isVisited[i] = false;
		}
	}

	private static void input() throws IOException {
		T = Integer.parseInt(in.readLine());
		map = new int[T][9];
		
		for (int j = 0; j < T; j++) {
			String[] line = in.readLine().split(" ");
			for (int i = 0; i < line.length; i++) {
				map[j][i] = Integer.parseInt(line[i]);
			}
		}
	}
	
}
